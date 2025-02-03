package com.davibilapps.learnsmart.entity.Exercise;

import com.davibilapps.learnsmart.entity.BaseEntity;
import com.davibilapps.learnsmart.entity.Inscription;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "solution_exercice")
@EntityListeners(AuditingEntityListener.class)
public class SolutionExercice extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    private LocalDate dateTraitement;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String titre;

    private int score;

    private boolean modifiable;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question; //represente l'epreuve a traiter de l'eleve

    @ManyToOne
    @JoinColumn(name = "reponse_id")
    private Reponse reponse;

    @ManyToOne
    @JoinColumn(name = "inscription_id")
    private Inscription inscription;





}
