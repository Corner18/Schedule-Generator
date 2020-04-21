package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Restriction;

public interface RestrictionRepository extends JpaRepository<Restriction, Long> {
}
