package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Holiday;
import ru.itis.schedule.repositories.HolidayRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class HolidayServiceImpl implements HolidayService {
    @Autowired
    private HolidayRepository holidayRepository;

    @Override
    public List<LocalDate> getAll() {
        List<Holiday> holidays = holidayRepository.findAll();
        List<LocalDate> dates = new ArrayList<>();
        for (Holiday holiday : holidays){
            dates.add(holiday.getDate());
        }
        return dates;
    }
}
