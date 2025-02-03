package senam.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
