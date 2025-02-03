package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.QuestionRequest;
import com.davibilapps.learnsmart.dto.response.QuestionResponse;

import java.util.List;
import java.util.UUID;

public interface QuestionService {

    QuestionResponse save(QuestionRequest questionRequest);

    QuestionResponse update(UUID trackingId, QuestionRequest questionRequest);

    QuestionResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<QuestionResponse> listAll();

    List<QuestionResponse> listAllByExercice(UUID exerciceID);

    List<QuestionResponse> listAllByCours(UUID coursID);


}
