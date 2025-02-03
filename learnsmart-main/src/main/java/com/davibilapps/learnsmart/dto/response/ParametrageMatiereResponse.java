package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ParametrageMatiereResponse(
        UUID trackingId,
        int coef,
        UUID matiereId,
        UUID classeId,
        String classeName,
        UUID enseignantId,
        String enseignantName,
        UUID etablissementId,
        String etablissementName,
        UUID anneeScolaireId
) {}
