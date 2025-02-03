package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {

    Optional<Categorie> findByTrackingId(UUID uuid);

    List<Categorie> findAllByOrderByLibelle();
    
    boolean existsByLibelle(String libelle);

}
