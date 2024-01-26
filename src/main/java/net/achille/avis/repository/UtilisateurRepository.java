package net.achille.avis.repository;

import net.achille.avis.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
   Optional <Utilisateur> findByEmail(String email);
}
