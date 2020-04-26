package ru.itis.schedule.services;


import ru.itis.schedule.models.OptionalSubject;

import java.util.List;
import java.util.Map;

public interface OptionalSubjectService {
    void save(OptionalSubject optionalSubject);
    List<OptionalSubject> getOptionalSubject();
    List<OptionalSubject> getOptionalSubjectByCourseId(Long courseId);
    OptionalSubject getById(Long id);
    Map<Integer, List<OptionalSubject>> getMap(Long CourseId);
}
