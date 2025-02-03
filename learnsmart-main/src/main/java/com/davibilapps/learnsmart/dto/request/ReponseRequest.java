package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record ReponseRequest(
        String indexation,
        boolean etat, String libelle,
        UUID questionId
) {}
