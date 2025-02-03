package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.NationaliteRequest;
import com.davibilapps.learnsmart.dto.response.NationaliteResponse;
import com.davibilapps.learnsmart.entity.Nationalite;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.NationaliteMapper;
import com.davibilapps.learnsmart.repository.NationaliteRepository;
import com.davibilapps.learnsmart.services.NationaliteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class NationaliteServiceImpl implements NationaliteService {

    private final NationaliteRepository nationaliteRepository;

    private final NationaliteMapper nationaliteMapper;

    public NationaliteServiceImpl(NationaliteRepository nationaliteRepository, NationaliteMapper nationaliteMapper) {
        this.nationaliteRepository = nationaliteRepository;
        this.nationaliteMapper = nationaliteMapper;
    }


    @Override
    public NationaliteResponse save(NationaliteRequest nationaliteRequest) {

        if (nationaliteRepository.existsByLibelle(nationaliteRequest.libelle())) {
            throw new AlreadyExistException("existe deja ");
        }

        Nationalite nationalite = nationaliteMapper.toEntity(nationaliteRequest);
        Nationalite nationalite1 = nationaliteRepository.save(nationalite);

        return nationaliteMapper.toResponse(nationalite1);
    }

    @Override
    @Transactional
    public NationaliteResponse update(UUID trackingId, NationaliteRequest nationaliteRequest) {

        Nationalite nationalite = nationaliteRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Nationalité avec trackingId %s introuvable.", trackingId)));

        if (!nationalite.getLibelle().equals(nationaliteRequest.libelle())) {
            nationalite.setLibelle(nationaliteRequest.libelle());
        }

        return nationaliteMapper.toResponse(nationalite);
    }

    @Override
    public NationaliteResponse getOne(UUID trackingId) {

        Nationalite nationalite = nationaliteRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Nationalité avec trackingId %s introuvable.", trackingId)));

        return nationaliteMapper.toResponse(nationalite);
    }

    @Override
    public void delete(UUID trackingId) {

        Nationalite nationalite = nationaliteRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Nationalité avec trackingId %s introuvable.", trackingId)));

        nationaliteRepository.delete(nationalite);

    }

    @Override
    public List<NationaliteResponse> listAll() {

        List<Nationalite> nationalites = nationaliteRepository.findAll();

        return nationalites.stream().map(nationaliteMapper::toResponse).toList();
    }
}
