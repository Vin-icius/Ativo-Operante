package br.unoeste.fipp.ativooperante.dataBase.repositories;

import br.unoeste.fipp.ativooperante.dataBase.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface feedbackRepository extends JpaRepository<Feedback,Long> {
}
