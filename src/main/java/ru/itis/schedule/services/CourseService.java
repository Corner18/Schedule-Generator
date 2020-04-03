package ru.itis.schedule.services;

import ru.itis.schedule.models.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCources();
    void save(Course course);
    Course getById(Long id);
}
