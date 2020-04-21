package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.dto.ExamDto;
import ru.itis.schedule.models.Exam;
import ru.itis.schedule.models.Group;
import ru.itis.schedule.repositories.ExamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> getExams() {
        List<Exam> list = examRepository.findAll();
        return list;
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }


    @Override
    public List<Exam> getAllByMainSubject_Group_Id(Long groupId) {
        return examRepository.getAllByMainSubject_Group_Id(groupId);
    }

    @Override
    public List<Exam> getAllByOptionalSubject_Course_Id(Long courseId) {
        return examRepository.getAllByOptionalSubject_Course_Id(courseId);
    }

    @Override
    public List<Exam> getExamsByGroup(Group group) {
        List<Exam> mainExams = examRepository.getAllByMainSubject_Group_Id(group.getId());
        List<Exam> optioanlExams = examRepository.getAllByOptionalSubject_Course_Id(group.getGroupSet().getCourse().getId());
        List<Exam> allExams = new ArrayList<>();
        allExams.addAll(optioanlExams);
        allExams.addAll(mainExams);
        return allExams;
    }

    @Override
    public List<ExamDto> getExamsDto() {
        List<Exam> exams = examRepository.findAll();
        return ExamDto.from(exams);
    }
}
