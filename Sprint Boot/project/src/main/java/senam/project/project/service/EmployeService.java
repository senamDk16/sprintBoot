package senam.project.project.service;

import senam.project.project.entity.Employe;

import java.util.List;

public interface EmployeService {
    Employe createEmploye(Employe employe);
    List<Employe> readEmploye();
    Employe updateEmploye(Long id, Employe EmployeDetail);
    void delteEmploye(Long id);
}
