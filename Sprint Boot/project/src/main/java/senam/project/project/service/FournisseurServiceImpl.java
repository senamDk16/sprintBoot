package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Fournisseur;
import senam.project.project.repository.FournisseurRepository;

import java.util.List;
@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public List<Fournisseur> readFournisseur() {

        return fournisseurRepository.findAll();
    }

    @Override
    public Fournisseur updateFournisseur(Long id, Fournisseur fournisseurDetail) {
        Fournisseur fournisseur = fournisseurRepository.findById(id).orElse(null);
        fournisseur.setPrenom(fournisseur.getPrenom());
        fournisseur.setNom(fournisseur.getNom());
        fournisseur.setActive(fournisseurDetail.isActive());
        fournisseur.setEmail(fournisseur.getEmail());
        fournisseur.setTelephone(fournisseur.getTelephone());

        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
}
