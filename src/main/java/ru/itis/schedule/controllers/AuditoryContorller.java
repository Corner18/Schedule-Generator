package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.Auditory;
import ru.itis.schedule.services.AuditoryService;

import java.util.List;

@RestController
@RequestMapping("/auditories")
public class AuditoryContorller {

    @Autowired
    private AuditoryService auditoryService;

    @GetMapping
    public ResponseEntity<List<Auditory>> getAuditories(){
        return ResponseEntity.ok(auditoryService.getAuditories());
    }

    @PostMapping
    public ResponseEntity<?> addAuditory(Auditory auditory){
        auditoryService.add(auditory);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/id/{auditory}")
    public ResponseEntity<Auditory> getAuditoryById(
            @PathVariable("auditory") Long auditory){
        return ResponseEntity.ok(auditoryService.getById(auditory));
    }

}
