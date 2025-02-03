package com.davibilapps.learnsmart.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record AnneeScolaireResponse(
        UUID trackingId,
        LocalDate dateDebut,
        LocalDate dateFin,
        String libelle,
        boolean etat
) {}
