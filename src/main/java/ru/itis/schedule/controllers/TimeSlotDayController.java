package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.dto.TimeslotDayDto;
import ru.itis.schedule.models.TimeslotDay;
import ru.itis.schedule.services.TimeslotDayService;

import java.util.List;

@RestController
@RequestMapping("/timeslots-day")
public class TimeSlotDayController {

    @Autowired
    private TimeslotDayService timeslotDayService;

    @PostMapping
    public ResponseEntity<?> addTimeslotDay(TimeslotDay timeslotDay){
        timeslotDayService.save(timeslotDay);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/set-period")
    public ResponseEntity<?> setPeriod(@RequestBody TimeslotDayDto timeslotDayDto){
        timeslotDayService.setPeriod(timeslotDayDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<TimeslotDay>> getTimeSlots(){
        return new ResponseEntity<>(timeslotDayService.getTimeSlotDays(), HttpStatus.OK);
    }
}
