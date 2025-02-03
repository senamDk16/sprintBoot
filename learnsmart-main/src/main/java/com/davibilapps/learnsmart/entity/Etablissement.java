package com.davibilapps.learnsmart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;


@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "etablissements")
@EntityListeners(AuditingEntityListener.class)
public class Etablissement extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId = UUID.randomUUID();

    private String nom;

    private String slogan;

    private String telephone1;

    private String telephone2;

    private String email1;

    private String email2;

    private String site;

    private String fondateur;

    private boolean etat;
}
