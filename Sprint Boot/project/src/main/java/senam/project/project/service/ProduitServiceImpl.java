package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Produit;
import senam.project.project.repository.PaiementRepository;
import senam.project.project.repository.ProduitRepository;

import java.util.List;
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> readProduit() {

        return produitRepository.findAll();
    }

    @Override
    public Produit updateProduit(Long id, Produit produitDetail) {
        Produit produit = produitRepository.findById(id).orElse(null);
        produit.setDescription(produitDetail.getDescription());
        produit.setNom(produit.getNom());
        produit.setPrix(produit.getPrix());
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
