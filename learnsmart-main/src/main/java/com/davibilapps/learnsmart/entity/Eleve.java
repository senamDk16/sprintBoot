package com.davibilapps.learnsmart.entity;

import com.davibilapps.learnsmart.enums.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "eleves")
@EntityListeners(AuditingEntityListener.class)
public class Eleve extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    @NotBlank
    @Column(nullable = false, length = 10)
    private String matricule;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nom;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String prenom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Genre sexe;

    private LocalDate dateNaissance;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String lieuNaissance;

    @NotBlank
    @Column(nullable = false, length = 10)
    @Pattern(regexp = "^\\d{10}$", message = "Le contact doit être un numéro à 10 chiffres")
    private String contact;

    @NotBlank
    @Column(nullable = true, length = 10)
    @Pattern(regexp = "^\\d{10}$", message = "Le contact doit être un numéro à 10 chiffres")
    private String contact2;

    @NotBlank
    @Column(nullable = true, length = 100)
    private String email;

    @NotBlank
    @Column(nullable = true, length = 100)
    private String email2;

    @ManyToOne
    @JoinColumn(name = "nationalite_id")
    private Nationalite nationalite;

}
