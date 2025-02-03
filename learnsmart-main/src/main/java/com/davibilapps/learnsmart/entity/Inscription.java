package com.davibilapps.learnsmart.entity;

import com.davibilapps.learnsmart.enums.Statut;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "inscriptions")
@EntityListeners(AuditingEntityListener.class)
public class Inscription extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Statut statut;

    private LocalDate dateInscription;


    @ManyToOne
    @JoinColumn(name = "eleve_id")
    private Eleve eleve;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "annee_scolaire_id")
    private AnneeScolaire anneeScolaire;

    @ManyToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;


}
