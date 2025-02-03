package senam.project.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import senam.project.project.entity.Employe;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
}
