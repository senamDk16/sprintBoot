package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EnseignantResponse(
        UUID trackingId,
        String nom,
        String prenom,
        String contact
) {}
