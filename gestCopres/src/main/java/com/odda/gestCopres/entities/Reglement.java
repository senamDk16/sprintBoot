package com.odda.gestCopres.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reglement implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, nullable = false)
    private Double montantDonne;

    @Column(length = 30, nullable = false)
    private Double montantRestant;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateReglement;

    @LastModifiedDate
    private LocalDateTime updateDate;

    public Reglement() {
    }

    public Reglement(long id, Double montantDonne, Double montantRestant, LocalDateTime dateReglement, LocalDateTime updateDate) {
        this.id = id;
        this.montantDonne = montantDonne;
        this.montantRestant = montantRestant;
        this.dateReglement = dateReglement;
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMontantDonne() {
        return montantDonne;
    }

    public void setMontantDonne(Double montantDonne) {
        this.montantDonne = montantDonne;
    }

    public Double getMontantRestant() {
        return montantRestant;
    }

    public void setMontantRestant(Double montantRestant) {
        this.montantRestant = montantRestant;
    }

    public LocalDateTime getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(LocalDateTime dateReglement) {
        this.dateReglement = dateReglement;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
