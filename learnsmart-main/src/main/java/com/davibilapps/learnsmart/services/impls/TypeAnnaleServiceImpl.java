package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.TypeAnnaleRequest;
import com.davibilapps.learnsmart.dto.response.TypeAnnaleResponse;
import com.davibilapps.learnsmart.entity.annaltest.TypeAnnale;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.TypeAnnaleMapper;
import com.davibilapps.learnsmart.repository.annaltest.TypeAnnaleRepository;
import com.davibilapps.learnsmart.services.TypeAnnaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TypeAnnaleServiceImpl implements TypeAnnaleService {

    private final TypeAnnaleRepository typeAnnaleRepository;

    private final TypeAnnaleMapper typeAnnaleMapper;

    public TypeAnnaleServiceImpl(TypeAnnaleRepository typeAnnaleRepository, TypeAnnaleMapper typeAnnaleMapper) {
        this.typeAnnaleRepository = typeAnnaleRepository;
        this.typeAnnaleMapper = typeAnnaleMapper;
    }

    @Override
    public TypeAnnaleResponse save(TypeAnnaleRequest typeAnnaleRequest) {

        if (typeAnnaleRepository.existsByLibelle(typeAnnaleRequest.libelle())) {
            throw new AlreadyExistException("existe deja");

        }
        TypeAnnale annale = typeAnnaleMapper.toEntity(typeAnnaleRequest);

        TypeAnnale typeAnnale = typeAnnaleRepository.save(annale);

        return typeAnnaleMapper.toResponse(typeAnnale);
    }

    @Override
    @Transactional
    public TypeAnnaleResponse update(UUID trackingId, TypeAnnaleRequest typeAnnaleRequest) {

        TypeAnnale typeAnnale = typeAnnaleRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        if (!typeAnnale.getLibelle().equals(typeAnnaleRequest.libelle())) {

            typeAnnale.setLibelle(typeAnnaleRequest.libelle());

        }

        return typeAnnaleMapper.toResponse(typeAnnale);
    }

    @Override
    public TypeAnnaleResponse getOne(UUID trackingId) {

        TypeAnnale typeAnnale = typeAnnaleRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        return typeAnnaleMapper.toResponse(typeAnnale);
    }

    @Override
    public void delete(UUID trackingId) {

        TypeAnnale typeAnnale = typeAnnaleRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));
        typeAnnaleRepository.delete(typeAnnale);

    }

    @Override
    public List<TypeAnnaleResponse> listAll() {

        List<TypeAnnale> typeAnnales = typeAnnaleRepository.findAll();

        return typeAnnales.stream().map(typeAnnaleMapper::toResponse).toList();
    }
}
