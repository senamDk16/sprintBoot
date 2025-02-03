package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.AnneeScolaireRequest;
import com.davibilapps.learnsmart.dto.request.NationaliteRequest;
import com.davibilapps.learnsmart.dto.response.AnneeScolaireResponse;
import com.davibilapps.learnsmart.dto.response.NationaliteResponse;
import com.davibilapps.learnsmart.entity.AnneeScolaire;
import com.davibilapps.learnsmart.entity.Nationalite;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnneeScolaireMapper {


    public AnneeScolaire toEntity(AnneeScolaireRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La requête AnneeScolaire est nulle.");
        }

        return AnneeScolaire.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .dateDebut(request.dateDebut())
                .dateFin(request.dateFin())
                .etat(request.etat())
                .build();
    }


    public AnneeScolaireResponse toResponse(AnneeScolaire anneeScolaire) {
        if (anneeScolaire == null) {
            throw new IllegalArgumentException("L'entité AnneeScolaire est nulle.");
        }

        return  AnneeScolaireResponse.builder()
                .dateDebut(anneeScolaire.getDateDebut())
                .dateFin(anneeScolaire.getDateFin())
                .etat(anneeScolaire.isEtat())
                .trackingId(anneeScolaire.getTrackingId())
                .build();


    }


    public AnneeScolaire toEntityFromResponse(AnneeScolaireResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("La réponse AnneeScolaire est nulle.");
        }

        return AnneeScolaire.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle())
                .etat(response.etat())
                .build();
    }
}
