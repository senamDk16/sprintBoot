package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Classe;
import com.davibilapps.learnsmart.entity.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClasseRepository extends JpaRepository<Classe,Long> {

    Optional<Classe> findByTrackingId(UUID uuid);

    List<Classe> findAllByOrderByLibelle();

    List<Classe> findAllByNiveauOrderByLibelle(Niveau niveau);

    boolean existsByLibelle(String libelle);



}
