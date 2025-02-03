package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Cycle;
import com.davibilapps.learnsmart.entity.Etablissement;
import com.davibilapps.learnsmart.entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CycleRepository extends JpaRepository<Cycle,Long> {

    Optional<Cycle> findByTrackingId(UUID uuid);

    List<Cycle> findAllByOrderByLibelle();
    
    List<Cycle> findAllByEtablissementOrderByLibelle(Etablissement etablissement);
    
    boolean existsByLibelle(String libelle);
    
    

}
