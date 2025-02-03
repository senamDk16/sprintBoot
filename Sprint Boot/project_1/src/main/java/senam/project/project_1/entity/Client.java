package senam.project.project_1.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String contact;
    @Temporal(TemporalType.DATE)
    private Date date_debut;


    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;

    public Client() {
    }

    public Client(Long id, String nom, String prenom, String contact, Date date_debut, Utilisateur utilisateur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.date_debut = date_debut;

        this.utilisateur = utilisateur;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", contact='" + contact + '\'' +
                ", date_debut=" + date_debut +
                ", utilisateur=" + utilisateur +
                '}';
    }
}
