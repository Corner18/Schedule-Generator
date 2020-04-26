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
    private CourseService courseService;

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

    private static Map<ProfessorResource, Integer> map = new HashMap<>();

    @Override
    public void generate() {
        // достали все группы
        List<Group> groups = groupService.getGroups();
        // выполняем цикл по всем группам
        for (Group group : groups) {
            // для каждой группы посчитаем количество экзаменов
            int countExam = getExamCount(group);
            // если все экзамены расставлены, то выходим из цикла
            if (getAddedExams(group) < countExam) {

                Map<Integer, List<OptionalSubject>> optionalSubjectMap = optionalSubjectService.getMap(group.getGroupSet().getCourse().getId());
                //     // получили список всех предметов по выбору
                //     List<OptionalSubject> optionalSubjects = optionalSubjectService.getOptionalSubjectByCourseId(
                //            group.getGroupSet().getCourse().getId());
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

                Set<MainSubject> set = new HashSet<>();
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


                // потом все основные предметы
            }
        }
    }


    private int getExamCount(Group group) {
        List<MainSubject> mainSubjects = mainSubjectService.getAllByGroupId(group.getId());
        List<OptionalSubject> optionalSubjects = optionalSubjectService.getOptionalSubjectByCourseId(group.getGroupSet().getCourse().getId());
        return mainSubjects.size() + optionalSubjects.size();
    }

    private int getAddedExams(Group group) {
        List<Exam> exams = examService.getExamsByGroup(group);
        return exams.size();
    }

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

    private AuditoryResource getPossibleTimeSlotForOptional(Group group, OptionalSubject optionalSubject) {
        List<ProfessorResource> professorResources = professorResourceService.getProfessorResourcesByProfessorId(optionalSubject.getProfessor().getId());
        List<AuditoryResource> auditoryResources = auditoryResourceService.getAuditoryResoucres();
        List<Exam> exams = examService.getExamsByGroup(group);
        if (exams.isEmpty()) {
            // бежим по всем временным ячейкам профессора
            for (ProfessorResource professorResource : professorResources) {
                // бежим по всем ячейкам расписания
                for (AuditoryResource auditoryResource : auditoryResources) {
                    // если ячейка свободна и препод может в это время и вместительность аудитории позволяет
                    if (auditoryResource.getTimeslot().equals(professorResource.getTimeslot()) &&
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
        } else {
            TimeslotDay timeslotDay;
            if (exams.get(exams.size() - 1).getOptionalSubject().getSet() == optionalSubject.getSet()) {
                timeslotDay = exams.get(exams.size() - 1).getTimeslot().getTimeslotDay();
            } else {
                timeslotDay = timeslotDayService.getOk(exams.get(exams.size() - 1).getTimeslot().getTimeslotDay());
            }
            for (ProfessorResource professorResource : professorResources) {
                for (AuditoryResource auditoryResource : auditoryResources) {
                    // временная ячейка только в конкретный день
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


    private AuditoryResource getPossibleTimeslotMain(Group group, int countOfStudent, Professor professor) {
        // лист временных ячеек, когда может профессор
        List<ProfessorResource> professorResources = professorResourceService.getProfessorResourcesByProfessorId(professor.getId());
        // лист экзаменов у данной группы
        List<Exam> exams = examService.getExamsByGroup(group);
        // ячейки расписания
        List<AuditoryResource> auditoryResources = auditoryResourceService.getAuditoryResoucres();
        if (exams.isEmpty()) {
            // бежим по всем временным ячейкам профессора
            for (ProfessorResource professorResource : professorResources) {
                if (map.containsKey(professorResource)) {
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
        } else {
            Timeslot last = exams.get(exams.size() - 1).getTimeslot(); // забрали дату последнего экзамена
            for (ProfessorResource professorResource : professorResources) {
                if (map.containsKey(professorResource)) {
                    List<Exam> exams1 = examService.getExamsByProfessorResource(professorResource);
                    for (Exam exam : exams1) {
                        countOfStudent += exam.getMainSubject().getGroup().getCount_of_students();
                    }
                }
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
