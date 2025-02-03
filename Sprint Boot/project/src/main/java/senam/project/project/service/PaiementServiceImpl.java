package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Paiement;
import senam.project.project.repository.PaiementRepository;

import java.util.List;
@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {
    private final PaiementRepository paiementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public Paiement createPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public List<Paiement> readPaiement() {

        return paiementRepository.findAll();
    }

    @Override
    public Paiement updatePaiement(Long id, Paiement paiementDetail) {
        Paiement paiement = paiementRepository.findById(id).orElse(null);
        paiement.setDate_paiement(paiementDetail.getDate_paiement());
        paiement.setMontant(paiementDetail.getMontant());
        return paiementRepository.save(paiement);
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}
