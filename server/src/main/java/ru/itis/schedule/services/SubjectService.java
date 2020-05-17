package ru.itis.schedule.services;

import ru.itis.schedule.models.Subject;

import java.util.List;

public interface SubjectService {
    void save(Subject subject);
    List<Subject> getSubjects();
    List<Subject> getSubjectsByCourseId(Long courseId);
}
