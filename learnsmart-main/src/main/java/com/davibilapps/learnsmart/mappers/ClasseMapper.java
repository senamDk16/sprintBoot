package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.ClasseRequest;
import com.davibilapps.learnsmart.dto.response.ClasseResponse;
import com.davibilapps.learnsmart.entity.Classe;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.NiveauRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClasseMapper {

    private final NiveauRepository niveauRepository;

    public ClasseMapper(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public Classe toEntity(ClasseRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("ClasseRequest est nulle.");
        }

        Niveau niveau = niveauRepository.findByTrackingId(request.niveauId())
                .orElseThrow(() -> new ResourceNotFoundException("Ce niveau n'existe pas"));

        return Classe.builder()
                .trackingId(UUID.randomUUID())
                .code(request.code())
                .libelle(request.libelle())
                .niveau(niveau)
                .build();
    }

    public ClasseResponse toResponse(Classe classe) {
        if (classe == null) {
            throw new IllegalArgumentException("Classe est nulle.");
        }

        return ClasseResponse.builder()
                .trackingId(classe.getTrackingId())
                .code(classe.getCode())
                .libelle(classe.getLibelle())
                .niveauLibelle(classe.getNiveau() != null ? classe.getNiveau().getLibelle() : null)
                .niveauId(classe.getNiveau() != null ? classe.getNiveau().getTrackingId() : null)
                .build();
    }

    public Classe toEntityFromResponse(ClasseResponse response, Niveau niveau) {
        if (response == null) {
            throw new IllegalArgumentException("ClasseResponse est nulle.");
        }

        return Classe.builder()
                .trackingId(response.trackingId())
                .code(response.code())
                .libelle(response.libelle())
                .niveau(niveau)
                .build();
    }

}
