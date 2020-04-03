package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.AuditoryResource;

import java.util.List;

public interface AuditoryResourceRepository extends JpaRepository<AuditoryResource, Long> {
    List<AuditoryResource> getAllByAuditory_Id(Long auditoryId);
    List<AuditoryResource> getAllByTimeslot_Id(Long timeslotId);
}
