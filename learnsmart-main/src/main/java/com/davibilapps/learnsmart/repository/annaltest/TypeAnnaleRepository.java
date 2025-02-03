package com.davibilapps.learnsmart.repository.annaltest;

import com.davibilapps.learnsmart.entity.annaltest.TypeAnnale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TypeAnnaleRepository extends JpaRepository<TypeAnnale,Long> {

    Optional<TypeAnnale> findByTrackingId(UUID uuid);

    List<TypeAnnale> findALlByOrderByLibelle();

    boolean existsByLibelle(String libelle);
        

}
