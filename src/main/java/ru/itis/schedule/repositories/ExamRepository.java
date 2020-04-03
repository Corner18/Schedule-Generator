package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
