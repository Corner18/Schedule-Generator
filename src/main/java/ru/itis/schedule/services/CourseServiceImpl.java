package ru.itis.schedule.services;

import com.sun.xml.internal.rngom.digested.DListPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Course;
import ru.itis.schedule.repositories.CourseRepository;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCources() {
        List<Course> list = courseRepository.findAll();
        return list;
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.getOne(id);
    }
}
