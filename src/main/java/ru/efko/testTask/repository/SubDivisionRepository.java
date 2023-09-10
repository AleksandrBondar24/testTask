package ru.efko.testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.efko.testTask.model.SubDivision;

public interface SubDivisionRepository extends JpaRepository<SubDivision, Long> {
}
