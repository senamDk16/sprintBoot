package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClasseResponse(
        UUID trackingId,
        String code,
        String libelle,
        String niveauLibelle,
        UUID niveauId
) {}
