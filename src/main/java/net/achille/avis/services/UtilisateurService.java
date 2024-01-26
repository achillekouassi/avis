package net.achille.avis.services;

import lombok.AllArgsConstructor;

import net.achille.avis.entities.Role;
import net.achille.avis.entities.TypeRole;
import net.achille.avis.entities.Utilisateur;
import net.achille.avis.entities.Validation;
import net.achille.avis.repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurService {
    private ValidationService validationService;
   private UtilisateurRepository utilisateurRepository;
   private BCryptPasswordEncoder passwordEncoder;
    public void inscription(Utilisateur utilisateur){
        // VERIFIER EMAIL
        if(!utilisateur.getEmail().contains("@")){
            throw new RuntimeException("Votre mail est invalide");
        }
        if(!utilisateur.getEmail().contains(".")){
            throw new RuntimeException("Votre mail est invalide");
        }

        // VERIFIER SI L'EMAIL N'EXISTE PAS
        Optional <Utilisateur> utilisateurOptional = this.utilisateurRepository.findByEmail(utilisateur.getEmail());
        if(utilisateurOptional.isPresent()){
            throw new RuntimeException("Votre mail est déja utilisé");
        }

    // CRIPTER MOT DE PASSE
      String mdpCripte = this.passwordEncoder.encode(utilisateur.getMdp());
      utilisateur.setMdp(mdpCripte);

// DEFINIR ROLE UTILISATEUR PAR DEFAUT
        Role roleUtilisateur = new Role();
        roleUtilisateur.setLibelle(TypeRole.UTILISATEUR);
        utilisateur.setRole(roleUtilisateur);
   utilisateur = this.utilisateurRepository.save(utilisateur);
   this.validationService.enregistrer(utilisateur);
    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));
        if(Instant.now().isAfter(validation.getExpiration())){
            throw new RuntimeException("Votre code à expiré");
        }
       Utilisateur utilisateurActiver = this.utilisateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(()-> new RuntimeException("Utilisateur est inconnu"));
        utilisateurActiver.setActif(true);
        this.utilisateurRepository.save(utilisateurActiver);
    }
}
