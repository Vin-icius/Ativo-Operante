package br.unoeste.fipp.ativooperante.dataBase.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="denuncia")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="den_id")
    private Long id;
    @Column(name="den_titulo")
    private String title;
    @Column(name="den_texto")
    private String text;
    @Column(name="den_urgencia")
    private int urgency;
    @ManyToOne
    @JoinColumn(name="org_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Agency agency;
    @Column(name="den_data")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name="tip_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private Type type;
    @ManyToOne
    @JoinColumn(name="usu_id", nullable = false) // nome da coluna que vai referenciar, e se pode ou não ser nula, nesse caso não pode ser NOT_NULL
    private User user;

    @OneToOne(mappedBy="denuncia")
    private Feedback feedback;

    public Complaint() {
        this(0L,"","",0,null, null,null,null);
    }

    public Complaint(Long id, String title, String text, int urgency, Agency org_id, LocalDate data, Type tip_id, User user) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.urgency = urgency;
        this.agency = org_id;
        this.data = data;
        this.type = tip_id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public Agency getOrg_id() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
