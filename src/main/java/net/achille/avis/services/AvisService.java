package net.achille.avis.services;


import net.achille.avis.entities.Avis;
import net.achille.avis.repository.AvisRepository;
import org.springframework.stereotype.Service;

@Service
public class AvisService {
   private final AvisRepository avisRepository;

    public AvisService(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    public void creer(Avis avis){
     this.avisRepository.save(avis);
  }
}
