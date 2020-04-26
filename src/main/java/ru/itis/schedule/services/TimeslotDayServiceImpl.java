package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.dto.TimeslotDayDto;
import ru.itis.schedule.models.Holiday;
import ru.itis.schedule.models.TimeslotDay;
import ru.itis.schedule.repositories.TimeSlotDayRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Component
public class TimeslotDayServiceImpl implements TimeslotDayService {

    @Autowired
    private HolidayService holidayService;

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
        List<LocalDate> holidays = holidayService.getAll();
        TimeslotDay timeslotDay;
        LocalDate begin = LocalDate.parse(timeslotDayDto.getBegin());
        LocalDate end = LocalDate.parse(timeslotDayDto.getEnd());
        while (!(begin.equals(end))) {
            timeslotDay = TimeslotDay.builder()
                    .date(begin)
                    .is_workday(  !  ((begin.getDayOfWeek().equals(DayOfWeek.SUNDAY)) || (holidays.contains(begin)) ) )
                    .build();
            timeSlotDayRepository.save(timeslotDay);
            begin = begin.plusDays(1);
        }
    }

    @Override
    public TimeslotDay getOk(TimeslotDay timeslotDay) {
        List<TimeslotDay> timeslotDays = timeSlotDayRepository.findAll();
        for (TimeslotDay timeslotDay1 : timeslotDays){
            if( (timeslotDay1.getDate().compareTo(timeslotDay.getDate()) >= 4) && (timeslotDay1.is_workday())){
                return timeslotDay1;
            }
        } return null;
    }
}
