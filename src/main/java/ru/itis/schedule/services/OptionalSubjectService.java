package ru.itis.schedule.services;


import ru.itis.schedule.models.OptionalSubject;

import java.util.List;

public interface OptionalSubjectService {
    void save(OptionalSubject optionalSubject);
    List<OptionalSubject> getOptionalSubject();
    List<OptionalSubject> getOptionalSubjectByCourseId(Long courseId);
    OptionalSubject getById(Long id);
}
