package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.SubjectSet;
import ru.itis.schedule.repositories.SubjectSetRepository;

import java.util.List;

@Component
public class SubjectSetServiceImpl implements SubjectSetService {

    @Autowired
    private SubjectSetRepository subjectSetRepository;

    @Override
    public List<SubjectSet> getSubjectSets() {
        return subjectSetRepository.findAll();
    }

    @Override
    public void save(SubjectSet subjectSet) {
        subjectSetRepository.save(subjectSet);
    }
}
