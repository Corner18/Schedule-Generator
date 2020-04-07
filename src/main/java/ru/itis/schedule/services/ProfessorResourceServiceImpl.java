package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Professor;
import ru.itis.schedule.models.ProfessorResource;
import ru.itis.schedule.models.Timeslot;
import ru.itis.schedule.repositories.ProfessorRepository;
import ru.itis.schedule.repositories.ProfessorResourceRepository;

import java.util.List;

@Component
public class ProfessorResourceServiceImpl implements ProfessorResourceService {

    @Autowired
    private ProfessorResourceRepository professorResourceRepository;

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private ProfessorService professorService;

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

    @Override
    public void generate() {
        List<Timeslot> timeslots = timeslotService.getTimeSlots();
        List<Professor> professors = professorService.getProfessors();
        for (Timeslot timeslot : timeslots){
            for (Professor professor : professors){
                ProfessorResource professorResource = ProfessorResource.builder()
                        .professor(professor)
                        .timeslot(timeslot)
                        .count(1)
                        .build();
                professorResourceRepository.save(professorResource);
            }
        }
    }
}
