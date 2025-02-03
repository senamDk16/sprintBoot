package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record SolutionExerciceResponse(
        UUID trackingId,
        LocalDate dateTraitement,
        String titre,
        int score,
        boolean modifiable,
        UUID questionId,
        String ordre,
        UUID inscriptionId,
        String nomEleve
) {}
