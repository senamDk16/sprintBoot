package com.davibilapps.learnsmart.repository.exercise;

import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Exercise.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReponseRepository extends JpaRepository<Reponse,Long> {

    Optional<Reponse> findByTrackingId(UUID uuid);

    List<Reponse> findAllByQuestionOrderByQuestionOrdreAsc(Question question);
    
    boolean existsByLibelle(String libelle);

}
