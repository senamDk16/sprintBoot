package com.davibilapps.learnsmart.dto.request;

import com.davibilapps.learnsmart.enums.Genre;
import com.davibilapps.learnsmart.enums.Statut;

import java.time.LocalDate;
import java.util.UUID;

public record EleveRequest(
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
        UUID nationaliteTrackingId,
        Statut statut,
        LocalDate dateInscription,
        UUID classeId,
        UUID anneeScolaireId,
        UUID etablissementId
) {}
