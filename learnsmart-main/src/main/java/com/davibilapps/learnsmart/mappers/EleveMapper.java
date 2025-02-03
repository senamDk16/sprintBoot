package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.EleveRequest;
import com.davibilapps.learnsmart.dto.response.EleveResponse;
import com.davibilapps.learnsmart.entity.Eleve;
import com.davibilapps.learnsmart.entity.Nationalite;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.NationaliteRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class EleveMapper {

    private final NationaliteRepository nationaliteRepository;

    public EleveMapper(NationaliteRepository nationaliteRepository) {
        this.nationaliteRepository = nationaliteRepository;
    }


    public Eleve toEntity(EleveRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("EleveRequest est null.");
        }

        Nationalite nationalite = nationaliteRepository.findByTrackingId(request.nationaliteTrackingId())
                .orElseThrow(() -> new ResourceNotFoundException("Nationalité non trouvée"));

        return Eleve.builder()
                .trackingId(UUID.randomUUID())
                .matricule(request.matricule())
                .nom(request.nom())
                .prenom(request.prenom())
                .sexe(request.sexe())
                .dateNaissance(request.dateNaissance())
                .lieuNaissance(request.lieuNaissance())
                .contact(request.contact())
                .contact2(request.contact2())
                .email(request.email())
                .email2(request.email2())
                .nationalite(nationalite)
                .build();
    }

    public Eleve toEntityFromResponse(EleveResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("EleveResponse est null.");
        }

        Nationalite nationalite = nationaliteRepository.findByTrackingId(response.nationaliteId())
                .orElseThrow(() -> new ResourceNotFoundException("Nationalité non trouvée"));

        return Eleve.builder()
                .trackingId(response.trackingId())
                .matricule(response.matricule())
                .nom(response.nom())
                .prenom(response.prenom())
                .sexe(response.sexe())
                .dateNaissance(response.dateNaissance())
                .lieuNaissance(response.lieuNaissance())
                .contact(response.contact())
                .contact2(response.contact2())
                .email(response.email())
                .email2(response.email2())
                .nationalite(nationalite)
                .build();
    }


    public EleveResponse toResponse(Eleve eleve) {
        if (eleve == null) {
            throw new IllegalArgumentException("Eleve est null.");
        }

        return EleveResponse.builder()
                .trackingId(eleve.getTrackingId())
                .matricule(eleve.getMatricule())
                .nom(eleve.getNom())
                .prenom(eleve.getPrenom())
                .sexe(eleve.getSexe())
                .dateNaissance(eleve.getDateNaissance())
                .lieuNaissance(eleve.getLieuNaissance())
                .contact(eleve.getContact())
                .contact2(eleve.getContact2())
                .email(eleve.getEmail())
                .email2(eleve.getEmail2())
                .nationaliteLibelle(eleve.getNationalite().getLibelle())
                .nationaliteId(eleve.getNationalite().getTrackingId())
                .build();
    }
}
