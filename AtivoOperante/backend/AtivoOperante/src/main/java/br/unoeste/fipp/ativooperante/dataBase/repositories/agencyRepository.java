package br.unoeste.fipp.ativooperante.dataBase.repositories;

import br.unoeste.fipp.ativooperante.dataBase.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface agencyRepository extends JpaRepository<Agency, Long> {

}
