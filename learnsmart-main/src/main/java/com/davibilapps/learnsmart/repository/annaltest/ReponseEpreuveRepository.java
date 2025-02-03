package com.davibilapps.learnsmart.repository.annaltest;

import com.davibilapps.learnsmart.entity.annaltest.Epreuve;
import com.davibilapps.learnsmart.entity.annaltest.ReponseEpreuve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReponseEpreuveRepository extends JpaRepository<ReponseEpreuve,Long> {

    Optional<ReponseEpreuve> findByTrackingId(UUID uuid);

    List<ReponseEpreuve> findALlByEpreuve(Epreuve epreuve);


}
