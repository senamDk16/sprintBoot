package com.davibilapps.learnsmart.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AnneeScolaireRequest(
        LocalDate dateDebut,
        LocalDate dateFin,
        @NotBlank(message = "Le libelle ne peut pas être vide")
        String libelle,
        boolean etat
) {}
