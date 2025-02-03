package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CoursResponse(
        UUID trackingId,
        String titre,
        String description,
        String matiereTitre,
        UUID matiereId
) {}


