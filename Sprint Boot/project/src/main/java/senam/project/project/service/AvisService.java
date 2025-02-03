package senam.project.project.service;

import senam.project.project.entity.Avis;

import java.util.List;

public interface AvisService {
    Avis createAvis(Avis avis);
    List<Avis> readAvis();
    Avis updateAvis(Long id, Avis avisDetail);
    void deleteAvis(Long id);

}
