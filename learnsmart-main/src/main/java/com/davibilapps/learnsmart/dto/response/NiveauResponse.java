package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record NiveauResponse(
        UUID trackingId,
        String libelle,
        String serieLibelle,
        UUID serieId
) {}
