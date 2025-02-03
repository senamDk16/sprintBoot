package senam.project.project_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project_1.entity.Comptabilite;

public interface ComptabiliteRepository extends JpaRepository<Comptabilite, Long> {
}
