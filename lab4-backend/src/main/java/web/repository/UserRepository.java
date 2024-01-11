package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
