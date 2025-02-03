package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.CycleRequest;
import com.davibilapps.learnsmart.dto.response.CycleResponse;
import com.davibilapps.learnsmart.entity.Cycle;
import com.davibilapps.learnsmart.entity.Etablissement;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.CycleMapper;
import com.davibilapps.learnsmart.repository.CycleRepository;
import com.davibilapps.learnsmart.repository.EtablissementRepository;
import com.davibilapps.learnsmart.services.CycleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CycleServiceImpl implements CycleService {

    private final CycleRepository cycleRepository;

    private final CycleMapper cycleMapper;

    private final EtablissementRepository etablissementRepository;

    public CycleServiceImpl(CycleRepository cycleRepository, CycleMapper cycleMapper, EtablissementRepository etablissementRepository) {
        this.cycleRepository = cycleRepository;
        this.cycleMapper = cycleMapper;
        this.etablissementRepository = etablissementRepository;
    }

    @Override
    public CycleResponse save(CycleRequest cycleRequest) {

        Etablissement etablissement = etablissementRepository.findByTrackingId(cycleRequest.etablissementId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID ", cycleRequest.etablissementId())));


        if (cycleRepository.existsByLibelle(cycleRequest.libelle())) {
            throw new AlreadyExistException("cycle existe deja");
        }

        Cycle cycle = cycleMapper.toEntity(cycleRequest);

        Cycle cycle1 = cycleRepository.save(cycle);

        return cycleMapper.toResponse(cycle1);
    }

    @Override
    @Transactional
    public CycleResponse update(UUID trackingId, CycleRequest cycleRequest) {

        Cycle cycle = cycleRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format(" eurreur trackingId : %s", trackingId)));

        if (!cycle.getLibelle().equals(cycleRequest.libelle())) {
            cycle.setLibelle(cycleRequest.libelle());
        }

        if (!cycle.getEtablissement().getTrackingId().equals(cycleRequest.etablissementId())) {
            Etablissement etablissement = etablissementRepository.findByTrackingId(cycleRequest.etablissementId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID ", trackingId)));

            cycle.setEtablissement(etablissement);
        }


        return cycleMapper.toResponse(cycle);
    }

    @Override
    public CycleResponse getOne(UUID trackingId) {

        Cycle cycle = cycleRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format(" eurreur trackingId : %s", trackingId)));

        return cycleMapper.toResponse(cycle);
    }

    @Override
    public void delete(UUID trackingId) {
        Cycle cycle = cycleRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format(" eurreur trackingId : %s", trackingId)));

        cycleRepository.delete(cycle);

    }

    @Override
    public List<CycleResponse> listAll() {
        List<Cycle> cycles = cycleRepository.findAll();
        return cycles.stream().map(cycleMapper::toResponse).toList();
    }

    @Override
    public List<CycleResponse> listAllByEtablissement(UUID etablissement) {

        Etablissement etablissement1 = etablissementRepository.findByTrackingId(etablissement).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID ", etablissement)));

        List<Cycle> cycles = cycleRepository.findAllByEtablissementOrderByLibelle(etablissement1);

        return cycles.stream().map(cycleMapper::toResponse).toList();
    }
}
