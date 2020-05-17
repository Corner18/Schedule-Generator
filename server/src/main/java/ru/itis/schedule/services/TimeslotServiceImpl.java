package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Timeslot;
import ru.itis.schedule.models.TimeslotDay;
import ru.itis.schedule.models.TimeslotTime;
import ru.itis.schedule.repositories.TimeSlotRepository;

import java.util.List;

@Component
public class TimeslotServiceImpl implements TimeslotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private TimeslotDayService timeslotDayService;

    @Autowired
    private TimeslotTimeService timeslotTimeService;

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

    @Override
    public void generateTimeSlots() {
        List<TimeslotDay> dayList = timeslotDayService.getTimeSlotDays();
        List<TimeslotTime> timeList = timeslotTimeService.getTimeslotTime();
        for (TimeslotDay timeslotDay : dayList) {
            if (timeslotDay.is_workday()) {
                for (TimeslotTime timeslotTime : timeList) {
                    Timeslot timeslot = Timeslot.builder()
                            .timeslotDay(timeslotDay)
                            .timeslotTime(timeslotTime)
                            .build();
                    timeSlotRepository.save(timeslot);
                }
            }
        }
    }
}
