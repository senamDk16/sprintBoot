package com.davibilapps.learnsmart.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record ExerciceRequest(
        LocalDate dateDebut,
        LocalDate dateFin,
        String titre,
        UUID matiereId,
        UUID niveauId
) {}
