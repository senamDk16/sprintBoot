package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EtablissementRepository extends JpaRepository<Etablissement,Long> {

    Optional<Etablissement> findByTrackingId(UUID uuid);

    List<Etablissement> findAllByOrderByNom();
    
    boolean existsByNom(String libelle);

}
