package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Categorie;
import senam.project.project.repository.CategorieRepository;

import java.util.List;
@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> readCategorie() {

        return categorieRepository.findAll();
    }

    @Override
    public Categorie updateCategorie(Long id, Categorie categorieDetail) {
        Categorie categorie = categorieRepository.findById(id).orElse(null);
        categorie.setDescription(categorieDetail.getDescription());
        categorie.setLibelle(categorieDetail.getLibelle());
        return categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
}
