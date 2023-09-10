package ru.efko.testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.efko.testTask.model.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
