package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record SerieRequest(
        String libelle,
        UUID cycleId
) {}

