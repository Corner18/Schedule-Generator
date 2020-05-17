package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.TimeslotTime;
import ru.itis.schedule.services.TimeslotTimeService;

import java.util.List;

@RestController
@RequestMapping("/timeslots-time")
public class TimeSlotTimeControllerRest {

    @Autowired
    private TimeslotTimeService timeslotTimeService;

    @GetMapping
    public ResponseEntity<List<TimeslotTime>> getTimeSlots(){
        return new ResponseEntity<>(timeslotTimeService.getTimeslotTime(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TimeslotTime> getTimeSlotById(@PathVariable("id") Long id){
        return new ResponseEntity<>(timeslotTimeService.getByTimeslotTimeId(id), HttpStatus.OK);
    }
}
