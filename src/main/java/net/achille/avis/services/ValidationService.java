package net.achille.avis.services;

import lombok.AllArgsConstructor;
import net.achille.avis.entities.Utilisateur;
import net.achille.avis.entities.Validation;
import net.achille.avis.repository.ValidationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@AllArgsConstructor
public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;
    public void enregistrer(Utilisateur utilisateur){

        Validation validation = new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation = Instant.now();
        validation.setCreation(creation);

        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);
      Random random = new Random();
        int randomInteger =  random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);

        this.validationRepository.save(validation);
        this.notificationService.envoyer(validation);

    }
    public Validation lireEnFonctionDuCode(String code){
        return this.validationRepository.findByCode(code).orElseThrow(()-> new RuntimeException("Votre code est valide"));
    }
}
