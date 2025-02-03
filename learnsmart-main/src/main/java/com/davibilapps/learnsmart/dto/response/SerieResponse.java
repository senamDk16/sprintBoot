package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SerieResponse(
        UUID trackingId,
        String libelle,
        UUID cycleId,
        String cycle
) {}
