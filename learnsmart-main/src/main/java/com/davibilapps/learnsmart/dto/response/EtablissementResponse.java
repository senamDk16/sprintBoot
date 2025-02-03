package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EtablissementResponse(
        UUID trackingId,
        String nom,
        String slogan,
        String telephone1,
        String telephone2,
        String email1,
        String email2,
        String site,
        String fondateur,
        boolean etat
) {}
