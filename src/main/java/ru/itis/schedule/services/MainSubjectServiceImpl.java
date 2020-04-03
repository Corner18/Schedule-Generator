package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.MainSubject;
import ru.itis.schedule.repositories.MainSubjectRepository;

import java.util.List;

@Component
public class MainSubjectServiceImpl implements MainSubjectService {

    @Autowired
    private MainSubjectRepository mainSubjectRepository;

    @Override
    public void save(MainSubject mainSubject) {
        mainSubjectRepository.save(mainSubject);
    }

    @Override
    public List<MainSubject> getMainSubjects() {
        List<MainSubject> list = mainSubjectRepository.findAll();
        return list;
    }

    @Override
    public List<MainSubject> getMainSubjectByGroupId(Long groupId) {
        List<MainSubject> list = mainSubjectRepository.getAllByGroup_Id(groupId);
        return list;
    }

    @Override
    public MainSubject getById(Long id) {
        return mainSubjectRepository.getOne(id);
    }
}
