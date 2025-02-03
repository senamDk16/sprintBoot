package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.ReponseEpreuveRequest;
import com.davibilapps.learnsmart.dto.response.ReponseEpreuveResponse;
import com.davibilapps.learnsmart.entity.annaltest.Epreuve;
import com.davibilapps.learnsmart.entity.annaltest.ReponseEpreuve;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.ReponseEpreuveMapper;
import com.davibilapps.learnsmart.repository.annaltest.EpreuveRepository;
import com.davibilapps.learnsmart.repository.annaltest.ReponseEpreuveRepository;
import com.davibilapps.learnsmart.services.ReponseEpreuveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReponseEpreuveServiceImpl implements ReponseEpreuveService {

    private final ReponseEpreuveRepository reponseEpreuveRepository;

    private final ReponseEpreuveMapper reponseEpreuveMapper;

    private final EpreuveRepository epreuveRepository;

    public ReponseEpreuveServiceImpl(ReponseEpreuveRepository reponseEpreuveRepository, ReponseEpreuveMapper reponseEpreuveMapper, EpreuveRepository epreuveRepository) {
        this.reponseEpreuveRepository = reponseEpreuveRepository;
        this.reponseEpreuveMapper = reponseEpreuveMapper;
        this.epreuveRepository = epreuveRepository;
    }

    @Override
    public ReponseEpreuveResponse save(ReponseEpreuveRequest reponseEpreuveRequest) {

        Epreuve epreuve = epreuveRepository.findByTrackingId(reponseEpreuveRequest.epreuveId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse Epreuve avec trackingId %s introuvable.", reponseEpreuveRequest.epreuveId())));


        ReponseEpreuve reponseEpreuve = reponseEpreuveMapper.toEntity(reponseEpreuveRequest);
        ReponseEpreuve reponseEpreuve1 = reponseEpreuveRepository.save(reponseEpreuve);

        return reponseEpreuveMapper.toResponse(reponseEpreuve1);
    }

    @Override
    @Transactional
    public ReponseEpreuveResponse update(UUID trackingId, ReponseEpreuveRequest reponseEpreuveRequest) {

        ReponseEpreuve reponseEpreuve = reponseEpreuveRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : ReponseEpreuve avec trackingId %s introuvable.", trackingId)));

        if (!reponseEpreuve.getIndexation().equals(reponseEpreuveRequest.indexation())){
            reponseEpreuve.setIndexation(reponseEpreuveRequest.indexation());
        }
        if (!reponseEpreuve.getLibelle().equals(reponseEpreuveRequest.libelle())){
            reponseEpreuve.setLibelle(reponseEpreuve.getLibelle());
        }

        if(!reponseEpreuve.getEpreuve().getTrackingId().equals(reponseEpreuveRequest.epreuveId())){
            Epreuve epreuve = epreuveRepository.findByTrackingId(reponseEpreuveRequest.epreuveId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : ReponseEpreuve avec trackingId %s introuvable.", trackingId)));

            reponseEpreuve.setEpreuve(epreuve);

        }

        return reponseEpreuveMapper.toResponse(reponseEpreuve);
    }

    @Override
    public ReponseEpreuveResponse getOne(UUID trackingId) {

        ReponseEpreuve reponseEpreuve = reponseEpreuveRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : ReponseEpreuve avec trackingId %s introuvable.", trackingId)));

        return reponseEpreuveMapper.toResponse(reponseEpreuve);
    }

    @Override
    public void delete(UUID trackingId) {

        ReponseEpreuve reponseEpreuve = reponseEpreuveRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : ReponseEpreuve avec trackingId %s introuvable.", trackingId)));

        reponseEpreuveRepository.delete(reponseEpreuve);

    }

    @Override
    public List<ReponseEpreuveResponse> listAll() {

        List<ReponseEpreuve> reponseEpreuves = reponseEpreuveRepository.findAll();

        return reponseEpreuves.stream().map(reponseEpreuveMapper::toResponse).toList();
    }

    @Override
    public List<ReponseEpreuveResponse> listAllByEpreuve(UUID epreuveID) {

        Epreuve epreuve = epreuveRepository.findByTrackingId(epreuveID).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Epreuve avec trackingId %s introuvable.", epreuveID)));

        List<ReponseEpreuve> reponseEpreuves = reponseEpreuveRepository.findALlByEpreuve(epreuve);

        return reponseEpreuves.stream().map(reponseEpreuveMapper::toResponse).toList();
    }
}
