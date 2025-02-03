package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.QuestionRequest;
import com.davibilapps.learnsmart.dto.response.QuestionResponse;
import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Exercise.Exercice;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.QuestionMapper;
import com.davibilapps.learnsmart.repository.CoursRepository;
import com.davibilapps.learnsmart.repository.exercise.ExerciceRepository;
import com.davibilapps.learnsmart.repository.exercise.QuestionRepository;
import com.davibilapps.learnsmart.services.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    private final ExerciceRepository exerciceRepository;

    private final CoursRepository coursRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper, ExerciceRepository exerciceRepository, CoursRepository coursRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.exerciceRepository = exerciceRepository;
        this.coursRepository = coursRepository;
    }

    @Override
    public QuestionResponse save(QuestionRequest questionRequest) {

        Exercice exercice = exerciceRepository.findByTrackingId(questionRequest.exerciceId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Exercice avec trackingId %s introuvable.", questionRequest.exerciceId())));

        Cours cours = coursRepository.findByTrackingId(questionRequest.coursId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Exercice avec trackingId %s introuvable.", questionRequest.coursId())));


        if (questionRepository.existsByOrdre(questionRequest.ordre())) {
            throw new AlreadyExistException("existe deja ");
        }

        Question question = questionMapper.toEntity(questionRequest);

        Question question1 = questionRepository.save(question);

        return questionMapper.toResponse(question1);
    }

    @Override
    @Transactional
    public QuestionResponse update(UUID trackingId, QuestionRequest questionRequest) {

        Question question = questionRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Niveau avec trackingId %s introuvable.", trackingId)));

        if (!question.getOrdre().equals(questionRequest.ordre())) {
            question.setOrdre(questionRequest.ordre());
        }

        if (!question.getContenu().equals(questionRequest.contenu())) {
            question.setContenu(questionRequest.contenu());
        }

        if (!question.getExercice().getTrackingId().equals(questionRequest.exerciceId())) {

            Exercice exercice = exerciceRepository.findByTrackingId(questionRequest.exerciceId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Exercice avec trackingId %s introuvable.", questionRequest.exerciceId())));

            question.setExercice(exercice);
        }

        if (!question.getCours().getTrackingId().equals(questionRequest.coursId())) {

            Cours cours = coursRepository.findByTrackingId(questionRequest.coursId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Exercice avec trackingId %s introuvable.", questionRequest.coursId())));

            question.setCours(cours);

        }


        return questionMapper.toResponse(question);
    }

    @Override
    public QuestionResponse getOne(UUID trackingId) {

        Question question = questionRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Niveau avec trackingId %s introuvable.", trackingId)));

        return questionMapper.toResponse(question);
    }

    @Override
    public void delete(UUID trackingId) {

        Question question = questionRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Niveau avec trackingId %s introuvable.", trackingId)));

        questionRepository.delete(question);

    }

    @Override
    public List<QuestionResponse> listAll() {

        List<Question> questions = questionRepository.findAll();

        return questions.stream().map(questionMapper::toResponse).toList();
    }

    @Override
    public List<QuestionResponse> listAllByExercice(UUID exerciceID) {

        List<Question> questions = questionRepository.findByExercice(exerciceID);

        return questions.stream().map(questionMapper::toResponse).toList();
    }

    @Override
    public List<QuestionResponse> listAllByCours(UUID coursID) {

        List<Question> questions = questionRepository.findByCours(coursID);

        return questions.stream().map(questionMapper::toResponse).toList();
    }
}
