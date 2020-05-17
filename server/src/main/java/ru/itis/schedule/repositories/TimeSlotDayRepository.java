package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.TimeslotDay;

public interface TimeSlotDayRepository extends JpaRepository<TimeslotDay, Long> {
}
