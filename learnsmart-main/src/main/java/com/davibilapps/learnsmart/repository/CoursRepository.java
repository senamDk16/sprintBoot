package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CoursRepository extends JpaRepository<Cours,Long> {

    Optional<Cours> findByTrackingId(UUID uuid);

    List<Cours> findAllByOrderByTitre();

    List<Cours> findAllByMatiereOrderByTitre(Matiere matiere);

    boolean existsByTitre(String libelle);

}
