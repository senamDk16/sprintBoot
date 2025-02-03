package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DocumentResponse(
        UUID trackingId,
        String path,
        String name,
        boolean etat,
        String questionLibelle,
        UUID questionId
) {}
