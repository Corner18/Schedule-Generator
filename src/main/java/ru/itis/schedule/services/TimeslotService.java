package ru.itis.schedule.services;

import ru.itis.schedule.models.Timeslot;

import java.util.List;

public interface TimeslotService {
    void save(Timeslot timeslot);
    List<Timeslot> getTimeSlots();
    Timeslot getTimeSlotById(Long id);
}
