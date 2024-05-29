package br.unoeste.fipp.ativooperante.dataBase.repositories;

import br.unoeste.fipp.ativooperante.dataBase.entities.Type;
import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface typeRepository extends JpaRepository<Type, Long> {

}
