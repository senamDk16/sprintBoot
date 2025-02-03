package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.MatiereRequest;
import com.davibilapps.learnsmart.dto.response.MatiereResponse;
import com.davibilapps.learnsmart.entity.Categorie;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.TypeMatiere;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.MatiereMapper;
import com.davibilapps.learnsmart.repository.CategorieRepository;
import com.davibilapps.learnsmart.repository.MatiereRepository;
import com.davibilapps.learnsmart.repository.TypeMatiereRepository;
import com.davibilapps.learnsmart.services.MatiereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class MatiereServiceImpl implements MatiereService {

    private final MatiereRepository matiereRepository;

    private final MatiereMapper matiereMapper;

    private final TypeMatiereRepository typeMatiereRepository;

    private final CategorieRepository categorieRepository;

    public MatiereServiceImpl(MatiereRepository matiereRepository, MatiereMapper matiereMapper, TypeMatiereRepository typeMatiereRepository, CategorieRepository categorieRepository) {
        this.matiereRepository = matiereRepository;
        this.matiereMapper = matiereMapper;
        this.typeMatiereRepository = typeMatiereRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public MatiereResponse save(MatiereRequest matiereRequest) {

        TypeMatiere typeMatiere = typeMatiereRepository.findByTrackingId(matiereRequest.typeMatiereId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Type de Matière avec trackingId %s introuvable.", matiereRequest.typeMatiereId())));

        Categorie categorie = categorieRepository.findByTrackingId(matiereRequest.typeMatiereId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Categorie avec trackingId %s introuvable.", matiereRequest.categorieId())));


        if (matiereRepository.existsByLibelle(matiereRequest.libelle())) {
            throw new AlreadyExistException("le libelle existe deja");
        }

        Matiere matiere = matiereMapper.toEntity(matiereRequest);

        Matiere matiere1 = matiereRepository.save(matiere);

        return matiereMapper.toResponse(matiere1);
    }

    @Override
    @Transactional
    public MatiereResponse update(UUID trackingId, MatiereRequest matiereRequest) {

        Matiere matiere = matiereRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(" erreur UUID : " + trackingId));

        if (!matiere.getLibelle().equals(matiereRequest.libelle())) {
            matiere.setLibelle(matiereRequest.libelle());
        }

        if (!matiere.getCode().equals(matiereRequest.code())) {
            matiere.setCode(matiere.getCode());
        }

        if (!matiere.getTypeMatiere().getTrackingId().equals(matiereRequest.typeMatiereId())) {

            TypeMatiere typeMatiere = typeMatiereRepository.findByTrackingId(matiereRequest.typeMatiereId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Type de Matière avec trackingId %s introuvable.", matiereRequest.typeMatiereId())));
            matiere.setTypeMatiere(typeMatiere);

        }

        if (!matiere.getCategorie().getTrackingId().equals(matiereRequest.categorieId())) {

            Categorie categorie = categorieRepository.findByTrackingId(matiereRequest.typeMatiereId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Categorie avec trackingId %s introuvable.", matiereRequest.categorieId())));
            matiere.setCategorie(categorie);
        }
        return matiereMapper.toResponse(matiere);
    }


    @Override
    public MatiereResponse getOne(UUID trackingId) {
        Matiere matiere = matiereRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(" erreur UUID : " + trackingId));

        return matiereMapper.toResponse(matiere);
    }

    @Override
    public void delete(UUID trackingId) {

        Matiere matiere = matiereRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(" erreur UUID : " + trackingId));

        matiereRepository.delete(matiere);

    }

    @Override
    public List<MatiereResponse> listAll() {

        List<Matiere> matieres = matiereRepository.findAll();

        return matieres.stream().map(matiereMapper::toResponse).toList();
    }

    @Override
    public List<MatiereResponse> listAllByCategorie(UUID categorieID) {

        Categorie categorie = categorieRepository.findByTrackingId(categorieID).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Categorie avec trackingId %s introuvable.", categorieID)));

        List<Matiere> matieres = matiereRepository.findAllByCategorieOrderByLibelle(categorie);

        return matieres.stream().map(matiereMapper::toResponse).toList();
    }

    @Override
    public List<MatiereResponse> listAllByType(UUID typeID) {

        TypeMatiere typeMatiere = typeMatiereRepository.findByTrackingId(typeID).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Type de Matière avec trackingId %s introuvable.", typeID)));

        List<Matiere> matieres = matiereRepository.findAllByTypeMatiereOrderByLibelle(typeMatiere);

        return matieres.stream().map(matiereMapper::toResponse).toList();
    }
}
