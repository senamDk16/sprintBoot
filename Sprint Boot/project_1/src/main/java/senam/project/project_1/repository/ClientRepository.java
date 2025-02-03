package senam.project.project_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project_1.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
