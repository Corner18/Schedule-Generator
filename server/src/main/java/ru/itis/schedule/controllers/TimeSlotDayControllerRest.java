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
@RequestMapping("api/timeslots-day")
public class TimeSlotDayControllerRest {

    @Autowired
    private TimeslotDayService timeslotDayService;

    @PostMapping
    public ResponseEntity<?> addTimeslotDay(TimeslotDay timeslotDay){
        timeslotDayService.save(timeslotDay);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/period")
    public ResponseEntity<?> setPeriod(@RequestBody TimeslotDayDto timeslotDayDto){
        timeslotDayService.setPeriod(timeslotDayDto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/period")
    public ResponseEntity<List<TimeslotDay>> getTimeSlots(){
        return new ResponseEntity<>(timeslotDayService.getTimeSlotDays(), HttpStatus.OK);
    }

}
