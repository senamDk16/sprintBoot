package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReponseEpreuveResponse(
        UUID trackingId,
        String indexation,
        boolean etat,
        String libelle,
        UUID epreuveId,
        String epreuveName
) {}

