package com.davibilapps.learnsmart.repository.annaltest;

import com.davibilapps.learnsmart.entity.annaltest.Annale;
import com.davibilapps.learnsmart.entity.annaltest.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EpreuveRepository extends JpaRepository<Epreuve,Long> {

    Optional<Epreuve> findByTrackingId(UUID uuid);

    List<Epreuve> findALlByAnnale(Annale annale);

    boolean existsByTitre(String  titre);


}
