package com.davibilapps.learnsmart.repository.exercise;

import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Exercise.Reponse;
import com.davibilapps.learnsmart.entity.Exercise.SolutionExercice;
import com.davibilapps.learnsmart.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SolutionExerciceRepository extends JpaRepository<SolutionExercice,Long> {

    Optional<SolutionExercice> findByTrackingId(UUID uuid);

    List<SolutionExercice> findAllByQuestionOrderByQuestionOrdreAsc(Question question);

    List<SolutionExercice> findAllByQuestionAndInscription(Question question, Inscription inscription);

}
