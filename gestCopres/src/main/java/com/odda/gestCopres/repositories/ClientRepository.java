package com.odda.gestCopres.repositories;

import com.odda.gestCopres.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
