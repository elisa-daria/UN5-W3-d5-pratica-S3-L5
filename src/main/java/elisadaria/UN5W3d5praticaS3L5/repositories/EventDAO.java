package elisadaria.UN5W3d5praticaS3L5.repositories;

import elisadaria.UN5W3d5praticaS3L5.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventDAO extends JpaRepository<Event,Long> {
    Optional<Event> findByTitle(String title);
}
