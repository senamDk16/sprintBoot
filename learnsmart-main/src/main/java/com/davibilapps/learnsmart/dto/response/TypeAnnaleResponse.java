package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TypeAnnaleResponse(
        UUID trackingId,
        String libelle
) {}
