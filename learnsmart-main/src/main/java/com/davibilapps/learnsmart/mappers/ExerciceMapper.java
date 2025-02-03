package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.entity.Exercise.Exercice;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.MatiereRepository;
import com.davibilapps.learnsmart.repository.NiveauRepository;
import org.springframework.stereotype.Component;

@Component
public class ExerciceMapper {

    private final MatiereRepository matiereRepository;
    private final NiveauRepository niveauRepository;

    public ExerciceMapper(MatiereRepository matiereRepository, NiveauRepository niveauRepository) {
        this.matiereRepository = matiereRepository;
        this.niveauRepository = niveauRepository;
    }


    public ExerciceResponse toResponse(Exercice exercice) {
        if (exercice == null) {
            throw new IllegalArgumentException("L'exercice est nul.");
        }

        return ExerciceResponse.builder()
                .trackingId(exercice.getTrackingId())
                .dateDebut(exercice.getDateDebut())
                .dateFin(exercice.getDateFin())
                .titre(exercice.getTitre())
                .matiereId(exercice.getMatiere() != null ? exercice.getMatiere().getTrackingId() : null)
                .matiere(exercice.getMatiere() != null ? exercice.getMatiere().getLibelle() : null)
                .niveauId(exercice.getNiveau() != null ? exercice.getNiveau().getTrackingId() : null)
                .niveau(exercice.getNiveau() != null ? exercice.getNiveau().getLibelle() : null)
                .build();
    }


    public Exercice toEntity(ExerciceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La requête ExerciceRequest est nulle.");
        }

        Matiere matiere = matiereRepository.findByTrackingId(request.matiereId())
                .orElseThrow(() -> new ResourceNotFoundException("Matière non trouvée"));
        Niveau niveau = niveauRepository.findByTrackingId(request.niveauId())
                .orElseThrow(() -> new ResourceNotFoundException("Niveau non trouvé"));

        return Exercice.builder()
                .dateDebut(request.dateDebut())
                .dateFin(request.dateFin())
                .titre(request.titre())
                .matiere(matiere)
                .niveau(niveau)
                .build();
    }
}
