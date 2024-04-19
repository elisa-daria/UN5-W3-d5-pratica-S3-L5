package elisadaria.UN5W3d5praticaS3L5.repositories;

import elisadaria.UN5W3d5praticaS3L5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersDAO extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
