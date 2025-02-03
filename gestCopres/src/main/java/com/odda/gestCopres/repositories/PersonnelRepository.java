package com.odda.gestCopres.repositories;

import com.odda.gestCopres.entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel,Long> {
}
