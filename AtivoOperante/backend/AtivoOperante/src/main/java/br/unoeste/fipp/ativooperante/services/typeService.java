package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.Type;
import br.unoeste.fipp.ativooperante.dataBase.repositories.typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class typeService {

    @Autowired
    private typeRepository typeRepo;

    public Type addType(Type type){
        return typeRepo.save(type);
    }

    public Type getById(Long id){
        Type type = typeRepo.findById(id).get();
        return type;
    }

    public List<Type> getAll(){
        return typeRepo.findAll();
    }

    public boolean deleteById(Long id){
        try{
            typeRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
