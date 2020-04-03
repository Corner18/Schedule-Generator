package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.ProfessorResource;
import ru.itis.schedule.services.ProfessorResourceService;

import java.util.List;

@RestController
@RequestMapping("/professors/resources")
public class ProfessorResourceContoller {

    @Autowired
    private ProfessorResourceService professorResourceService;

    @PostMapping
    public ResponseEntity<?> addProfessorResource(ProfessorResource professorResource){
        professorResourceService.save(professorResource);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResource>> getProfessorResources(){
        return ResponseEntity.ok(professorResourceService.getProfessorResources());
    }

    @GetMapping("/professor-id/{professor}")
    public ResponseEntity<List<ProfessorResource>> getProfessorResourcesByProfessorId(
            @PathVariable("professor") Long professor){
        return ResponseEntity.ok(professorResourceService.getProfessorResourcesByProfessorId(professor));
    }

    @GetMapping("/timeslot-id/{timeslot}")
    public ResponseEntity<List<ProfessorResource>> getProfessorResourcesByTimeslotId(
            @PathVariable("timeslot") Long timeslot){
        return ResponseEntity.ok(professorResourceService.getProfessorResourcesByTimeslotId(timeslot));
    }
}
