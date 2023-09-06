package ru.efko.testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.efko.testTask.model.Structure;

public interface StructureRepository extends JpaRepository<Structure, Long> {
}
