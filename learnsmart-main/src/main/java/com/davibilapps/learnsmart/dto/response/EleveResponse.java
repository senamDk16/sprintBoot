package com.davibilapps.learnsmart.dto.response;

import com.davibilapps.learnsmart.enums.Genre;
import com.davibilapps.learnsmart.enums.Statut;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record EleveResponse(

        UUID trackingId,
        String matricule,
        String nom,
        String prenom,
        Genre sexe,
        LocalDate dateNaissance,
        String lieuNaissance,
        String contact,
        String contact2,
        String email,
        String email2,
        String nationaliteLibelle,
        UUID nationaliteId

) {}
