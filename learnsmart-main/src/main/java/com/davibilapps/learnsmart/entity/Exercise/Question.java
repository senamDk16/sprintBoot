package com.davibilapps.learnsmart.entity.Exercise;

import com.davibilapps.learnsmart.entity.BaseEntity;
import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Document;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "questions")
@EntityListeners(AuditingEntityListener.class)
public class Question extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    private  String ordre;

    @NotBlank
    @Column(nullable = false)
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "exercice_id")
    private Exercice exercice;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @OneToMany(mappedBy = "question")
    private List<Document> documents;

    @OneToMany(mappedBy = "question")
    private List<Reponse> reponseList;

}
