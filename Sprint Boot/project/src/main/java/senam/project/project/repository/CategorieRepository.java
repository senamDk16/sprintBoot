package senam.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
