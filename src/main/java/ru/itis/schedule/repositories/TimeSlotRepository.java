package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Timeslot;

import java.util.List;

public interface TimeSlotRepository extends JpaRepository<Timeslot, Long> {
}
