package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.ReponseRequest;
import com.davibilapps.learnsmart.dto.response.ReponseResponse;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Exercise.Reponse;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.exercise.QuestionRepository;
import org.springframework.stereotype.Component;

@Component
public class ReponseMapper {

    private final QuestionRepository questionRepository;

    public ReponseMapper(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public ReponseResponse toResponse(Reponse reponse) {
        if (reponse == null) {
            throw new IllegalArgumentException("Reponse est nulle.");
        }

        return ReponseResponse.builder()
                .trackingId(reponse.getTrackingId())
                .indexation(reponse.getIndexation())
                .etat(reponse.isEtat())
                .libelle(reponse.getLibelle())
                .questionId(reponse.getQuestion() != null ? reponse.getQuestion().getTrackingId() : null)
                .titre(reponse.getQuestion() != null ? reponse.getQuestion().getOrdre() : null)
                .build();
    }


    public Reponse toEntity(ReponseRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("ReponseRequest est nulle.");
        }

        Question question = questionRepository.findByTrackingId(request.questionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question non trouvée"));

        return Reponse.builder()
                .indexation(request.indexation())
                .etat(request.etat())
                .libelle(request.libelle())
                .question(question)
                .build();
    }
}
