package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.MainSubject;
import ru.itis.schedule.services.MainSubjectService;

import java.util.List;

@RestController
@RequestMapping("/main-subjects")
public class MainSubjectController {

    @Autowired
    private MainSubjectService mainSubjectService;

    @PostMapping
    public ResponseEntity<?> addMainSubject(MainSubject mainSubject){
        mainSubjectService.save(mainSubject);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<MainSubject>> getMainSubject(){
        return new ResponseEntity<>(mainSubjectService.getMainSubjects(), HttpStatus.OK);
    }

    @GetMapping("/subject-set-id/{id}")
    public ResponseEntity<List<MainSubject>> getMainSubjectByGroup(@PathVariable("id") Long id){
        return new ResponseEntity<>(mainSubjectService.getMainSubjectBySubjectSetId(id), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MainSubject> getMainSubjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(mainSubjectService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<?> generate(){
        mainSubjectService.generate();
        return ResponseEntity.accepted().build();
    }
}
