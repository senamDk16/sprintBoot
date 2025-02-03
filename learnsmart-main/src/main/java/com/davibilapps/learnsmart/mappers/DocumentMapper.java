package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.ClasseRequest;
import com.davibilapps.learnsmart.dto.request.DocumentRequest;
import com.davibilapps.learnsmart.dto.response.ClasseResponse;
import com.davibilapps.learnsmart.dto.response.DocumentResponse;
import com.davibilapps.learnsmart.entity.Classe;
import com.davibilapps.learnsmart.entity.Document;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.NiveauRepository;
import com.davibilapps.learnsmart.repository.exercise.QuestionRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DocumentMapper {


    private final QuestionRepository questionRepository;

    public DocumentMapper(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Document toEntity(DocumentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request de Document est null.");
        }

        Question question = questionRepository.findByTrackingId(request.questionTrackingId())
                .orElseThrow(() -> new ResourceNotFoundException("Question non trouvée."));

        return Document.builder()
                .trackingId(UUID.randomUUID())
                .path(request.path())
                .name(request.name())
                .etat(request.etat())
                .question(question)
                .build();
    }


    public Document toEntityFromResponse(DocumentResponse response, Question question) {
        if (response == null) {
            throw new IllegalArgumentException("DocumentResponse est null.");
        }

        return Document.builder()
                .trackingId(response.trackingId())
                .path(response.path())
                .name(response.name())
                .etat(response.etat())
                .question(question)
                .build();
    }


    public DocumentResponse toResponse(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document est null.");
        }

        return DocumentResponse.builder()
                .trackingId(document.getTrackingId())
                .path(document.getPath())
                .name(document.getName())
                .etat(document.isEtat())
                .questionLibelle(document.getQuestion().getOrdre())
                .questionId(document.getQuestion().getTrackingId())
                .build();
    }
}
