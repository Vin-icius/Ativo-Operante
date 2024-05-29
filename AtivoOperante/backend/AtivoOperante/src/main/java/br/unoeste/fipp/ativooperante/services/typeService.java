package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.Type;
import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import br.unoeste.fipp.ativooperante.dataBase.repositories.typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public boolean editType(Type type) {
        // Verifica se o tipo de problema existe no banco de dados
        Optional<Type> existingTypeOptional = typeRepo.findById(type.getId());
        if (existingTypeOptional.isPresent()) {
            // Atualiza os campos do tipo de problema existente com os novos valores
            Type existingType = existingTypeOptional.get();
            existingType.setName(type.getName()); // Atualiza o nome do tipo de problema

            // Salva as alterações no banco de dados
            typeRepo.save(existingType);
            return true; // Retorna verdadeiro indicando que o tipo de problema foi editado com sucesso
        } else {
            return false; // Retorna falso se o tipo de problema não foi encontrado
        }
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
