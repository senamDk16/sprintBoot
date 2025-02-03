package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.SolutionExerciceRequest;
import com.davibilapps.learnsmart.dto.response.SolutionExerciceResponse;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Exercise.SolutionExercice;
import com.davibilapps.learnsmart.entity.Inscription;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.InscriptionRepository;
import com.davibilapps.learnsmart.repository.exercise.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class SolutionExerciceMapper {

    private final QuestionRepository questionRepository;
    private final InscriptionRepository inscriptionRepository;

    public SolutionExerciceMapper(QuestionRepository questionRepository, InscriptionRepository inscriptionRepository) {
        this.questionRepository = questionRepository;
        this.inscriptionRepository = inscriptionRepository;
    }


    public SolutionExerciceResponse toResponse(SolutionExercice solutionExercice) {
        if (solutionExercice == null) {
            throw new IllegalArgumentException("SolutionExercice est nulle.");
        }

        return SolutionExerciceResponse.builder()
                .trackingId(solutionExercice.getTrackingId())
                .dateTraitement(solutionExercice.getDateTraitement())
                .titre(solutionExercice.getTitre())
                .score(solutionExercice.getScore())
                .modifiable(solutionExercice.isModifiable())
                .questionId(solutionExercice.getQuestion() != null ? solutionExercice.getQuestion().getTrackingId() : null)
                .ordre(solutionExercice.getTitre())
                .inscriptionId(solutionExercice.getInscription() != null ? solutionExercice.getInscription().getTrackingId() : null)
                .nomEleve(solutionExercice.getInscription() != null ? solutionExercice.getInscription().getEleve().getNom() : null)
                .build();
    }


    public SolutionExercice toEntity(SolutionExerciceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("SolutionExerciceRequest est nulle.");
        }

        Question question = questionRepository.findByTrackingId(request.questionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question non trouvée"));

        Inscription inscription = inscriptionRepository.findByTrackingId(request.inscriptionId())
                .orElseThrow(() -> new ResourceNotFoundException("Inscription non trouvée"));

        return SolutionExercice.builder()
                .dateTraitement(request.dateTraitement())
                .titre(request.titre())
                .score(request.score())
                .modifiable(request.modifiable())
                .question(question)
                .inscription(inscription)
                .build();
    }
}
