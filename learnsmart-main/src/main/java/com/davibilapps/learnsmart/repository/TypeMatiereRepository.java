package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.TypeMatiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TypeMatiereRepository extends JpaRepository<TypeMatiere,Long> {

    Optional<TypeMatiere> findByTrackingId(UUID uuid);

    List<TypeMatiere> findAllByOrderByLibelle();
    
    boolean existsByLibelle(String libelle);

}
