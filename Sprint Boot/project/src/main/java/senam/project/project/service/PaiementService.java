package senam.project.project.service;

import senam.project.project.entity.Paiement;

import java.util.List;

public interface PaiementService {
    Paiement createPaiement (Paiement paiement);
    List<Paiement> readPaiement();
    Paiement updatePaiement(Long id, Paiement paiementDetail);
    void deletePaiement(Long id);
}
