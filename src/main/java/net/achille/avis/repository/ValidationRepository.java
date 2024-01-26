package net.achille.avis.repository;

import net.achille.avis.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {

    Optional<Validation> findByCode(String code);
}
