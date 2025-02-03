package com.davibilapps.learnsmart.entity.Exercise;

import com.davibilapps.learnsmart.entity.BaseEntity;
import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.Niveau;
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
@Table(name = "exercice")
@EntityListeners(AuditingEntityListener.class)
public class Exercice extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String titre;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;




}
