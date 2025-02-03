package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record CoursRequest(
        String titre,
        String description,
        UUID matiereId
) {}

