package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.EleveRequest;
import com.davibilapps.learnsmart.dto.request.InscriptionRequest;
import com.davibilapps.learnsmart.dto.response.EleveResponse;
import com.davibilapps.learnsmart.dto.response.InscriptionResponse;
import com.davibilapps.learnsmart.entity.*;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InscriptionMapper {


    private final EleveRepository eleveRepository;
    private final ClasseRepository classeRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;
    private final EtablissementRepository etablissementRepository;

    public InscriptionMapper(EleveRepository eleveRepository,
                             ClasseRepository classeRepository,
                             AnneeScolaireRepository anneeScolaireRepository,
                             EtablissementRepository etablissementRepository) {
        this.eleveRepository = eleveRepository;
        this.classeRepository = classeRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.etablissementRepository = etablissementRepository;
    }


    public Inscription toEntity(InscriptionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("InscriptionRequest est null.");
        }

        Eleve eleve = eleveRepository.findById(request.eleveId())
                .orElseThrow(() -> new ResourceNotFoundException("Élève non trouvé"));

        Classe classe = classeRepository.findById(request.classeId())
                .orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée"));

        AnneeScolaire anneeScolaire = anneeScolaireRepository.findById(request.anneeScolaireId())
                .orElseThrow(() -> new ResourceNotFoundException("Année scolaire non trouvée"));

        Etablissement etablissement = etablissementRepository.findById(request.etablissementId())
                .orElseThrow(() -> new ResourceNotFoundException("Établissement non trouvé"));

        return Inscription.builder()
                .statut(request.statut())
                .dateInscription(request.dateInscription())
                .eleve(eleve)
                .trackingId(UUID.randomUUID())
                .classe(classe)
                .anneeScolaire(anneeScolaire)
                .etablissement(etablissement)
                .build();
    }


    public InscriptionResponse toResponse(Inscription inscription) {
        if (inscription == null) {
            throw new IllegalArgumentException("Inscription est null.");
        }

        return InscriptionResponse.builder()
                .trackingId(inscription.getTrackingId())
                .statut(inscription.getStatut())
                .dateInscription(inscription.getDateInscription())
                .eleveId(inscription.getEleve().getId())
                .nomEleve(inscription.getEleve().getNom() + " " + inscription.getEleve().getPrenom())
                .classeId(inscription.getClasse().getId())
                .classe(inscription.getClasse().getLibelle())
                .anneeScolaireId(inscription.getAnneeScolaire().getId())
                .etablissementId(inscription.getEtablissement().getId())
                .build();
    }
}
