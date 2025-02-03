package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Categorie;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.TypeMatiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MatiereRepository extends JpaRepository<Matiere,Long> {

    Optional<Matiere> findByTrackingId(UUID uuid);

    List<Matiere> findAllByOrderByLibelle();

    List<Matiere> findAllByCategorieOrderByLibelle(Categorie categorie);

    List<Matiere> findAllByTypeMatiereOrderByLibelle(TypeMatiere typeMatiere);

    boolean existsByLibelle(String libelle);

}
