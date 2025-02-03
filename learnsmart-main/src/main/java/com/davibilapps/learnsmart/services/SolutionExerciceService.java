package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.SolutionExerciceRequest;
import com.davibilapps.learnsmart.dto.response.SolutionExerciceResponse;

import java.util.List;
import java.util.UUID;

public interface SolutionExerciceService {

    SolutionExerciceResponse save(SolutionExerciceRequest solutionExerciceRequest);

    SolutionExerciceResponse update(UUID trackingId, SolutionExerciceRequest solutionExerciceRequest);

    SolutionExerciceResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<SolutionExerciceResponse> listAll();

    List<SolutionExerciceResponse> listAllByInscription(UUID inscritID);


}
