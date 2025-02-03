package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record NationaliteResponse(
        UUID trackingId,
        String libelle
) {}
