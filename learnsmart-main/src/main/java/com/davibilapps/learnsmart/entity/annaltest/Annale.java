package com.davibilapps.learnsmart.entity.annaltest;

import com.davibilapps.learnsmart.entity.BaseEntity;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.Niveau;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "annales")
@EntityListeners(AuditingEntityListener.class)
public class Annale extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    private String annee;

    @Column(nullable = false, length = 100)
    private String notion;

    @ManyToOne
    @JoinColumn(name = "type_annale_id")
    private TypeAnnale typeAnnale;

    @ManyToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    private Niveau niveau;

    @OneToMany(mappedBy = "annale")
    private List<Epreuve> epreuveList;


}
