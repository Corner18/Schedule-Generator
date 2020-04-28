package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.*;

import java.util.*;

@Component
public class GenerationServiceImpl implements GenerationService {

    @Autowired
    private OptionalSubjectService optionalSubjectService;

    @Autowired
    private MainSubjectService mainSubjectService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TimeslotDayService timeslotDayService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ProfessorResourceService professorResourceService;

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private AuditoryResourceService auditoryResourceService;

    @Autowired
    private AuditoryService auditoryService;

    // мапа хранящая временной отрезок + профессор и колво экзаменов у этого профессора в эту временную ячейку
    private static Map<ProfessorResource, Integer> map = new HashMap<>();

    @Override
    public void generate() {
        // достали все группы
        List<Group> groups = groupService.getGroups();
        // выполняем цикл по всем группам
        for (Group group : groups) {
            // для каждой группы посчитаем количество экзаменов
            int countExam = getExamCount(group);
            // если еще не все экзамены расставлены в расписании
            if (getAddedExams(group) < countExam) {
                // мапа предметов по выбору. Ключ - идентификатор для каждой группы таких предметов, которые эквивалентны между собой
                // например введение в искусственный интелект и прочее - одна группа, джабалаб - другая группа
                Map<Integer, List<OptionalSubject>> optionalSubjectMap = optionalSubjectService.getMap(group.getGroupSet().getCourse().getId());
                // получили все основные предметы
                List<MainSubject> mainSubjects = mainSubjectService.getAllByGroupId(group.getId());
                // сначала расставляем все предметы по выбору
                for (List<OptionalSubject> optionalSubjects : optionalSubjectMap.values()) {
                    for (OptionalSubject optionalSubject : optionalSubjects) {
                        if (!isOptionalSubjectAdded(optionalSubject)) {
                            AuditoryResource auditoryResource = getPossibleTimeSlotForOptional(group, optionalSubject);
                            if (auditoryResource != null) {
                                Exam exam = Exam.builder()
                                        .optionalSubject(optionalSubject)
                                        .professor(optionalSubject.getProfessor())
                                        .auditory(auditoryResource.getAuditory())
                                        .timeslot(auditoryResource.getTimeslot())
                                        .build();
                                examService.save(exam);

                            }
                        }
                        countExam--;
                    }
                }
                //заводим сет, где будем хранить уже расставленные основные предметы
                Set<MainSubject> set = new HashSet<>();
                // пока остались экзамены, которые не стоят в расписании
                while (countExam > 0) {
                    for (MainSubject mainSubject : mainSubjects) {
                        if (set.add(mainSubject)) {
                            AuditoryResource auditoryResource = getPossibleTimeslotMain(group, group.getCount_of_students(),
                                    mainSubject.getSubjectSet().getProfessor());
                            if (auditoryResource != null) {
                                Exam exam = Exam.builder()
                                        .mainSubject(mainSubject)
                                        .professor(mainSubject.getSubjectSet().getProfessor())
                                        .timeslot(auditoryResource.getTimeslot())
                                        .auditory(auditoryResource.getAuditory())
                                        .build();
                                examService.save(exam);
                                countExam--;
                            }
                        }
                    }
                }
            }
        }
    }

    // колво экзаменов для группы
    private int getExamCount(Group group) {
        List<MainSubject> mainSubjects = mainSubjectService.getAllByGroupId(group.getId());
        List<OptionalSubject> optionalSubjects = optionalSubjectService.getOptionalSubjectByCourseId(group.getGroupSet().getCourse().getId());
        return mainSubjects.size() + optionalSubjects.size();
    }

    // сколько экзаменов уже поставили в расписание
    private int getAddedExams(Group group) {
        List<Exam> exams = examService.getExamsByGroup(group);
        return exams.size();
    }

    // стоит ли этот предмет по выбору в расписании или нет
    // тк на весь курс только один в расписании
    private boolean isOptionalSubjectAdded(OptionalSubject optionalSubject) {
        List<Exam> exams = examService.getExams();
        for (Exam exam : exams) {
            if (exam.getOptionalSubject() != null) {
                if (exam.getOptionalSubject().equals(optionalSubject)) {
                    return true;
                }
            }

        }
        return false;
    }

