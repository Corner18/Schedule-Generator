package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Auditory;

public interface AuditoryRepository extends JpaRepository<Auditory, Long> {
}
