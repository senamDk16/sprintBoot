package com.davibilapps.learnsmart.dto.request;

public record AnnaleRequest(

        String annee,String notion,
        Long typeAnnaleId,
        Long matiereId,
        Long niveauId
) {}

