package senam.project.project.service;

import senam.project.project.entity.Fournisseur;

import java.util.List;

public interface FournisseurService {
    Fournisseur createFournisseur(Fournisseur fournisseur);
    List<Fournisseur> readFournisseur();
    Fournisseur updateFournisseur(Long id, Fournisseur fournisseurDetail);
    void deleteFournisseur(Long id);
}
