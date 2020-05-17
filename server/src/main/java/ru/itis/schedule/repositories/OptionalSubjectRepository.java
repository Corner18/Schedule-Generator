package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.OptionalSubject;

import java.util.List;

public interface OptionalSubjectRepository extends JpaRepository<OptionalSubject, Long> {
    List<OptionalSubject> getAllByCourse_Id(Long courseId);
}
