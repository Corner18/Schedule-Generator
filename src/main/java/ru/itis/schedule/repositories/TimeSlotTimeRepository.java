package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.TimeslotTime;

public interface TimeSlotTimeRepository extends JpaRepository<TimeslotTime, Long> {
}
