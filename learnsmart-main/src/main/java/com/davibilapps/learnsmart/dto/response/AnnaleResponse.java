package com.davibilapps.learnsmart.dto.response;

import java.util.UUID;

public record AnnaleResponse(
        UUID trackingId,
        String annee,
        String notion,
        Long typeAnnaleId,
        Long matiereId,
        String matiere,
        Long niveauId,
        String niveau
) {}
