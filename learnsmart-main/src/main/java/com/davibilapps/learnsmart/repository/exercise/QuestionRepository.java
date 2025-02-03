package com.davibilapps.learnsmart.repository.exercise;

import com.davibilapps.learnsmart.entity.Exercise.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    Optional<Question> findByTrackingId(UUID uuid);
    
    @Query("SELECT e FROM Question e WHERE (:exerciceID IS NULL OR e.exercice.trackingId = :exerciceID) AND (:coursID IS NULL OR e.cours.trackingId = :coursID)")
    List<Question> findAllByFilters(@Param("exerciceID") UUID exerciceID, @Param("coursID") UUID coursID);
    
    boolean existsByOrdre(String ordre);

    List<Question> findByExercice(UUID exerciceID);

    List<Question> findByCours(UUID coursID);
}
