package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.dto.TimeslotDayDto;
import ru.itis.schedule.models.TimeslotDay;
import ru.itis.schedule.repositories.TimeSlotDayRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Component
public class TimeslotDayServiceImpl implements TimeslotDayService {

    @Autowired
    private TimeSlotDayRepository timeSlotDayRepository;

    @Override
    public void save(TimeslotDay timeslotDay) {
        timeSlotDayRepository.save(timeslotDay);
    }

    @Override
    public List<TimeslotDay> getTimeSlotDays() {
        return timeSlotDayRepository.findAll();
    }

    @Override
    public void setPeriod(TimeslotDayDto timeslotDayDto) {
        TimeslotDay timeslotDay;
        LocalDate begin = timeslotDayDto.getBegin();
        LocalDate end = timeslotDayDto.getEnd();
        while (!(begin.equals(end))) {
            timeslotDay = TimeslotDay.builder()
                    .date(begin)
                    .is_workday(!(begin.getDayOfWeek().equals(DayOfWeek.SUNDAY)))
                    .build();
            timeSlotDayRepository.save(timeslotDay);
            begin = begin.plusDays(1);
        }
    }
}
