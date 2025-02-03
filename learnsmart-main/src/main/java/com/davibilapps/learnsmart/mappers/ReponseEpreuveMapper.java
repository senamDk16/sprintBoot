package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.ReponseEpreuveRequest;
import com.davibilapps.learnsmart.dto.response.ReponseEpreuveResponse;
import com.davibilapps.learnsmart.entity.annaltest.Epreuve;
import com.davibilapps.learnsmart.entity.annaltest.ReponseEpreuve;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.annaltest.EpreuveRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReponseEpreuveMapper {

    private final EpreuveRepository epreuveRepository;

    public ReponseEpreuveMapper(EpreuveRepository epreuveRepository) {
        this.epreuveRepository = epreuveRepository;
    }

    public ReponseEpreuveResponse toResponse(ReponseEpreuve reponseEpreuve) {
        if (reponseEpreuve == null) {
            throw new IllegalArgumentException("ReponseEpreuve est nulle.");
        }

        return ReponseEpreuveResponse.builder()
                .trackingId(reponseEpreuve.getTrackingId())
                .indexation(reponseEpreuve.getIndexation())
                .etat(reponseEpreuve.isEtat())
                .libelle(reponseEpreuve.getLibelle())
                .epreuveId(reponseEpreuve.getEpreuve() != null ? reponseEpreuve.getEpreuve().getTrackingId() : null)
                .epreuveName(reponseEpreuve.getEpreuve() != null ? reponseEpreuve.getEpreuve().getTitre() : null) // Supposons que Epreuve a un champ `getTitre()`
                .build();
    }

    public ReponseEpreuve toEntity(ReponseEpreuveRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("ReponseEpreuveRequest est nulle.");
        }

        Epreuve epreuve = epreuveRepository.findByTrackingId(request.epreuveId())
                .orElseThrow(() -> new ResourceNotFoundException("Epreuve non trouvée"));

        return ReponseEpreuve.builder()
                .trackingId(UUID.randomUUID())
                .indexation(request.indexation())
                .etat(request.etat())
                .libelle(request.libelle())
                .epreuve(epreuve)
                .build();
    }


}
