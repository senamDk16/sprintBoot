package com.davibilapps.learnsmart.entity.annaltest;

import com.davibilapps.learnsmart.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;


@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "epreuves")
@EntityListeners(AuditingEntityListener.class)
public class Epreuve extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    private String titre;

    private String contenu;

    @ManyToOne
    @JoinColumn(name = "annale_id")
    private Annale annale;





}
