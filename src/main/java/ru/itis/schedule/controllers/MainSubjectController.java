package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(mainSubjectService.getMainSubjects());
    }

    @GetMapping("/group-id/{group}")
    public ResponseEntity<List<MainSubject>> getMainSubjectByGroup(@PathVariable("group") Long group){
        return ResponseEntity.ok(mainSubjectService.getMainSubjectByGroupId(group));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MainSubject> getMainSubjectById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mainSubjectService.getById(id));
    }
}
