package net.achille.avis.services;

import lombok.AllArgsConstructor;
import net.achille.avis.entities.Validation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void envoyer(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-replyce@achille.com");
        message.setFrom(validation.getUtilisateur().getEmail());
        message.setSubject("Votre code d'activation");
        String texte = String.format("Bonjour %s <br /> Votre code d'action est %s; A bientôt ", validation.getUtilisateur().getNom(),validation.getCode());
        message.setText(texte);
        javaMailSender.send(message);
    }
}
