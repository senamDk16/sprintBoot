package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Nationalite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NationaliteRepository extends JpaRepository<Nationalite,Long> {

    Optional<Nationalite> findByTrackingId(UUID uuid);

    List<Nationalite> findAllByOrderByLibelle();
    
    boolean existsByLibelle(String libelle);

}
