package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MatiereResponse(
        UUID trackingId,
        String code,
        String libelle,
        UUID categorieId,
        String categorieName,
        UUID typeMatiereId,
        String typeMatiereName
) {}
