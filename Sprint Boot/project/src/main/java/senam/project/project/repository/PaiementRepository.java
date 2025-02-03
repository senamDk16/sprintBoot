package senam.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project.entity.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement,Long> {
}
