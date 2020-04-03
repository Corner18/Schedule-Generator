package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.schedule.models.Subject;
import ru.itis.schedule.services.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<?> addSubject(Subject subject){
        subjectService.save(subject);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects(){
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping("/course-id/{course}")
    public ResponseEntity<List<Subject>> getSubjectsByCourseId(
            @PathVariable("course") Long course){
        return ResponseEntity.ok(subjectService.getSubjectsByCourseId(course));
    }
}
