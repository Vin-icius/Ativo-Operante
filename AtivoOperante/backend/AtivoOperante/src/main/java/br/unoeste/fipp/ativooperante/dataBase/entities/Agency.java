package br.unoeste.fipp.ativooperante.dataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="orgaos")
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="org_id")
    private Long id;
    @Column(name="org_nome")
    private String name;

    public Agency() {
        this(0L,"");
    }

    public Agency(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
