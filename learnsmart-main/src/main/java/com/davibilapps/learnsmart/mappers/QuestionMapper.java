package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.QuestionRequest;
import com.davibilapps.learnsmart.dto.response.QuestionResponse;
import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Exercise.Exercice;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.CoursRepository;
import com.davibilapps.learnsmart.repository.exercise.ExerciceRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionMapper {

    private final ExerciceRepository exerciceRepository;
    private final CoursRepository coursRepository;

    public QuestionMapper(ExerciceRepository exerciceRepository, CoursRepository coursRepository) {
        this.exerciceRepository = exerciceRepository;
        this.coursRepository = coursRepository;
    }

    public QuestionResponse toResponse(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("La question est nulle.");
        }

        return QuestionResponse.builder()
                .trackingId(question.getTrackingId())
                .ordre(question.getOrdre())
                .contenu(question.getContenu())
                .exerciceId(question.getExercice() != null ? question.getExercice().getTrackingId() : null)
                .coursId(question.getCours() != null ? question.getCours().getTrackingId() : null)
                .cours(question.getCours() != null ? question.getCours().getTitre() : null) // Supposons que Cours a une méthode getNom()
                .build();
    }


    public Question toEntity(QuestionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La requête QuestionRequest est nulle.");
        }

        Exercice exercice = exerciceRepository.findByTrackingId(request.exerciceId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercice non trouvé"));

        Cours cours = coursRepository.findByTrackingId(request.coursId())
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé"));

        return Question.builder()
                .ordre(request.ordre())
                .trackingId(UUID.randomUUID())
                .contenu(request.contenu())
                .exercice(exercice)
                .cours(cours)
                .build();
    }
}
