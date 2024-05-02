package br.unoeste.fipp.ativooperante.dataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tipo")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tip_id")
    private Long id;
    @Column(name="tip_nome")
    private String name;

    public Type() {
        this(0L,"");
    }

    public Type(Long id, String name) {
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
