package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.NiveauRequest;
import com.davibilapps.learnsmart.dto.response.NiveauResponse;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.entity.Serie;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.SerieRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NiveauMapper {

    private final SerieRepository serieRepository;

    public NiveauMapper(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public Niveau toEntity(NiveauRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("NiveauRequest est nulle.");
        }

        Serie serie = serieRepository.findByTrackingId(request.serieId())
                .orElseThrow(() -> new ResourceNotFoundException("Cette serie n'existe pas"));

        return Niveau.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .serie(serie)
                .build();
    }

    public NiveauResponse toResponse(Niveau niveau) {
        if (niveau == null) {
            throw new IllegalArgumentException("Niveau est nulle.");
        }

        return NiveauResponse.builder()
                .trackingId(niveau.getTrackingId())
                .libelle(niveau.getLibelle())
                .serieLibelle(niveau.getSerie() != null ? niveau.getSerie().getLibelle() : null)
                .serieId(niveau.getSerie() != null ? niveau.getSerie().getTrackingId() : null)
                .build();
    }

    public Niveau toEntityFromResponse(NiveauResponse response, Serie serie) {
        if (response == null) {
            throw new IllegalArgumentException("NiveauResponse est nulle.");
        }

        return Niveau.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle())
                .serie(serie)
                .build();
    }

}
