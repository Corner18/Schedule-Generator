package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Auditory;
import ru.itis.schedule.models.AuditoryResource;
import ru.itis.schedule.models.Timeslot;
import ru.itis.schedule.repositories.AuditoryRepository;
import ru.itis.schedule.repositories.AuditoryResourceRepository;

import java.util.List;

@Component
public class AuditoryResourceServiceImpl implements AuditoryResourceService {

    @Autowired
    private AuditoryResourceRepository auditoryResourceRepository;

    @Autowired
    private AuditoryService auditoryService;

    @Autowired
    private TimeslotService timeslotService;

    @Override
    public List<AuditoryResource> getAuditoryResoucres() {
        List<AuditoryResource> list = auditoryResourceRepository.findAll();
        return list;
    }

    @Override
    public void save(AuditoryResource auditoryResource) {
        auditoryResourceRepository.save(auditoryResource);
    }

    @Override
    public List<AuditoryResource> getAuditoryResourcesByAuditoryId(Long auditoryId) {
        return auditoryResourceRepository.getAllByAuditory_Id(auditoryId);
    }

    @Override
    public List<AuditoryResource> getAuditoryResourcesByTimeSlotId(Long timesloId) {
        return auditoryResourceRepository.getAllByTimeslot_Id(timesloId);
    }

    @Override
    public void generate() {
        List<Timeslot> timeslots = timeslotService.getTimeSlots();
        List<Auditory> auditories = auditoryService.getAuditories();
        for (Timeslot timeslot : timeslots) {
            for (Auditory auditory : auditories) {
                AuditoryResource auditoryResource = AuditoryResource.builder()
                        .auditory(auditory)
                        .timeslot(timeslot)
                        .build();
                auditoryResourceRepository.save(auditoryResource);
            }
        }
    }

    @Override
    public void delete(Long id) {
        auditoryResourceRepository.deleteById(id);
    }

    @Override
    public AuditoryResource getByAuditoryIdAndTimeslotId(Long timeslotId, Long auditoryId) {
        return auditoryResourceRepository.getByAuditory_IdAndTimeslot_Id(auditoryId, timeslotId);
    }
}
