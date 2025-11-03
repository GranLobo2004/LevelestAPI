package es.zenith.levelestapi.repository;

import es.zenith.levelestapi.domain.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsigniaRepository extends JpaRepository<Insignia, Long> {
}