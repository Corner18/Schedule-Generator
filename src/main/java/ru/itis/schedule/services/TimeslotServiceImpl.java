package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Timeslot;
import ru.itis.schedule.repositories.TimeSlotRepository;

import java.util.List;

@Component
public class TimeslotServiceImpl implements TimeslotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public void save(Timeslot timeslot) {
        timeSlotRepository.save(timeslot);
    }

    @Override
    public List<Timeslot> getTimeSlots() {
        return timeSlotRepository.findAll();
    }

    @Override
    public Timeslot getTimeSlotById(Long id) {
        return timeSlotRepository.getOne(id);
    }
}
