package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Exam;
import ru.itis.schedule.repositories.ExamRepository;

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
}
