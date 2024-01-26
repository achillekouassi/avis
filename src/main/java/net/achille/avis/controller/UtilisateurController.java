package net.achille.avis.controller;

import lombok.AllArgsConstructor;
import net.achille.avis.entities.Utilisateur;
import net.achille.avis.services.UtilisateurService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurController {
    private UtilisateurService utilisateurService;
    @PostMapping(path = "inscription")
    public void inscription(@RequestBody Utilisateur utilisateur){
     this.utilisateurService.inscription(utilisateur);

    }

    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation){
        this.utilisateurService.activation(activation);

    }
}
