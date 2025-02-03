package com.davibilapps.learnsmart.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record SolutionExerciceRequest(

        LocalDate dateTraitement,
        String titre,
        int score,
        boolean modifiable,
        UUID questionId,
        UUID inscriptionId
) {}
