package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CategorieResponse(
        UUID trackingId,
        String libelle
) {}
