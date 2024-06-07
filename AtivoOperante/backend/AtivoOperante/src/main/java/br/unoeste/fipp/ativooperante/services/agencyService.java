package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.Agency;
import br.unoeste.fipp.ativooperante.dataBase.entities.Type;
import br.unoeste.fipp.ativooperante.dataBase.repositories.agencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class agencyService {

    @Autowired
    private agencyRepository agencyRepo;

    public Agency addAgency(Agency agency){
        return agencyRepo.save(agency);
    }

    public Agency getById(Long id){
        Agency agency = agencyRepo.findById(id).get();
        return agency;
    }

    public boolean editAgency(Agency agency) {
        Optional<Agency> existingAgencyOptional = agencyRepo.findById(agency.getId());
        if (existingAgencyOptional.isPresent()) {
            // Atualiza os campos do tipo de problema existente com os novos valores
            Agency existingAgency = existingAgencyOptional.get();
            existingAgency.setName(agency.getName()); // Atualiza o nome do tipo de problema

            // Salva as alterações no banco de dados
            agencyRepo.save(existingAgency);
            return true; // Retorna verdadeiro indicando que o tipo de problema foi editado com sucesso
        } else {
            return false; // Retorna falso se o tipo de problema não foi encontrado
        }
    }

    public List<Agency> getAll(){
        return agencyRepo.findAll();
    }

    public boolean deleteById(Long id){
        try{
            agencyRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
