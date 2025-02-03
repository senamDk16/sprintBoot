package com.davibilapps.learnsmart.repository.annaltest;

import com.davibilapps.learnsmart.entity.annaltest.Annale;
import com.davibilapps.learnsmart.entity.annaltest.TypeAnnale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnnaleRepository extends JpaRepository<Annale,Long> {

    Optional<Annale> findByTrackingId(UUID uuid);

    List<Annale> findALlByTypeAnnale(TypeAnnale typeAnnale);

    @Query("SELECT e FROM Annale e WHERE (:niveauID IS NULL OR e.niveau.trackingId = :niveauID) AND (:matiereID IS NULL OR e.matiere.trackingId = :matiereID)")
    List<Annale> findAllByFilters(@Param("niveauID") UUID niveauID, @Param("matiereID") UUID matiereID);



}
