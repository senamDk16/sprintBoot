package com.davibilapps.learnsmart.dto.request;

public record EtablissementRequest(
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
