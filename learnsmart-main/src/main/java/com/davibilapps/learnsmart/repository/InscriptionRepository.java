package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InscriptionRepository extends JpaRepository<Inscription,Long> {

    Optional<Inscription> findByTrackingId(UUID uuid);

    List<Inscription> findAllByAnneeScolaire_Etat(boolean anneeScolaire);

    List<Inscription> findAllByEtablissementAndAnneeScolaireOrderByEleveNomAscPrenomAsc(Etablissement etablissement, AnneeScolaire anneeScolaire);

    boolean existsByAnneeScolaireAndClasseAndEtablissementAndEleve(AnneeScolaire anneeScolaire, Classe classe, Etablissement etablissement, Eleve eleve);
    
    

}
