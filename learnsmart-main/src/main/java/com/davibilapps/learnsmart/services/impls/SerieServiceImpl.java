package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.SerieRequest;
import com.davibilapps.learnsmart.dto.response.SerieResponse;
import com.davibilapps.learnsmart.entity.Cycle;
import com.davibilapps.learnsmart.entity.Serie;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.SerieMapper;
import com.davibilapps.learnsmart.repository.CycleRepository;
import com.davibilapps.learnsmart.repository.SerieRepository;
import com.davibilapps.learnsmart.services.SerieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SerieServiceImpl implements SerieService {

    private final SerieRepository serieRepository;

    private final SerieMapper serieMapper;

    private final CycleRepository cycleRepository;

    public SerieServiceImpl(SerieRepository serieRepository, SerieMapper serieMapper, CycleRepository cycleRepository) {
        this.serieRepository = serieRepository;
        this.serieMapper = serieMapper;
        this.cycleRepository = cycleRepository;
    }

    @Override
    public SerieResponse save(SerieRequest serieRequest) {

        if (serieRepository.existsByLibelle(serieRequest.libelle())) {
            throw new AlreadyExistException("existe deja");
        }

        Serie serie = serieMapper.toEntity(serieRequest);
        Serie serie1 = serieRepository.save(serie);

        return serieMapper.toResponse(serie1);
    }

    @Override
    @Transactional
    public SerieResponse update(UUID trackingId, SerieRequest serieRequest) {

        Serie serie = serieRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", trackingId)));

        if (!serie.getLibelle().equals(serieRequest.libelle())) {
            serie.setLibelle(serieRequest.libelle());
        }

        if (!serie.getCycle().getTrackingId().equals(serieRequest.cycleId())) {

            Cycle cycle = cycleRepository.findByTrackingId(serieRequest.cycleId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", serieRequest.cycleId())));

            serie.setCycle(cycle);

        }

        return serieMapper.toResponse(serie);
    }

    //     String libelle,
    //        UUID cycleId

    @Override
    public SerieResponse getOne(UUID trackingId) {

        Serie serie = serieRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", trackingId)));

        return serieMapper.toResponse(serie);
    }

    @Override
    public void delete(UUID trackingId) {

        Serie serie = serieRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", trackingId)));

        serieRepository.delete(serie);

    }

    @Override
    public List<SerieResponse> listAll() {

        List<Serie> series = serieRepository.findAll();

        return series.stream().map(serieMapper::toResponse).toList();
    }

    @Override
    public List<SerieResponse> listAllByCycle(UUID cycle) {

        Cycle cycle1 = cycleRepository.findByTrackingId(cycle).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", cycle)));

        List<Serie> series = serieRepository.findAllByCycleOrderByLibelle(cycle1);

        return series.stream().map(serieMapper::toResponse).toList();
    }
}
