package senam.project.project_1.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Column(name = "mot_passe")
    private String motPasse;
    private String etat;


    @OneToOne
    private User user;

    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, String motPasse, String etat, User user) {
        this.id = id;
        this.nom = nom;
        this.motPasse = motPasse;
        this.etat = etat;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", motPasse='" + motPasse + '\'' +
                ", etat='" + etat + '\'' +
                ", users=" + user +
                '}';
    }
}
