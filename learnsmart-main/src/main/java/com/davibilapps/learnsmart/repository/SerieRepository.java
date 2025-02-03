package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Cycle;
import com.davibilapps.learnsmart.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SerieRepository extends JpaRepository<Serie,Long> {

    Optional<Serie> findByTrackingId(UUID uuid);

    List<Serie> findAllByOrderByLibelle();

    List<Serie> findAllByCycleOrderByLibelle(Cycle cycle);
    
    boolean existsByLibelle(String libelle);

}
