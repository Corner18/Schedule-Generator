package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.OptionalSubject;
import ru.itis.schedule.services.OptionalSubjectService;

import java.util.List;

@RestController
@RequestMapping("/optional-subjects")
public class OptionalSubjectControllerRest {

    @Autowired
    private OptionalSubjectService optionalSubjectService;

    @PostMapping
    public ResponseEntity<?> addOptionalSubject(OptionalSubject optionalSubject){
        optionalSubjectService.save(optionalSubject);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<OptionalSubject>> getOptionalSubjects(){
        return new ResponseEntity<>(optionalSubjectService.getOptionalSubject(), HttpStatus.OK);
    }

    @GetMapping("/course-id/{course}")
    public ResponseEntity<List<OptionalSubject>> getOptionalSubjectsByCourseId(@PathVariable("course") Long course){
        return new ResponseEntity<>(optionalSubjectService.getOptionalSubjectByCourseId(course), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OptionalSubject> getOptionalSubjectsByIf(@PathVariable("id") Long id){
        return new ResponseEntity<>(optionalSubjectService.getById(id), HttpStatus.OK);
    }
}
