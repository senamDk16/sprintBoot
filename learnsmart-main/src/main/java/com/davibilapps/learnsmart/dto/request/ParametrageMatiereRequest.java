package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record ParametrageMatiereRequest(

        int coef,
        UUID matiereId,
        UUID classeId,
        UUID enseignantId,
        UUID etablissementId,
        UUID anneeScolaireId
) {}
