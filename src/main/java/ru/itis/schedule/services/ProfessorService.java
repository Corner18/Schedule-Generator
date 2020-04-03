package ru.itis.schedule.services;

import ru.itis.schedule.models.Professor;

import java.util.List;

public interface ProfessorService {
    void save(Professor professor);
    List<Professor> getProfessors();
}
