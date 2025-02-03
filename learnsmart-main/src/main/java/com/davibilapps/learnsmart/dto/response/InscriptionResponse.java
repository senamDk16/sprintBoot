package com.davibilapps.learnsmart.dto.response;

import com.davibilapps.learnsmart.enums.Statut;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record InscriptionResponse(
        UUID trackingId,
        Statut statut,
        LocalDate dateInscription,
        Long eleveId,
        String nomEleve,
        Long classeId,
        String classe,
        Long anneeScolaireId,
        Long etablissementId
) {}
