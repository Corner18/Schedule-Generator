package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
