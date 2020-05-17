package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.schedule.models.Course;
import ru.itis.schedule.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/cources")
public class CourseControllerRest {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getCources(){
        return new ResponseEntity<>(courseService.getCources(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(Course course){
        courseService.save(course);
        return ResponseEntity.accepted().build();
    }
}
