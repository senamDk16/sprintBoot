package com.odda.gestCopres.repositories;

import com.odda.gestCopres.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente,Long> {
}
