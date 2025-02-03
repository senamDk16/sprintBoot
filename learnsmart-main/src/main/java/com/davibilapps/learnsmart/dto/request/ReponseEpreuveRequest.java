package com.davibilapps.learnsmart.dto.request;

import java.util.UUID;

public record ReponseEpreuveRequest(

        String indexation,
        boolean etat,String libelle,
        UUID epreuveId
) {}
