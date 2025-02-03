package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record EpreuveRequest(
        String titre,
        String contenu,
        UUID annaleId
) {}

