package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.Group;
import ru.itis.schedule.models.MainSubject;

import java.util.List;

public interface MainSubjectRepository extends JpaRepository<MainSubject, Long> {
    List<MainSubject> getAllByGroup_Id(Long groupId);
}
