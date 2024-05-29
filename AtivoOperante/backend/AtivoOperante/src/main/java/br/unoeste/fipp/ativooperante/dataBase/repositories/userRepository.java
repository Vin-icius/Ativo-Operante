package br.unoeste.fipp.ativooperante.dataBase.repositories;

import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepository extends JpaRepository<User, Long> {
    //@Query("SELECT COUNT(u) FROM User u WHERE u.level = 1 AND u.active = true")
    //long countByFullAccessAndActive();

    User findByEmail(String email);
}
