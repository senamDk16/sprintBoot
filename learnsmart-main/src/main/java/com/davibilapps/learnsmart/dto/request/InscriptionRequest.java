package com.davibilapps.learnsmart.dto.request;

import com.davibilapps.learnsmart.enums.Statut;

import java.time.LocalDate;

public record InscriptionRequest(
        Statut statut,
        LocalDate dateInscription,
        Long eleveId,
        Long classeId,
        Long anneeScolaireId,
        Long etablissementId
) {}
