package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EleveRepository extends JpaRepository<Eleve,Long> {

    Optional<Eleve> findByTrackingId(UUID uuid);


    @Query("select i.eleve from Inscription i where i.anneeScolaire.etat=true and i.classe=?1 order by i.eleve.nom,i.eleve.prenom")
    List<Eleve> findAllByEleve(Etablissement etablissement, AnneeScolaire anneeScolaire);



}
