package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.CycleRequest;
import com.davibilapps.learnsmart.dto.response.CycleResponse;
import com.davibilapps.learnsmart.entity.Cycle;
import com.davibilapps.learnsmart.entity.Etablissement;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.EtablissementRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CycleMapper {

    private final EtablissementRepository etablissementRepository;

    public CycleMapper(EtablissementRepository etablissementRepository) {
        this.etablissementRepository = etablissementRepository;
    }

    public Cycle toEntity(CycleRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CycleRequest est nulle.");
        }

        Etablissement etablissement = etablissementRepository.findByTrackingId(request.etablissementId())
                .orElseThrow(() -> new ResourceNotFoundException("Cet etablissement n'existe pas"));

        return Cycle.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .etablissement(etablissement)
                .build();
    }

    public CycleResponse toResponse(Cycle cycle) {
        if (cycle == null) {
            throw new IllegalArgumentException("Cycle est nulle.");
        }

        return CycleResponse.builder()
                .trackingId(cycle.getTrackingId())
                .libelle(cycle.getLibelle())
                .etablissementNom(cycle.getEtablissement() != null ? cycle.getEtablissement().getNom() : null)
                .etablissementId(cycle.getEtablissement() != null ? cycle.getEtablissement().getTrackingId() : null)
                .build();
    }

    public Cycle toEntityFromResponse(CycleResponse response, Etablissement etablissement) {
        if (response == null) {
            throw new IllegalArgumentException("CycleResponse est nulle.");
        }

        return Cycle.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle())
                .etablissement(etablissement)
                .build();
    }

}
