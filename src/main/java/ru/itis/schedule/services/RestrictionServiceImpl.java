package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.*;
import ru.itis.schedule.repositories.RestrictionRepository;

import java.time.LocalDate;
import java.util.*;

@Component
public class RestrictionServiceImpl implements RestrictionService {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ProfessorResourceService professorResourceService;

    @Autowired
    private RestrictionRepository restrictionRepository;

    //TODO: методам плохо, так не пойдет, переделай обязательно

    @Override
    public boolean isLimitedExamInAuditory(List<Exam> exams) {

        Map<AuditoryResource, Integer> map1 = new HashMap<>(); //колво экзаменов в аудитории
        Map<AuditoryResource, Professor> map2 = new HashMap<>(); // приклеиваем каждой ячейке расписания своего профессора
        AuditoryResource auditoryResource;
        for (Exam exam : exams) {
            auditoryResource = AuditoryResource.builder()
                    .timeslot(exam.getTimeslot())
                    .auditory(exam.getAuditory())
                    .build();
            if (!map2.containsKey(auditoryResource)) { // если в этой ячейке расписания еще нет профессора
                map2.put(auditoryResource, exam.getProfessor()); // кладем в ячейку профессора
                map1.put(auditoryResource, 1); // говорим что пока в эту ячейку раписания проводится только один экзамен
            } else { // если уже был экзамен в это же время в этой же аудитории
                if (exam.getProfessor().equals(map2.get(auditoryResource))) { // проверяем с этим ли преподом был экзамен
                    map1.put(auditoryResource, map1.get(auditoryResource) + 1); // если с ним, то уведичиваем колво экзаменов в этой ячейке
                } else {
                    return false; //если не выполнился предыдущий иф, значит в одной ячейке расписания экзамен у рахных преподов. Это плохо
                }
            }
            if (map1.get(auditoryResource) > exam.getProfessor().getCount())
                return false; // если колво экзаменов у одного препода в одну временную ячейку больше, чем хотел препод, возвращаем фолс
        }
        return true;
    }

    @Override
    public boolean isLimitedExamsAtProfessor(List<Exam> exams) {
        // создаю мапу профессор в ячейку времени - количество таких ячеек
        Map<ProfessorResource, Integer> map = new HashMap<>();
        ProfessorResource professorResource;
        for (Exam exam : exams) {
            // бегу по экзаменам, создаю профессор в ячейку времени
            professorResource = ProfessorResource.builder()
                    .timeslot(exam.getTimeslot())
                    .professor(exam.getProfessor())
                    .build();
            // если в мапе еще нет таких ячеек, то создаю
            if (!map.containsKey(professorResource))
                map.put(professorResource, 1);
                // если уже есть, то увеличиваю количество
            else {
                int i = map.get(professorResource);
                map.put(professorResource, i + 1);
            }
            // если оказывается, что количество ячеек привысило пожелания профессора, то фолс
            if (map.get(professorResource) > exam.getProfessor().getCount())
                return false;
        }
        return true;
    }

