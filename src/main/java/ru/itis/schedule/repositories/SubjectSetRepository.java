package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.SubjectSet;

public interface SubjectSetRepository extends JpaRepository<SubjectSet, Long> {
}
