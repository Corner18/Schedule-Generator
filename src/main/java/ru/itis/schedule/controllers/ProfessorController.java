package ru.itis.schedule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.schedule.models.Professor;
import ru.itis.schedule.services.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<?> addProfessor(Professor professor){
        professorService.save(professor);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getProfessors(Professor professor){
        return ResponseEntity.ok(professorService.getProfessors());
    }

}
