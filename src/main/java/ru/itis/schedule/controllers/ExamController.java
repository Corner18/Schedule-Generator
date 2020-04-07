package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.schedule.models.Exam;
import ru.itis.schedule.services.ExamService;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List<Exam>> getExams(){
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addExam(Exam exam){
        examService.save(exam);
        return ResponseEntity.accepted().build();
    }
}
