package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.Timeslot;
import ru.itis.schedule.services.TimeslotService;

import java.util.List;

@RestController
@RequestMapping("api/timeslots")
public class TimeSlotControllerRest {

    @Autowired
    private TimeslotService timeslotService;

    @PostMapping
    public ResponseEntity<?> addTimeslot(Timeslot timeslot){
        timeslotService.save(timeslot);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Timeslot>> getTimeSlots(){
        return new ResponseEntity<>(timeslotService.getTimeSlots(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Timeslot> getTimeSlotById(@PathVariable("id") Long id){
        return new ResponseEntity<>(timeslotService.getTimeSlotById(id), HttpStatus.OK);
    }

    @GetMapping("/generate")
    private ResponseEntity<?> generate(){
        timeslotService.generateTimeSlots();
        return ResponseEntity.accepted().build();
    }


}
