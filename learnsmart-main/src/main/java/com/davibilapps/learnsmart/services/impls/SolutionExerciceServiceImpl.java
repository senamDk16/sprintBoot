package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.SolutionExerciceRequest;
import com.davibilapps.learnsmart.dto.response.SolutionExerciceResponse;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Exercise.SolutionExercice;
import com.davibilapps.learnsmart.entity.Inscription;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.SolutionExerciceMapper;
import com.davibilapps.learnsmart.repository.InscriptionRepository;
import com.davibilapps.learnsmart.repository.exercise.QuestionRepository;
import com.davibilapps.learnsmart.repository.exercise.SolutionExerciceRepository;
import com.davibilapps.learnsmart.services.SolutionExerciceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SolutionExerciceServiceImpl implements SolutionExerciceService {

    private final SolutionExerciceRepository exerciceRepository;

    private final SolutionExerciceMapper solutionExerciceMapper;

    private final QuestionRepository questionRepository;

    private final InscriptionRepository inscriptionRepository;

    public SolutionExerciceServiceImpl(SolutionExerciceRepository exerciceRepository, SolutionExerciceMapper solutionExerciceMapper, QuestionRepository questionRepository, InscriptionRepository inscriptionRepository) {
        this.exerciceRepository = exerciceRepository;
        this.solutionExerciceMapper = solutionExerciceMapper;
        this.questionRepository = questionRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public SolutionExerciceResponse save(SolutionExerciceRequest solutionExerciceRequest) {

        Question question = questionRepository.findByTrackingId(solutionExerciceRequest.questionId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", solutionExerciceRequest.questionId())));


        SolutionExercice solutionExercice = solutionExerciceMapper.toEntity(solutionExerciceRequest);

        SolutionExercice exercice = exerciceRepository.save(solutionExercice);

        return solutionExerciceMapper.toResponse(exercice);
    }

    @Override
    @Transactional
    public SolutionExerciceResponse update(UUID trackingId, SolutionExerciceRequest solutionExerciceRequest) {

        SolutionExercice solutionExercice = exerciceRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        if (!solutionExercice.getTitre().equals(solutionExerciceRequest.titre())) {
            solutionExercice.setTitre(solutionExerciceRequest.titre());
        }

        if (!(solutionExercice.getScore() == solutionExerciceRequest.score())) {
            solutionExercice.setScore(solutionExerciceRequest.score());
        }

        if (solutionExercice.getDateTraitement().isEqual(solutionExerciceRequest.dateTraitement())) {
            solutionExercice.setDateTraitement(solutionExerciceRequest.dateTraitement());
        }

        if (!solutionExercice.getQuestion().getTrackingId().equals(solutionExerciceRequest.questionId())) {

            Question question = questionRepository.findByTrackingId(solutionExerciceRequest.questionId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", solutionExerciceRequest.questionId())));

            solutionExercice.setQuestion(question);

        }

        if (!solutionExercice.getInscription().getTrackingId().equals(solutionExerciceRequest.inscriptionId())) {

            Inscription inscription = inscriptionRepository.findByTrackingId(solutionExerciceRequest.inscriptionId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", solutionExerciceRequest.inscriptionId())));

        }

        return solutionExerciceMapper.toResponse(solutionExercice);
    }

    @Override
    public SolutionExerciceResponse getOne(UUID trackingId) {


        SolutionExercice solutionExercice = exerciceRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));


        return solutionExerciceMapper.toResponse(solutionExercice);
    }

    @Override
    public void delete(UUID trackingId) {


        SolutionExercice solutionExercice = exerciceRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", trackingId)));

        exerciceRepository.delete(solutionExercice);
    }

    @Override
    public List<SolutionExerciceResponse> listAll() {

        List<SolutionExercice> solutionExercices = exerciceRepository.findAll();

        return solutionExercices.stream().map(solutionExerciceMapper::toResponse).toList();
    }

    @Override
    public List<SolutionExerciceResponse> listAllByInscription(UUID inscritID) {

        Inscription inscription = inscriptionRepository.findByTrackingId(inscritID).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : SolutionExercice avec trackingId %s introuvable.", inscritID)));

        return null;
    }
}
