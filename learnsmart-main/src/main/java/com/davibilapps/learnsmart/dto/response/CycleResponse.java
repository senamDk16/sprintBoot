package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CycleResponse(
        UUID trackingId,
        String libelle,
        String etablissementNom,
        UUID etablissementId
) {}
