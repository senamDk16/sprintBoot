package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NiveauRepository extends JpaRepository<Niveau,Long> {

    Optional<Niveau> findByTrackingId(UUID uuid);

    List<Niveau> findAllByOrderByLibelle();
    
    boolean existsByLibelle(String libelle);

    List<Niveau> findAllBySerie(Serie serie);
}
