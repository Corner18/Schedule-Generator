package ru.itis.schedule.services;

import ru.itis.schedule.models.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getExams();
    void save(Exam exam);
}
