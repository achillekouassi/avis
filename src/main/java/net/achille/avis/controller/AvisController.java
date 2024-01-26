package net.achille.avis.controller;

import lombok.AllArgsConstructor;
import net.achille.avis.entities.Avis;
import net.achille.avis.services.AvisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("avis")
@RestController
public class AvisController {
    public AvisService avisService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creer(@RequestBody Avis avis){
        this.avisService.creer(avis);
    }
}
