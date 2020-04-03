package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.TimeslotTime;
import ru.itis.schedule.repositories.TimeSlotTimeRepository;

import java.util.List;

@Component
public class TimeslotTimeServiceImpl implements TimeslotTimeService {

    @Autowired
    private TimeSlotTimeRepository timeSlotTimeRepository;

    @Override
    public List<TimeslotTime> getTimeslotTime() {
        return timeSlotTimeRepository.findAll();
    }

    @Override
    public TimeslotTime getByTimeslotTimeId(Long id) {
        return timeSlotTimeRepository.getOne(id);
    }
}
