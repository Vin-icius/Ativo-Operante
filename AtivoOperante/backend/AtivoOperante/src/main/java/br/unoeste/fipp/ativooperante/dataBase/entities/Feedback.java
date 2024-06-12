package br.unoeste.fipp.ativooperante.dataBase.entities;

import jakarta.persistence.*;

@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fee_id")
    private Long id;
    @Column(name="fee_texto")
    private String text;

    @OneToOne
    @JoinColumn(name="den_id")
    private Complaint denuncia;

    public Feedback() {
        this(0L,"",null);
    }

    public Feedback(Long id, String text, Complaint denuncia) {
        this.id = id;
        this.text = text;
        this.denuncia = denuncia;
    }

    public Complaint getDenuncia() {
        return denuncia;
    }

    public void setDenuncia(Complaint denuncia) {
        this.denuncia = denuncia;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return text;
    }

    public void setName(String name) {
        this.text = name;
    }
}
