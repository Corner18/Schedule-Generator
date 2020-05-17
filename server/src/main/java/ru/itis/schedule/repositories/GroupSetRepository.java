package ru.itis.schedule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.schedule.models.GroupSet;

public interface GroupSetRepository extends JpaRepository<GroupSet, Long> {
}
