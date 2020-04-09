package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Exam;
import ru.itis.schedule.models.Group;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    List<Exam> getAllByMainSubject_Group_Id(Long groupId);
    List<Exam> getAllByOptionalSubject_Course_Id(Long courseId);
}
