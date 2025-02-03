package com.davibilapps.learnsmart.entity;

import com.davibilapps.learnsmart.entity.Exercise.Question;
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
@Table(name = "documents")
@EntityListeners(AuditingEntityListener.class)
public class Document extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private UUID trackingId = UUID.randomUUID();

    @Column(name="libelle", length = 101, nullable = false)
    private String path;

    private String name;

    private boolean etat;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}
