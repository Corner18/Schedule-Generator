package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
