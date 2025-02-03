package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParametrageMatiereRepository extends JpaRepository<ParametrageMatiere,Long> {

    Optional<ParametrageMatiere> findByTrackingId(UUID uuid);

    List<ParametrageMatiere> findAllByOrderByMatiere();
    
    List<ParametrageMatiere> findAllByClasseAndAnneeScolaire_Etat(Classe classe, boolean anneeScolaire);
    
    boolean existsByAnneeScolaireAndMatiereAndClasse(AnneeScolaire anneeScolaire, Matiere matiere, Classe classe);
    
    

}
