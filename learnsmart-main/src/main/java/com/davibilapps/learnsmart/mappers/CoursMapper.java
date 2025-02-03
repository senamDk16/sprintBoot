package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.CoursRequest;
import com.davibilapps.learnsmart.dto.response.CoursResponse;
import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.MatiereRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CoursMapper {

    private final MatiereRepository matiereRepository;

    public CoursMapper(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public Cours toEntity(CoursRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CoursRequest est nulle.");
        }

        Matiere matiere = matiereRepository.findByTrackingId(request.matiereId())
                .orElseThrow(() -> new ResourceNotFoundException("Ce matiere n'existe pas"));

        return Cours.builder()
                .trackingId(UUID.randomUUID())
                .titre(request.titre())
                .description(request.description())
                .matiere(matiere)
                .build();
    }

    public CoursResponse toResponse(Cours cours) {
        if (cours == null) {
            throw new IllegalArgumentException("Cours est nulle.");
        }

        return CoursResponse.builder()
                .trackingId(cours.getTrackingId())
                .titre(cours.getTitre())
                .description(cours.getDescription())
                .matiereTitre(cours.getMatiere() != null ? cours.getMatiere().getLibelle() : null)
                .matiereId(cours.getMatiere() != null ? cours.getMatiere().getTrackingId() : null)
                .build();
    }

    public Cours toEntityFromResponse(CoursResponse response, Matiere matiere) {
        if (response == null) {
            throw new IllegalArgumentException("CoursResponse est nulle.");
        }

        return Cours.builder()
                .trackingId(response.trackingId())
                .titre(response.titre())
                .description(response.description())
                .matiere(matiere)
                .build();
    }

}