    // ячейка аудитория + временной отрезок для предмета по выбору
    private AuditoryResource getPossibleTimeSlotForOptional(Group group, OptionalSubject optionalSubject) {
        // список временных ячеек, когда может профессор
        List<ProfessorResource> professorResources = professorResourceService.getProfessorResourcesByProfessorId(optionalSubject.getProfessor().getId());
        // достали все свободные временные ячейки
        List<AuditoryResource> auditoryResources = auditoryResourceService.getAuditoryResoucres();
        // достали все уже раставленные в расписании экзамены для группы
        List<Exam> exams = examService.getExamsByGroup(group);
        // если еще ни один экзамен не расставлен
        if (exams.isEmpty()) {
            // бежим по всем временным ячейкам профессора
            for (ProfessorResource professorResource : professorResources) {
                // бежим по всем ячейкам расписания
                for (AuditoryResource auditoryResource : auditoryResources) {
                    // если ячейка свободна и препод может в это время и вместительность аудитории позволяет
                    if (auditoryResource.getTimeslot().equals(professorResource.getTimeslot()) &&
                            auditoryResource.getAuditory().getCapasity() >= optionalSubject.getCount_of_students()) {
                        // в бд есть аудитории 1310, 1311 и аудитория 131(обозначает объединение аудиторий 1310 и 1311)
                        // поэтому когда нам нужно совмещенные аудитории 1310 и 1311 мы должны удалить из бд ячейки расписания,
                        // где эти аудитории используются поотдельности
                        if (auditoryResource.getAuditory().getNumber() == 131) {
                            auditoryResourceService.deleteByAuditoryAndTimeslot(10L, auditoryResource.getTimeslot().getId());
                            auditoryResourceService.deleteByAuditoryAndTimeslot(11L, auditoryResource.getTimeslot().getId());
                        }
                        // удаляем из бд
                        auditoryResourceService.delete(auditoryResource.getId());
                        professorResourceService.delete(professorResource);
                        return auditoryResource;
                    }
                }
            }
        } else {
            // если в расписании экзаменов у группы уже стоять какие-то экзамены
            TimeslotDay timeslotDay;
            // если последний экзамен в расписании и экзамен который мы сейчас ставим в расписание из одной и той же группы предметов по выбору
            if (exams.get(exams.size() - 1).getOptionalSubject().getSet() == optionalSubject.getSet()) {
                // то мы должны поставить их в один день
                timeslotDay = exams.get(exams.size() - 1).getTimeslot().getTimeslotDay();
            } else {
                // если экзамены в разных группах предметов по выбору, то мы достаем такой день, чтобы промежуток между экзаменами был
                // не меньше трех дней и день не был выходным
                timeslotDay = timeslotDayService.getOk(exams.get(exams.size() - 1).getTimeslot().getTimeslotDay());
            }
            for (ProfessorResource professorResource : professorResources) {
                for (AuditoryResource auditoryResource : auditoryResources) {
                    // требования остались такими же, но + теперь нам нужна ячейка только в конкретный день
                    if (auditoryResource.getTimeslot().getTimeslotDay().equals(timeslotDay) &&
                            auditoryResource.getTimeslot().equals(professorResource.getTimeslot()) &&
                            auditoryResource.getAuditory().getCapasity() >= optionalSubject.getCount_of_students()) {
                        if (auditoryResource.getAuditory().getNumber() == 131) {
                            auditoryResourceService.deleteByAuditoryAndTimeslot(10L, auditoryResource.getTimeslot().getId());
                            auditoryResourceService.deleteByAuditoryAndTimeslot(11L, auditoryResource.getTimeslot().getId());
                        }
                        auditoryResourceService.delete(auditoryResource.getId());
                        professorResourceService.delete(professorResource);
                        return auditoryResource;
                    }
                }
            }
        }
        return null;
    }


