package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record MatiereRequest(

        String code,
        String libelle,
        UUID categorieId,
        UUID typeMatiereId
) {}
