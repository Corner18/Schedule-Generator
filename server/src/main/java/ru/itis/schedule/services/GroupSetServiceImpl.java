package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.GroupSet;
import ru.itis.schedule.repositories.GroupSetRepository;

import java.util.List;

@Component
public class GroupSetServiceImpl implements GroupSetService {

    @Autowired
    private GroupSetRepository groupSetRepository;

    @Override
    public List<GroupSet> getGroupSets() {
        return groupSetRepository.findAll();
    }

    @Override
    public void save(GroupSet g) {
        groupSetRepository.save(g);
    }
}
