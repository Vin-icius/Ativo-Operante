package br.unoeste.fipp.ativooperante.services;

import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import br.unoeste.fipp.ativooperante.dataBase.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private userRepository userRepo;
    public User getById(Long id){
        User user = userRepo.findById(id).get();
        return user;
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public boolean deleteById(Long id){
        try{
            userRepo.deleteById(id);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

}
