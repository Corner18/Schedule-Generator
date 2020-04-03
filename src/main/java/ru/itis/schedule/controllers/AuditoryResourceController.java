package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.AuditoryResource;
import ru.itis.schedule.services.AuditoryResourceService;

import java.util.List;

@RestController
@RequestMapping("auditories/resources")
public class AuditoryResourceController {

    @Autowired
    private AuditoryResourceService auditoryResourceService;

    @GetMapping
    public ResponseEntity<List<AuditoryResource>> getAuditoryResources(){
        return ResponseEntity.ok(auditoryResourceService.getAuditoryResoucres());
    }

    @PostMapping
    public ResponseEntity<?> addAuditoryResource(AuditoryResource auditoryResource){
        auditoryResourceService.save(auditoryResource);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/auditory-id/{auditory}")
    public ResponseEntity<List<AuditoryResource>> getAuditoryResourcesByAuditoryId(
            @PathVariable("auditory") Long auditory){
        return ResponseEntity.ok(auditoryResourceService.getAuditoryResourcesByAuditoryId(auditory));
    }

    @GetMapping("/timeslot-id/{timeslot}")
    public ResponseEntity<List<AuditoryResource>> getAuditoryResourcesByTimeslotId(
            @PathVariable("timeslot") Long timeslot){
        return ResponseEntity.ok(auditoryResourceService.getAuditoryResourcesByTimeSlotId(timeslot));
    }
}
