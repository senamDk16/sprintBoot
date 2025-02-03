package senam.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
