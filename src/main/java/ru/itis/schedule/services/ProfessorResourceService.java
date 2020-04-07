package ru.itis.schedule.services;

import ru.itis.schedule.models.ProfessorResource;

import java.util.List;

public interface ProfessorResourceService {
    void save(ProfessorResource professorResource);
    List<ProfessorResource> getProfessorResources();
    List<ProfessorResource> getProfessorResourcesByTimeslotId(Long timeslotId);
    List<ProfessorResource> getProfessorResourcesByProfessorId(Long professorId);
    void generate();
}
