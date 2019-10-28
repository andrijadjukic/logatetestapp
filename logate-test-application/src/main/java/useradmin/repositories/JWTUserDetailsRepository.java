package useradmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import useradmin.domain.User;

import java.util.Optional;

public interface JWTUserDetailsRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
