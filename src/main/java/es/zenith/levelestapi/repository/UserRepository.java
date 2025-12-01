package es.zenith.levelestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.zenith.levelestapi.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
