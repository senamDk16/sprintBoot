package senam.project.project_1.entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comptabilites")
public class Comptabilite{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date_paiment;
    private float montant_paye;
    private String etat;

    @OneToOne
    private Client client;

    public Comptabilite() {
    }

    public Comptabilite(Long id, LocalDateTime date_paiment, float montant_paye, String etat, Client client) {
        this.id = id;
        this.date_paiment = date_paiment;
        this.montant_paye = montant_paye;
        this.etat = etat;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getDate_paiment() {
        return date_paiment;
    }

    public void setDate_paiment(LocalDateTime date_paiment) {
        this.date_paiment = date_paiment;
    }

    public float getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(float montant_paye) {
        this.montant_paye = montant_paye;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Comptabilite{" +
                "id=" + id +
                ", date_paiment=" + date_paiment +
                ", montant_paye=" + montant_paye +
                ", etat='" + etat + '\'' +
                ", client=" + client +
                '}';
    }
}
