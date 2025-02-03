package com.davibilapps.learnsmart.repository.exercise;

import com.davibilapps.learnsmart.entity.Exercise.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExerciceRepository extends JpaRepository<Exercice,Long> {

    Optional<Exercice> findByTrackingId(UUID uuid);

    @Query("SELECT e FROM Exercice e WHERE (:niveauID IS NULL OR e.niveau.trackingId = :niveauID) AND (:matiereID IS NULL OR e.matiere.trackingId = :matiereID)")
    List<Exercice> findAllByFilters(@Param("niveauID") UUID niveauID, @Param("matiereID") UUID matiereID);

    boolean existsByTitre(String titre);

}
