package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.schedule.services.GenerationService;

@RestController
@RequestMapping("/generate")
public class GenerateContoller {

    @Autowired
    private GenerationService generationService;

    @GetMapping
    public ResponseEntity<?> generateExams(){
        generationService.generate();
        return ResponseEntity.accepted().build();
    }

}
