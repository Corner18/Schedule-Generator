package ru.itis.schedule.services;

import ru.itis.schedule.models.TimeslotTime;

import java.util.List;

public interface TimeslotTimeService {
    List<TimeslotTime> getTimeslotTime();
    TimeslotTime getByTimeslotTimeId(Long id);
}
