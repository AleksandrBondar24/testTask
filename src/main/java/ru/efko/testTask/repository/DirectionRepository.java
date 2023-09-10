package ru.efko.testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.efko.testTask.model.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
