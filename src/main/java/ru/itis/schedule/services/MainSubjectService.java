package ru.itis.schedule.services;

import ru.itis.schedule.models.MainSubject;

import java.util.List;

public interface MainSubjectService {
    void save(MainSubject mainSubject);
    List<MainSubject> getMainSubjects();
    List<MainSubject> getMainSubjectByGroupId(Long groupId);
    MainSubject getById(Long id);
}
