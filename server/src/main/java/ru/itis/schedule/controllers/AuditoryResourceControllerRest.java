package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.AuditoryResource;
import ru.itis.schedule.services.AuditoryResourceService;

import java.util.List;

@RestController
@RequestMapping("api/auditories/resources")
public class AuditoryResourceControllerRest {

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
        return new ResponseEntity<>(auditoryResourceService.getAuditoryResourcesByAuditoryId(auditory), HttpStatus.OK);
    }

    @GetMapping("/timeslot-id/{timeslot}")
    public ResponseEntity<List<AuditoryResource>> getAuditoryResourcesByTimeslotId(
            @PathVariable("timeslot") Long timeslot){
        return new ResponseEntity<>(auditoryResourceService.getAuditoryResourcesByTimeSlotId(timeslot), HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<?> generate(){
        auditoryResourceService.generate();
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuditory(@PathVariable("id") Long id){
        auditoryResourceService.delete(id);
        return ResponseEntity.accepted().build();
    }


}
