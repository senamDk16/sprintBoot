package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.ParametrageMatiereRequest;
import com.davibilapps.learnsmart.dto.request.TypeAnnaleRequest;
import com.davibilapps.learnsmart.dto.response.ParametrageMatiereResponse;
import com.davibilapps.learnsmart.dto.response.TypeAnnaleResponse;
import com.davibilapps.learnsmart.entity.ParametrageMatiere;
import com.davibilapps.learnsmart.entity.annaltest.TypeAnnale;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ParametrageMatiereMapper {


    private final MatiereRepository matiereRepository;
    private final ClasseRepository classeRepository;
    private final EnseignantRepository enseignantRepository;
    private final EtablissementRepository etablissementRepository;
    private final AnneeScolaireRepository anneeScolaireRepository;

    public ParametrageMatiereMapper(
            MatiereRepository matiereRepository,
            ClasseRepository classeRepository,
            EnseignantRepository enseignantRepository,
            EtablissementRepository etablissementRepository,
            AnneeScolaireRepository anneeScolaireRepository
    ) {
        this.matiereRepository = matiereRepository;
        this.classeRepository = classeRepository;
        this.enseignantRepository = enseignantRepository;
        this.etablissementRepository = etablissementRepository;
        this.anneeScolaireRepository = anneeScolaireRepository;
    }


    public ParametrageMatiere toEntity(ParametrageMatiereRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("ParametrageMatiereRequest est nul.");
        }

        return ParametrageMatiere.builder()
                .trackingId(UUID.randomUUID())
                .coef(request.coef())
                .matiere(matiereRepository.findByTrackingId(request.matiereId())
                        .orElseThrow(() -> new ResourceNotFoundException("Matière non trouvée.")))
                .classe(classeRepository.findByTrackingId(request.classeId())
                        .orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée.")))
                .enseignant(enseignantRepository.findByTrackingId(request.enseignantId())
                        .orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé.")))
                .etablissement(etablissementRepository.findByTrackingId(request.etablissementId())
                        .orElseThrow(() -> new ResourceNotFoundException("Etablissement non trouvé.")))
                .anneeScolaire(anneeScolaireRepository.findByTrackingId(request.anneeScolaireId())
                        .orElseThrow(() -> new ResourceNotFoundException("Année scolaire non trouvée.")))
                .build();
    }


    public ParametrageMatiereResponse toResponse(ParametrageMatiere entity) {
        if (entity == null) {
            throw new IllegalArgumentException("ParametrageMatiere est nul.");
        }

        return ParametrageMatiereResponse.builder()
                .trackingId(entity.getTrackingId())
                .coef(entity.getCoef())
                .matiereId(entity.getMatiere().getTrackingId())
                .classeId(entity.getClasse().getTrackingId())
                .classeName(entity.getClasse().getLibelle())
                .enseignantId(entity.getEnseignant().getTrackingId())
                .enseignantName(entity.getEnseignant().getNom())
                .etablissementId(entity.getEtablissement().getTrackingId())
                .etablissementName(entity.getEtablissement().getNom())
                .anneeScolaireId(entity.getAnneeScolaire().getTrackingId())
                .build();
    }


    public static ParametrageMatiere toEntityFromResponse(ParametrageMatiereResponse response) {
        if (response == null) {
            return null;
        }

        return ParametrageMatiere.builder()
                .trackingId(response.trackingId())
                .coef(response.coef())
                .build();
    }

}
