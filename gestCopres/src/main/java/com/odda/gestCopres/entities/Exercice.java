package com.odda.gestCopres.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

@Entity
public class Exercice implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Year annee;

    private boolean etat;
}