    @Override
    public boolean isGapBetweenExams(List<Exam> exams) {
        //метод возращает мапу группа-даты экзаменов
        Map<Group, List<TimeslotDay>> map = getMapGroupExam(exams);
        // для каждой группы проверяется расстояние между экзаменами
        for (Group group : map.keySet()) {
            if (!checkGaps(map.get(group), 3)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isAuditoryCapasityBigerStidentCount(List<Exam> exams) {
        // тут все просто
        for (Exam exam : exams) {
            if (exam.getOptionalSubject() != null) {
                if (exam.getOptionalSubject().getCount_of_students() > exam.getAuditory().getCapasity()) {
                    return false;
                }
            } else {
                if (exam.getMainSubject().getGroup().getCount_of_students() > exam.getAuditory().getCapasity()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isProfessorsCan(List<Exam> exams) {
        for (Exam exam : exams) {
            if(exam.getMainSubject() != null){ // только у преподов обязательных предметов
                if (professorResourceService.getByProfessorIdAndTimeslotId(
                        exam.getTimeslot().getId(), exam.getProfessor().getId()) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isOptionalSubjectsInOneDay(List<Exam> exams) {
        // получаем мапу курс - временные ячейки экзаменов по предеметам по выбору
        for (Exam exam : exams) {
            Map<Course, List<TimeslotDay>> map = getMapCourse(exams);
            for (Course course : map.keySet()) {
                // для каждого курса чекаем чтобы промежуток между экзаменами по выбору был 0 дней
                if (!checkGaps(map.get(course), 0)) {
                    return false;
                }
            }
        }
        return true;
    }


    private Map<Group, List<TimeslotDay>> getMapGroupExam(List<Exam> exams) {
        Map<Group, List<TimeslotDay>> map = new HashMap<>();
        for (Exam exam : exams) {
            if (exam.getMainSubject() != null) {
                // если это экзамен по обязательному предмету
                if (!map.containsKey(exam.getMainSubject().getGroup())) {
                    // если мапы для такой группы еще не сущетсвуте
                    List<TimeslotDay> timeslotDays = new ArrayList<>();
                    timeslotDays.add(exam.getTimeslot().getTimeslotDay());
                    // положили группу - лист экзаменов
                    map.put(exam.getMainSubject().getGroup(), timeslotDays);
                } else {
                    //если такая мапа есть то просто кладем в лист еще одну временную ячейку
                    List<TimeslotDay> timeslotDays = map.get(exam.getMainSubject().getGroup());
                    timeslotDays.add(exam.getTimeslot().getTimeslotDay());
                    map.put(exam.getMainSubject().getGroup(), timeslotDays);
                }
            }
        }

        for (Exam exam : exams) {
            if (exam.getOptionalSubject() != null) {
                // если это предмет по выбору, то достаем все группы этого курса( курс типа первый, второй и тд)
                List<Group> groups = groupService.getAllByCourseId(exam.getOptionalSubject().getCourse().getId());
                for (Group group : groups) {
                    // то есть если такой экзамен есть, то у всех групп на этом курсе он есть
                    if (!map.containsKey(group)) {
                        List<TimeslotDay> timeslotDays = new ArrayList<>();
                        timeslotDays.add(exam.getTimeslot().getTimeslotDay());
                        map.put(group, timeslotDays);
                    } else {
                        List<TimeslotDay> timeslotDays = map.get(group);
                        timeslotDays.add(exam.getTimeslot().getTimeslotDay());
                        map.put(group, timeslotDays);
                    }
                }
                break;
            }

        }
        return map;
    }

    private boolean checkGaps(List<TimeslotDay> days, int gap) {
        for (int i = 0; i < days.size() - 1; i++) {
            if (days.get(i + 1).getDate().compareTo(days.get(i).getDate()) < (gap + 1))
                return false;
        }
        return true;
    }

    private Map<Course, List<TimeslotDay>> getMapCourse(List<Exam> exams) {
        Map<Course, List<TimeslotDay>> map = new HashMap<>();
        for (Exam exam : exams) {
            if (exam.getOptionalSubject() != null) {
                if (!map.containsKey(exam.getOptionalSubject().getCourse())) {
                    List<TimeslotDay> days = new ArrayList<>();
                    days.add(exam.getTimeslot().getTimeslotDay());
                    map.put(exam.getOptionalSubject().getCourse(), days);
                } else {
                    List<TimeslotDay> days = map.get(exam.getOptionalSubject().getCourse());
                    days.add(exam.getTimeslot().getTimeslotDay());
                    map.put(exam.getOptionalSubject().getCourse(), days);
                }
            }
        }
        return map;
    }

    @Override
    public void fillRestrictionTable(List<Exam> exams) {
        List<Restriction> restrictions = restrictionRepository.findAll();
        for (Restriction restriction : restrictions) {
            if (restriction.getName().equals("isOneExamInAuditory")) {
                restriction.setEnabled(isLimitedExamInAuditory(exams));
                restrictionRepository.save(restriction);
            }
            if (restriction.getName().equals("isLimitedExamsAtProfessor")) {
                restriction.setEnabled(isLimitedExamsAtProfessor(exams));
                restrictionRepository.save(restriction);
            }
            if (restriction.getName().equals("isGapBetweenExams")) {
                restriction.setEnabled(isGapBetweenExams(exams));
                restrictionRepository.save(restriction);
            }
            if (restriction.getName().equals("isAuditoryCapasityBigerStidentCount")) {
                restriction.setEnabled(isAuditoryCapasityBigerStidentCount(exams));
                restrictionRepository.save(restriction);
            }
            if (restriction.getName().equals("isProfessorsCan")) {
                restriction.setEnabled(isProfessorsCan(exams));
                restrictionRepository.save(restriction);
            }
            if (restriction.getName().equals("isOptionalSubjectsInOneDay")) {
                restriction.setEnabled(isOptionalSubjectsInOneDay(exams));
                restrictionRepository.save(restriction);
            }
        }
    }
}
