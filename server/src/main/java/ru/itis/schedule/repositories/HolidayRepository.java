package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
