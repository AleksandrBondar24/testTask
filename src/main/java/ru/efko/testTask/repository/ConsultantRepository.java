package ru.efko.testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.efko.testTask.model.Consultant;

public interface ConsultantRepository extends JpaRepository<Consultant, Long> {
}
