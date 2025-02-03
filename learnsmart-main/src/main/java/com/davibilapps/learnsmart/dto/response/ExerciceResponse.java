package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ExerciceResponse(
        UUID trackingId,
        LocalDate dateDebut,
        LocalDate dateFin,
        String titre,
        UUID matiereId,
        String matiere,
        String niveau,
        UUID niveauId
) {}
