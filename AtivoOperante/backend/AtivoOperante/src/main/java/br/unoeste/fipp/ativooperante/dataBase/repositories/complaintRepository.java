package br.unoeste.fipp.ativooperante.dataBase.repositories;

import br.unoeste.fipp.ativooperante.dataBase.entities.Complaint;
import br.unoeste.fipp.ativooperante.dataBase.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface complaintRepository extends JpaRepository<Complaint, Long> {

    public List<Complaint> findAllByUser(User user);
    @Modifying
    @Transactional
    @Query(value="INSERT into feedback (fee_texto, den_id) VALUES (:texto,:den_id)",
            nativeQuery = true)
    public void addFeedback(@Param("den_id") Long den_id, @Param("texto") String texto);

    @Query("SELECT c FROM Complaint c WHERE c.user.email = :email")
    List<Complaint> findByUserEmail(@Param("email") String email);

}
