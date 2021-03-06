package ru.itis.schedule.services;

import ru.itis.schedule.models.Group;

import java.util.List;

public interface GroupService {
    List<Group> getGroups();
    void save(Group group);
    List<Group> getGroupsByGroupSet(Long id);
    List<Group> getAllByCourseId(Long courseId);
}
