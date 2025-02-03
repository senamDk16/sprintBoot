package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnseignantRepository extends JpaRepository<Enseignant,Long> {

    Optional<Enseignant> findByTrackingId(UUID uuid);

    List<Enseignant> findAllByOrderByNomAscPrenomAsc();

    boolean existsByContact(String  contact);
}
