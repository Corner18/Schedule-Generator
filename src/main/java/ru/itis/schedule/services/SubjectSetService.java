package ru.itis.schedule.services;

import ru.itis.schedule.models.SubjectSet;

import java.util.List;

public interface SubjectSetService {
    List<SubjectSet> getSubjectSets();
    void save(SubjectSet subjectSet);
}
