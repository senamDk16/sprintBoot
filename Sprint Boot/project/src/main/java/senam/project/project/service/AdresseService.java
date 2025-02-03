package senam.project.project.service;

import senam.project.project.entity.Adresse;

import java.util.List;

public interface AdresseService {
    Adresse createAdresse(Adresse adresse);
    List<Adresse> readAdresse();
    Adresse updateAdresse(Long id, Adresse adresseDetail);
    void deleteAdresse(Long id);
}
