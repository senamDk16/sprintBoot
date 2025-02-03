package senam.project.project_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project_1.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
