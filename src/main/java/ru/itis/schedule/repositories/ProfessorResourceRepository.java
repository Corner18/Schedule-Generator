package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.ProfessorResource;

import java.util.List;

public interface ProfessorResourceRepository extends JpaRepository<ProfessorResource, Long> {
    List<ProfessorResource> getAllByTimeslot_Id(Long timeslotId);
    List<ProfessorResource> getAllByProfessor_Id(Long professorId);
    ProfessorResource getByProfessor_IdAndTimeslot_Id(Long professorId, Long TimeslotId);
}
