package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.TimeslotTime;
import ru.itis.schedule.services.TimeslotTimeService;

import java.util.List;

@RestController
@RequestMapping("/timeslots-time")
public class TimeSlotTimeController {

    @Autowired
    private TimeslotTimeService timeslotTimeService;

    @GetMapping
    public ResponseEntity<List<TimeslotTime>> getTimeSlots(){
        return ResponseEntity.ok(timeslotTimeService.getTimeslotTime());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TimeslotTime> getTimeSlotById(@PathVariable("id") Long id){
        return ResponseEntity.ok(timeslotTimeService.getByTimeslotTimeId(id));
    }
}
