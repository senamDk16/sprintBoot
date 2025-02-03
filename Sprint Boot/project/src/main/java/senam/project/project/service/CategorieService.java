package senam.project.project.service;

import senam.project.project.entity.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie createCategorie(Categorie categorie);
    List<Categorie> readCategorie();
    Categorie updateCategorie(Long id, Categorie categorieDetail);
    void deleteCategorie(Long id);

}
