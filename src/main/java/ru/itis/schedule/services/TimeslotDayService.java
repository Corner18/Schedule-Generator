package ru.itis.schedule.services;

import ru.itis.schedule.dto.TimeslotDayDto;
import ru.itis.schedule.models.TimeslotDay;

import java.time.LocalDate;
import java.util.List;

public interface TimeslotDayService {
    void save(TimeslotDay timeslotDay);
    List<TimeslotDay> getTimeSlotDays();
    void setPeriod(TimeslotDayDto timeslotDayDto);
}
