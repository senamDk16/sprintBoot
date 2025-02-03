package senam.project.project_1.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "linges")
public class Linge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private float poids_total;
    private int cout_total;


    @ManyToOne
    private Client client;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne
    private TypePrestation typePrestation;

    public Linge() {
    }

    public Linge(Long id, String libelle, float poids_total, int cout_total, Client client, Utilisateur utilisateur, TypePrestation typePrestation) {
        this.id = id;
        this.libelle = libelle;
        this.poids_total = poids_total;
        this.cout_total = cout_total;
        this.client = client;
        this.utilisateur = utilisateur;
        this.typePrestation = typePrestation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPoids_total() {
        return poids_total;
    }

    public void setPoids_total(float poids_total) {
        this.poids_total = poids_total;
    }

    public int getCout_total() {
        return cout_total;
    }

    public void setCout_total(int cout_total) {
        this.cout_total = cout_total;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public TypePrestation getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(TypePrestation typePrestation) {
        this.typePrestation = typePrestation;
    }

    @Override
    public String toString() {
        return "Linge{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", poids_total=" + poids_total +
                ", cout_total=" + cout_total +
                ", client=" + client +
                ", utilisateur=" + utilisateur +
                ", typePrestation=" + typePrestation +
                '}';
    }
}
