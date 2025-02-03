package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record QuestionResponse(
        UUID trackingId,
        String ordre,
        String contenu,
        UUID exerciceId,
        UUID coursId,
        String cours
) {}
