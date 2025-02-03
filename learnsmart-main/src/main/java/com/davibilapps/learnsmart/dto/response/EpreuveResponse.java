package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EpreuveResponse(
        UUID trackingId,
        String titre,
        String contenu,
        UUID annaleId
) {}
