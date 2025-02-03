package com.davibilapps.learnsmart.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ClasseRequest(
        String code,
        String libelle,
        UUID niveauId
) {}
