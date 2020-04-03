package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.ProfessorResource;
import ru.itis.schedule.repositories.ProfessorRepository;
import ru.itis.schedule.repositories.ProfessorResourceRepository;

import java.util.List;

@Component
public class ProfessorResourceServiceImpl implements ProfessorResourceService {

    @Autowired
    private ProfessorResourceRepository professorResourceRepository;
    @Override
    public void save(ProfessorResource professorResource) {
        professorResourceRepository.save(professorResource);
    }

    @Override
    public List<ProfessorResource> getProfessorResources() {
        return professorResourceRepository.findAll();
    }

    @Override
    public List<ProfessorResource> getProfessorResourcesByTimeslotId(Long timeslotId) {
        return professorResourceRepository.getAllByTimeslot_Id(timeslotId);
    }

    @Override
    public List<ProfessorResource> getProfessorResourcesByProfessorId(Long professorId) {
        return professorResourceRepository.getAllByProfessor_Id(professorId);
    }
}
