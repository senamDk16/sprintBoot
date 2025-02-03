package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;

import java.util.List;
import java.util.UUID;

public interface ExerciceService {

    ExerciceResponse save(ExerciceRequest matiereRequest);

    ExerciceResponse update(UUID trackingId, ExerciceRequest matiereRequest);

    ExerciceResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<ExerciceResponse> listAll();

    List<ExerciceResponse> listAllByNiveauAndMatiere(UUID niveauID,UUID matiereID);



}
