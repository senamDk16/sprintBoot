package senam.project.project.service;

import senam.project.project.entity.Produit;

import java.util.List;

public interface ProduitService {
    Produit createProduit(Produit produit);
    List<Produit> readProduit();
    Produit updateProduit(Long id, Produit produitDetail);
    void deleteProduit(Long id);
}
