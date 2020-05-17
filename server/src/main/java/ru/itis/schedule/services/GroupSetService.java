package ru.itis.schedule.services;

import ru.itis.schedule.models.GroupSet;

import java.util.List;

public interface GroupSetService {
    List<GroupSet> getGroupSets();
    void save(GroupSet groupSet);
}
