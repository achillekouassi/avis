package net.achille.avis.repository;

import net.achille.avis.entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {

}
