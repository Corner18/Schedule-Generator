package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.schedule.models.Group;
import ru.itis.schedule.repositories.GroupRepository;

import java.util.List;

@Component
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> getGroups() {
        List<Group> list = groupRepository.findAll();
        return list;
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public List<Group> getGroupsByCourse(Long course) {
        List<Group> list = groupRepository.getAllByCourse_Id(course);
        return list;
    }
}
