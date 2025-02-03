package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record QuestionRequest(
        String ordre,
        String contenu,
        UUID exerciceId,
        UUID coursId
) {}

