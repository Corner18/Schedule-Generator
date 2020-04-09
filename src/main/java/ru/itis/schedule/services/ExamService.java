package ru.itis.schedule.services;

import ru.itis.schedule.models.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getExams();
    void save(Exam exam);
   // List<Exam> getExamsByGroupId(Long id);
    List<Exam> getAllByMainSubject_Group_Id(Long groupId);
    List<Exam> getAllByOptionalSubject_Course_Id(Long courseId);
}
