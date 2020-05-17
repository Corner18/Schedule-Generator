package ru.itis.schedule.services;

import ru.itis.schedule.models.Auditory;

import java.util.List;

public interface AuditoryService {
    List<Auditory> getAuditories();
    void add(Auditory auditory);
    Auditory getById(Long id);
}
