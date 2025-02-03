package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record DocumentRequest(
        String path,
        String name,
        boolean etat,
        UUID questionTrackingId
) {}
