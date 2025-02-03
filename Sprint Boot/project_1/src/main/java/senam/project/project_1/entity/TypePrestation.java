package senam.project.project_1.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "type_pretation")
public class TypePrestation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date_prestation;
    private float poids_total;
    private int cout_total;

    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne
    private Comptabilite comptabilite;
    @ManyToOne
    private Client client;

    public TypePrestation() {
    }

    public TypePrestation(Long id, LocalDateTime date_prestation, float poids_total, int cout_total, Utilisateur utilisateur, Comptabilite comptabilite, Client client) {
        this.id = id;
        this.date_prestation = date_prestation;
        this.poids_total = poids_total;
        this.cout_total = cout_total;
        this.utilisateur = utilisateur;
        this.comptabilite = comptabilite;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getDate_prestation() {
        return date_prestation;
    }

    public void setDate_prestation(LocalDateTime date_prestation) {
        this.date_prestation = date_prestation;
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


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Comptabilite getComptabilite() {
        return comptabilite;
    }

    public void setComptabilite(Comptabilite comptabilite) {
        this.comptabilite = comptabilite;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "TypePrestation{" +
                "id=" + id +
                ", date_prestation=" + date_prestation +
                ", poids_total=" + poids_total +
                ", cout_total=" + cout_total +
                ", utilisateur=" + utilisateur +
                ", comptabilite=" + comptabilite +
                ", client=" + client +
                '}';
    }
}
