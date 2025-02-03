package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record NiveauRequest(
        String libelle,
        UUID serieId
) {}

