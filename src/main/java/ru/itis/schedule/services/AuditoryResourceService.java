package ru.itis.schedule.services;

import ru.itis.schedule.models.AuditoryResource;

import java.util.List;

public interface AuditoryResourceService {
    List<AuditoryResource> getAuditoryResoucres();
    void save(AuditoryResource auditoryResource);
    List<AuditoryResource> getAuditoryResourcesByAuditoryId(Long auditoryId);
    List<AuditoryResource> getAuditoryResourcesByTimeSlotId(Long timesloId);
    AuditoryResource getByAuditoryIdAndTimeslotId(Long timeslotId, Long auditoryId);
    void generate();
    void delete(Long id);
    void deleteByAuditoryAndTimeslot(Long auditoryId, Long timeslotId);

}
