package ru.itis.schedule.services;

import ru.itis.schedule.models.Holiday;

import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    List<LocalDate> getAll();
}
