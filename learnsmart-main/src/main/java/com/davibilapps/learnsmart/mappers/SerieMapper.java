package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.SerieRequest;
import com.davibilapps.learnsmart.dto.response.SerieResponse;
import com.davibilapps.learnsmart.entity.Serie;
import com.davibilapps.learnsmart.entity.Cycle;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.CycleRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SerieMapper {

    private final CycleRepository cycleRepository;

    public SerieMapper(CycleRepository cycleRepository) {
        this.cycleRepository = cycleRepository;
    }

    public Serie toEntity(SerieRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("SerieRequest est nulle.");
        }

        Cycle cycle = cycleRepository.findByTrackingId(request.cycleId())
                .orElseThrow(() -> new ResourceNotFoundException("Ce cycle n'existe pas"));

        return Serie.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .cycle(cycle)
                .build();
    }

    public SerieResponse toResponse(Serie serie) {
        if (serie == null) {
            throw new IllegalArgumentException("Serie est nulle.");
        }

        return SerieResponse.builder()
                .trackingId(serie.getTrackingId())
                .libelle(serie.getLibelle())
                .cycle(serie.getCycle() != null ? serie.getCycle().getLibelle() : null)
                .cycleId(serie.getCycle() != null ? serie.getCycle().getTrackingId() : null)
                .build();
    }

    public Serie toEntityFromResponse(SerieResponse response, Cycle cycle) {
        if (response == null) {
            throw new IllegalArgumentException("SerieResponse est nulle.");
        }

        return Serie.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle())
                .cycle(cycle)
                .build();
    }

}
