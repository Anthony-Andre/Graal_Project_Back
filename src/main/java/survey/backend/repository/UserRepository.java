package survey.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import survey.backend.repository.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String Username);

}
