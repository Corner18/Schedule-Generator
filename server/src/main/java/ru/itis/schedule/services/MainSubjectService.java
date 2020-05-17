package ru.itis.schedule.services;

import ru.itis.schedule.models.MainSubject;

import java.util.List;

public interface MainSubjectService {
    void save(MainSubject mainSubject);
    List<MainSubject> getMainSubjects();
    List<MainSubject> getMainSubjectBySubjectSetId(Long id);
    MainSubject getById(Long id);
    List<MainSubject> getAllByCourseId(Long courseId);
    List<MainSubject> getAllByGroupId(Long gtoupId);
    void generate();
}
