package ru.itis.schedule.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        List<Group> list = groupRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        return list;
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    @Override
    public List<Group> getGroupsByGroupSet(Long id) {
        return groupRepository.getAllByGroupSet_Id(id);
    }

    @Override
    public List<Group> getAllByCourseId(Long courseId) {
        return groupRepository.getAllByGroupSet_Course_Id(courseId);
    }
}
