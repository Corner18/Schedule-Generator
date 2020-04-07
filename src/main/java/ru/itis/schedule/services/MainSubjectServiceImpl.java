package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Group;
import ru.itis.schedule.models.MainSubject;
import ru.itis.schedule.models.SubjectSet;
import ru.itis.schedule.repositories.MainSubjectRepository;

import java.util.List;

@Component
public class MainSubjectServiceImpl implements MainSubjectService {

    @Autowired
    private MainSubjectRepository mainSubjectRepository;

    @Autowired
    private SubjectSetService subjectSetService;

    @Autowired
    private GroupService groupService;

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
    public MainSubject getById(Long id) {
        return mainSubjectRepository.getOne(id);
    }

    @Override
    public List<MainSubject> getMainSubjectBySubjectSetId(Long id) {
        return mainSubjectRepository.getAllBySubjectSet_Id(id);
    }

    @Override
    public void generate() {
        List<SubjectSet> subjectSets = subjectSetService.getSubjectSets();
        for (SubjectSet subjectSet : subjectSets){
            List<Group> groups = groupService.getGroupsByGroupSet(subjectSet.getGroupSet().getId());
            for(Group group : groups){
                MainSubject mainSubject = MainSubject.builder()
                        .subjectSet(subjectSet)
                        .group(group)
                        .build();
                mainSubjectRepository.save(mainSubject);
            }
        }
    }
}
