package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.Feedback;
import br.unoeste.fipp.ativooperante.dataBase.entities.Type;
import br.unoeste.fipp.ativooperante.dataBase.repositories.complaintRepository;
import br.unoeste.fipp.ativooperante.dataBase.repositories.feedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class feedbackService {
    @Autowired
    private feedbackRepository feedRepo;


    public Feedback addFeedback(Feedback feed){
        return feedRepo.save(feed);
    }
}
