package ru.efko.testTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.efko.testTask.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
