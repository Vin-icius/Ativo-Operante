package br.unoeste.fipp.ativooperante.dataBase.repositories;

import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User, Long> {
}
