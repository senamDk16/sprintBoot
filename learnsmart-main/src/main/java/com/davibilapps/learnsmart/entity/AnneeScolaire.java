package com.davibilapps.learnsmart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "annee_scolaires")
@EntityListeners(AuditingEntityListener.class)
public class AnneeScolaire extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId = UUID.randomUUID();;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @Column(nullable = false, length = 10)
    private String libelle;

    private boolean etat;
}
