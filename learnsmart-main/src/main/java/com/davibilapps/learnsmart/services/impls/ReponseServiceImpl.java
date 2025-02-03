package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.ReponseRequest;
import com.davibilapps.learnsmart.dto.response.ReponseResponse;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import com.davibilapps.learnsmart.entity.Exercise.Reponse;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.ReponseMapper;
import com.davibilapps.learnsmart.repository.exercise.QuestionRepository;
import com.davibilapps.learnsmart.repository.exercise.ReponseRepository;
import com.davibilapps.learnsmart.services.ReponseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReponseServiceImpl implements ReponseService {

    private final ReponseRepository reponseRepository;

    private final ReponseMapper reponseMapper;

    private final QuestionRepository questionRepository;

    public ReponseServiceImpl(ReponseRepository reponseRepository, ReponseMapper reponseMapper, QuestionRepository questionRepository) {
        this.reponseRepository = reponseRepository;
        this.reponseMapper = reponseMapper;
        this.questionRepository = questionRepository;
    }

    @Override
    public ReponseResponse save(ReponseRequest reponseRequest) {

        Optional<Question> question = questionRepository.findByTrackingId(reponseRequest.questionId());


        if (reponseRepository.existsByLibelle(reponseRequest.libelle())) {
            throw new AlreadyExistException("existe deja");
        }

        Reponse reponse = reponseMapper.toEntity(reponseRequest);
        Reponse reponse1 = reponseRepository.save(reponse);

        return reponseMapper.toResponse(reponse1);
    }

    @Override
    @Transactional
    public ReponseResponse update(UUID trackingId, ReponseRequest reponseRequest) {

        Reponse reponse = reponseRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", trackingId)));

        if (!reponse.getIndexation().equals(reponseRequest.indexation())) {
            reponse.setIndexation(reponseRequest.indexation());
        }

        if (!reponse.getLibelle().equals(reponseRequest.libelle())) {
            reponse.setLibelle(reponseRequest.libelle());
        }

        if (!reponse.getQuestion().getTrackingId().equals(reponseRequest.questionId())) {
            Optional<Question> question = questionRepository.findByTrackingId(reponseRequest.questionId());

            reponse.setQuestion(question.get());

        }

        return reponseMapper.toResponse(reponse);
    }


    @Override
    public ReponseResponse getOne(UUID trackingId) {

        Reponse reponse = reponseRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", trackingId)));


        return reponseMapper.toResponse(reponse);
    }

    @Override
    public void delete(UUID trackingId) {

        Reponse reponse = reponseRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Reponse avec trackingId %s introuvable.", trackingId)));

        reponseRepository.delete(reponse);

    }

    @Override
    public List<ReponseResponse> listAll() {

        List<Reponse> reponses = reponseRepository.findAll();

        return reponses.stream().map(reponseMapper::toResponse).toList();
    }

    @Override
    public List<ReponseResponse> listAllByQuestion(UUID questionID) {

        Optional<Question> question = questionRepository.findByTrackingId(questionID);

        List<Reponse> reponses = reponseRepository.findAllByQuestionOrderByQuestionOrdreAsc(question.get());

        return reponses.stream().map(reponseMapper::toResponse).toList();
    }
}
