package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.TypeMatiereRequest;
import com.davibilapps.learnsmart.dto.response.TypeMatiereResponse;
import com.davibilapps.learnsmart.entity.TypeMatiere;
import com.davibilapps.learnsmart.entity.annaltest.TypeAnnale;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.TypeMatiereMapper;
import com.davibilapps.learnsmart.repository.TypeMatiereRepository;
import com.davibilapps.learnsmart.services.TypeMatiereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TypeMatiereServiceImpl implements TypeMatiereService {


    private final TypeMatiereRepository typeMatiereRepository;

    private final TypeMatiereMapper typeMatiereMapper;

    public TypeMatiereServiceImpl(TypeMatiereRepository typeMatiereRepository, TypeMatiereMapper typeMatiereMapper) {
        this.typeMatiereRepository = typeMatiereRepository;
        this.typeMatiereMapper = typeMatiereMapper;
    }

    @Override
    public TypeMatiereResponse save(TypeMatiereRequest typeMatiereRequest) {

        if (typeMatiereRepository.existsByLibelle(typeMatiereRequest.libelle())) {

            throw new AlreadyExistException("existe deja");

        }

        TypeMatiere typeMatiere = typeMatiereMapper.toEntity(typeMatiereRequest);

        TypeMatiere matiere = typeMatiereRepository.save(typeMatiere);

        return typeMatiereMapper.toResponse(matiere);
    }

    @Override
    @Transactional
    public TypeMatiereResponse update(UUID trackingId, TypeMatiereRequest typeMatiereRequest) {

        TypeMatiere matiere = typeMatiereRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        if (!matiere.getLibelle().equals(typeMatiereRequest.libelle())) {
            matiere.setLibelle(typeMatiereRequest.libelle());
        }

        return typeMatiereMapper.toResponse(matiere);
    }

    @Override
    public TypeMatiereResponse getOne(UUID trackingId) {
        TypeMatiere matiere = typeMatiereRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        return typeMatiereMapper.toResponse(matiere);
    }

    @Override
    public void delete(UUID trackingId) {

        TypeMatiere matiere = typeMatiereRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        typeMatiereRepository.delete(matiere);

    }

    @Override
    public List<TypeMatiereResponse> listAll() {

        List<TypeMatiere> matieres = typeMatiereRepository.findAll();

        return matieres.stream().map(typeMatiereMapper::toResponse).toList();
    }
}
