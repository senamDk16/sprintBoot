package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.EnseignantRequest;
import com.davibilapps.learnsmart.dto.response.EnseignantResponse;
import com.davibilapps.learnsmart.entity.Enseignant;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EnseignantMapper {


    public Enseignant toEntity(EnseignantRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("EnseignantRequest est null.");
        }

        return Enseignant.builder()
                .trackingId(UUID.randomUUID())
                .nom(request.nom())
                .prenom(request.prenom())
                .contact(request.contact())
                .build();
    }


    public EnseignantResponse toResponse(Enseignant enseignant) {
        if (enseignant == null) {
            throw new IllegalArgumentException("Enseignant est null.");
        }

        return new EnseignantResponse(
                enseignant.getTrackingId(),
                enseignant.getNom(),
                enseignant.getPrenom(),
                enseignant.getContact()
        );
    }


    public static Enseignant toEntityFromResponse(EnseignantResponse response) {
        if (response == null) {
            return null;
        }

        return Enseignant.builder()
                .trackingId(response.trackingId())
                .nom(response.nom())
                .prenom(response.prenom())
                .contact(response.contact())
                .build();
    }
}
