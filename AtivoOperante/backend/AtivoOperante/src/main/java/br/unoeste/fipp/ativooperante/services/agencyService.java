package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.Agency;
import br.unoeste.fipp.ativooperante.dataBase.repositories.agencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
