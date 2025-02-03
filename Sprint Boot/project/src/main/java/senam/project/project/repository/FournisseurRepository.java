package senam.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
}
