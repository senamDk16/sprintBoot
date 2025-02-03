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
@Table(name = "series")
@EntityListeners(AuditingEntityListener.class)
public class Serie extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID trackingId = UUID.randomUUID();

    @Column(nullable = false, length = 100)
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "cycle_id")
    private Cycle cycle;

}
