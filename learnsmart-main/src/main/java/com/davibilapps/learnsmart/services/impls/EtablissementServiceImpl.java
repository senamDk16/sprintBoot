package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.EtablissementRequest;
import com.davibilapps.learnsmart.dto.response.EtablissementResponse;
import com.davibilapps.learnsmart.entity.Etablissement;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.EtablissementMapper;
import com.davibilapps.learnsmart.repository.EtablissementRepository;
import com.davibilapps.learnsmart.services.EtablissementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EtablissementServiceImpl implements EtablissementService {

    private final EtablissementRepository etablissementRepository;

    private final EtablissementMapper etablissementMapper;

    public EtablissementServiceImpl(EtablissementRepository etablissementRepository, EtablissementMapper etablissementMapper) {
        this.etablissementRepository = etablissementRepository;
        this.etablissementMapper = etablissementMapper;
    }

    @Override
    public EtablissementResponse save(EtablissementRequest nationaliteRequest) {

        if (etablissementRepository.existsByNom(nationaliteRequest.nom())) {
            throw new AlreadyExistException("le nom existe deja");
        }

        Etablissement etablissement = etablissementMapper.toEntity(nationaliteRequest);
        Etablissement etablissement1 = etablissementRepository.save(etablissement);

        return etablissementMapper.toResponse(etablissement1);
    }

    @Override
    @Transactional
    public EtablissementResponse update(UUID trackingId, EtablissementRequest nationaliteRequest) {

        Etablissement etablissement = etablissementRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur UUID :%s", trackingId)));

        if (!etablissement.getNom().equals(nationaliteRequest.nom())) {
            etablissement.setNom(nationaliteRequest.nom());
        }
        if (!etablissement.getSlogan().equals(nationaliteRequest.slogan())) {
            etablissement.setSlogan(nationaliteRequest.slogan());
        }

        if (!etablissement.getEmail1().equals(nationaliteRequest.email1())) {
            etablissement.setEmail1(nationaliteRequest.email1());
        }

        if (!etablissement.getEmail2().equals(nationaliteRequest.email2())) {
            etablissement.setEmail2(nationaliteRequest.email2());
        }

        if (!etablissement.getFondateur().equals(nationaliteRequest.fondateur())) {
            etablissement.setFondateur(nationaliteRequest.fondateur());
        }

        if (!etablissement.getTelephone1().equals(nationaliteRequest.telephone1())) {
            etablissement.setTelephone1(nationaliteRequest.telephone1());
        }

        if (!etablissement.getTelephone2().equals(nationaliteRequest.telephone1())) {
            etablissement.setTelephone2(nationaliteRequest.telephone1());
        }

        if (!etablissement.getSite().equals(nationaliteRequest.site())) {
            etablissement.setSite(nationaliteRequest.site());
        }

        return etablissementMapper.toResponse(etablissement);
    }


    @Override
    public EtablissementResponse getOne(UUID trackingId) {

        Etablissement etablissement = etablissementRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur UUID :%s", trackingId)));

        return etablissementMapper.toResponse(etablissement);
    }

    @Override
    public void delete(UUID trackingId) {

        Etablissement etablissement = etablissementRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur UUID :%s", trackingId)));

        etablissementRepository.delete(etablissement);

    }

    @Override
    public List<EtablissementResponse> listAll() {

        List<Etablissement> etablissements = etablissementRepository.findAll();

        return etablissements.stream().map(etablissementMapper::toResponse).toList();
    }
}