    // достаем временной отрезок для экзамена по обязательному предмету
    private AuditoryResource getPossibleTimeslotMain(Group group, int countOfStudent, Professor professor) {
        // лист временных ячеек, когда может профессор
        List<ProfessorResource> professorResources = professorResourceService.getProfessorResourcesByProfessorId(professor.getId());
        // лист экзаменов у данной группы
        List<Exam> exams = examService.getExamsByGroup(group);
        // ячейки расписания
        List<AuditoryResource> auditoryResources = auditoryResourceService.getAuditoryResoucres();
        // если экзаменов еще нет в расписании
        if (exams.isEmpty()) {
            // бежим по всем временным ячейкам профессора
            for (ProfessorResource professorResource : professorResources) {
                // если препод уже ведет экзамен в это время
                if (map.containsKey(professorResource)) {
                    // то мы должны узнать колво всех учеников, чтобы они поместилиь в одну аудиторию
                    List<Exam> exams1 = examService.getExamsByProfessorResource(professorResource);
                    for (Exam exam : exams1) {
                        countOfStudent += exam.getMainSubject().getGroup().getCount_of_students();
                    }
                }
                // бежим по всем ячейкам расписания
                for (AuditoryResource auditoryResource : auditoryResources) {
                    // если ячейка свободна и препод может в это время и вместительность аудитории позволяет
                    if (auditoryResource.getTimeslot().equals(professorResource.getTimeslot()) &&
                            auditoryResource.getAuditory().getCapasity() >= countOfStudent) {
                        // если у препода еще нет экзаменов в это время
                        if (!map.containsKey(professorResource)) {
                            // то кладем в мапу
                            map.put(professorResource, 1);
                        } else {
                            // если уже есть, то увеличиваю количество
                            int i = map.get(professorResource);
                            map.put(professorResource, i + 1);
                            // достаю все экзамены у профессора в это время
                            List<Exam> exams1 = examService.getExamsByProfessorResource(professorResource);
                            for (Exam exam : exams1) {
                                // сохраняю обратно в бд ячейку расписания с маленькой аудиторией
                                auditoryResourceService.save(AuditoryResource.builder().auditory(exam.getAuditory()).timeslot(exam.getTimeslot()).build());
                                // и ставлю этому экзамену большую аудиторию
                                exam.setAuditory(auditoryResource.getAuditory());
                                exam.setTimeslot(auditoryResource.getTimeslot());
                                examService.update(exam);
                            }

                        }
                        // если у профессора колво экзаменов в одно время совпадает с ограничением по колву экзаменов в одно время
                        // то я удаляю из бд ячейку расписания, чтобы больше группы в это время не ставили
                        if (map.get(professorResource) == professor.getCount()) {
                            if (auditoryResource.getAuditory().getNumber() == 131) {
                                auditoryResourceService.deleteByAuditoryAndTimeslot(10L, auditoryResource.getTimeslot().getId());
                                auditoryResourceService.deleteByAuditoryAndTimeslot(11L, auditoryResource.getTimeslot().getId());
                            }
                            auditoryResourceService.delete(auditoryResource.getId());
                            professorResourceService.delete(professorResource);
                        }
                        return auditoryResource;
                    }
                }
            }
        } else {
            // если же у группы уже есть экзамены в расписании
            Timeslot last = exams.get(exams.size() - 1).getTimeslot(); // забрали дату последнего экзамена
            for (ProfessorResource professorResource : professorResources) {
                if (map.containsKey(professorResource)) {
                    List<Exam> exams1 = examService.getExamsByProfessorResource(professorResource);
                    for (Exam exam : exams1) {
                        countOfStudent += exam.getMainSubject().getGroup().getCount_of_students();
                    }
                }
                // здесь те же самые условия, только нужно выдержать 3 дня между экзаменами
                for (AuditoryResource auditoryResource : auditoryResources) {
                    if ((auditoryResource.getTimeslot().getTimeslotDay().getDate().compareTo(last.getTimeslotDay().getDate()) >= 4) &&
                            auditoryResource.getTimeslot().equals(professorResource.getTimeslot()) &&
                            auditoryResource.getAuditory().getCapasity() >= countOfStudent) {
                        if (!map.containsKey(professorResource)) {
                            map.put(professorResource, 1);
                            // если уже есть, то увеличиваю количество
                        } else {
                            int i = map.get(professorResource);
                            map.put(professorResource, i + 1);
                            List<Exam> exams1 = examService.getExamsByProfessorResource(professorResource);
                            for (Exam exam : exams1) {
                                auditoryResourceService.save(AuditoryResource.builder().auditory(exam.getAuditory()).timeslot(exam.getTimeslot()).build());
                                exam.setAuditory(auditoryResource.getAuditory());
                                exam.setTimeslot(auditoryResource.getTimeslot());
                                examService.update(exam);
                            }
                        }
                        if (map.get(professorResource) == professor.getCount()) {
                            if (auditoryResource.getAuditory().getNumber() == 131) {
                                auditoryResourceService.deleteByAuditoryAndTimeslot(10L, auditoryResource.getTimeslot().getId());
                                auditoryResourceService.deleteByAuditoryAndTimeslot(11L, auditoryResource.getTimeslot().getId());
                            }
                            auditoryResourceService.delete(auditoryResource.getId());
                            professorResourceService.delete(professorResource);
                        }
                        return auditoryResource;
                    }
                }
            }
        }
        return null;
    }
}
