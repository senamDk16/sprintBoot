package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.CategorieRequest;
import com.davibilapps.learnsmart.dto.response.CategorieResponse;
import com.davibilapps.learnsmart.entity.Categorie;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.CategorieMapper;
import com.davibilapps.learnsmart.repository.CategorieRepository;
import com.davibilapps.learnsmart.services.CategorieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;

    private final CategorieMapper categorieMapper;

    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @Override
    public CategorieResponse save(CategorieRequest categorieRequest) {

        if (categorieRepository.existsByLibelle(categorieRequest.libelle())) {
            throw new AlreadyExistException("libelle existe deja");
        }

        Categorie categorie = categorieMapper.toEntity(categorieRequest);
        Categorie categorie1 = categorieRepository.save(categorie);

        return categorieMapper.toResponse(categorie1);
    }

    @Override
    @Transactional
    public CategorieResponse update(UUID trackingId, CategorieRequest categorieRequest) {

        Categorie categorie = categorieRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Aucun categorie trouvé avec  l ID : %s  ", trackingId)));

        if (!categorie.getLibelle().equals(categorieRequest.libelle())) {
            categorie.setLibelle(categorieRequest.libelle());
        }

        return categorieMapper.toResponse(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public CategorieResponse getOne(UUID trackingId) {

        Categorie categorie = categorieRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Aucun categorie trouvé avec  l ID : %s  ", trackingId)));

        return categorieMapper.toResponse(categorie);
    }

    @Override
    public void delete(UUID trackingId) {

        Categorie categorie = categorieRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Aucun categorie trouvé avec  l ID : %s  ", trackingId)));

        categorieRepository.deleteById(categorie.getId());

    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieResponse> listAll() {

        List<Categorie> categories = categorieRepository.findAll();

        return categories.stream().map(categorieMapper::toResponse).toList();
    }

}
