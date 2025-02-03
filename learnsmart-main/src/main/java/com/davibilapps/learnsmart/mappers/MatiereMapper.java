package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.MatiereRequest;
import com.davibilapps.learnsmart.dto.response.MatiereResponse;
import com.davibilapps.learnsmart.entity.Categorie;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.TypeMatiere;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.CategorieRepository;
import com.davibilapps.learnsmart.repository.TypeMatiereRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MatiereMapper {

    private final CategorieRepository categorieRepository;
    private final TypeMatiereRepository typeMatiereRepository;

    public MatiereMapper(CategorieRepository categorieRepository, TypeMatiereRepository typeMatiereRepository) {
        this.categorieRepository = categorieRepository;
        this.typeMatiereRepository = typeMatiereRepository;
    }

    public Matiere toEntity(MatiereRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("MatiereRequest est nulle.");
        }

        Categorie categorie = categorieRepository.findByTrackingId(request.categorieId())
                .orElseThrow(() -> new ResourceNotFoundException("Ce categorie n'existe pas"));

        TypeMatiere typeMatiere = typeMatiereRepository.findByTrackingId(request.typeMatiereId())
                .orElseThrow(() -> new ResourceNotFoundException("Ce Type de matiere n'existe pas"));

        return Matiere.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .categorie(categorie)
                .typeMatiere(typeMatiere)
                .build();
    }

    public MatiereResponse toResponse(Matiere matiere) {
        if (matiere == null) {
            throw new IllegalArgumentException("Matiere est nulle.");
        }

        return MatiereResponse.builder()
                .trackingId(matiere.getTrackingId())
                .libelle(matiere.getLibelle())
                .categorieName(matiere.getCategorie() != null ? matiere.getCategorie().getLibelle() : null)
                .typeMatiereName(matiere.getTypeMatiere() != null ? matiere.getTypeMatiere().getLibelle() : null)
                .categorieId(matiere.getCategorie() != null ? matiere.getCategorie().getTrackingId() : null)
                .typeMatiereId(matiere.getTypeMatiere() != null ? matiere.getTypeMatiere().getTrackingId() : null)
                .build();
    }

    public Matiere toEntityFromResponse(MatiereResponse response, Categorie categorie,TypeMatiere typeMatiere) {
        if (response == null) {
            throw new IllegalArgumentException("MatiereResponse est nulle.");
        }

        return Matiere.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle())
                .categorie(categorie)
                .typeMatiere(typeMatiere)
                .build();
    }

}
