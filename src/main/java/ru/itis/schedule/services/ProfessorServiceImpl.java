package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Professor;
import ru.itis.schedule.repositories.ProfessorRepository;

import java.util.List;

@Component
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public void save(Professor professor) {
        professorRepository.save(professor);
    }

    @Override
    public List<Professor> getProfessors() {
        return professorRepository.findAll();
    }
}
