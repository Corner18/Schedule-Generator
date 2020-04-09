package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> getAllByGroupSet_Id(Long id);
    List<Group> getAllByGroupSet_Course_Id(Long courseId);
}
