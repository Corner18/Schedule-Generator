package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Auditory;
import ru.itis.schedule.repositories.AuditoryRepository;

import java.util.List;

@Component
public class AuditoryServiceImpl implements AuditoryService {

    @Autowired
    private AuditoryRepository auditoryRepository;

    @Override
    public List<Auditory> getAuditories() {
        List<Auditory> auditories = auditoryRepository.findAll();
        return auditories;
    }

    @Override
    public void add(Auditory auditory) {
        auditoryRepository.save(auditory);
    }

    @Override
    public Auditory getById(Long id) {
        return auditoryRepository.getOne(id);
    }
}
