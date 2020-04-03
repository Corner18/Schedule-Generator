package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Exam;
import ru.itis.schedule.repositories.ExamRepository;

import java.util.List;

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
}
