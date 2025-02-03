package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire,Long> {

    Optional<AnneeScolaire> findByTrackingId(UUID uuid);

    Optional<AnneeScolaire> findByEtatIsTrue();

    List<AnneeScolaire> findAllByOrderByIdDesc();

    @Modifying
    @Transactional
    @Query("UPDATE AnneeScolaire a SET a.etat = CASE WHEN a.trackingId = ?1 THEN true ELSE false END")
    int setEtatForIdAndResetOthers(UUID id);

    boolean existsByLibelle(String libelle);
}
