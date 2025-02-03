package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReponseResponse(
        UUID trackingId,
        String indexation,
        boolean etat,
        String libelle,
        UUID questionId,
        String titre
) {}
