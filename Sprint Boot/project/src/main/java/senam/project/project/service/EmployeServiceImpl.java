package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Employe;
import senam.project.project.repository.EmployeRepository;

import java.util.List;
@Service
@Transactional
public class EmployeServiceImpl implements EmployeService {
    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe createEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> readEmploye() {

        return employeRepository.findAll();
    }

    @Override
    public Employe updateEmploye(Long id, Employe employeDetail) {
        Employe employe = employeRepository.findById(id).orElse(null);
        employe.setActive(employeDetail.getActive());
        employe.setEmail(employeDetail.getEmail());
        employe.setNom(employeDetail.getNom());
        employe.setPrenom(employeDetail.getPrenom());
        employe.setTelephone(employeDetail.getTelephone());
        employe.setPoste(employeDetail.getPoste());

        return employeRepository.save(employe);
    }

    @Override
    public void delteEmploye(Long id) {
        employeRepository.deleteById(id);
    }
}
