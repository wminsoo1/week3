package madcamp.week3.repository;

import madcamp.week3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    User findByUserNameAndPassword(String userName, String password);

    Optional<User> findById(Long id);
}
