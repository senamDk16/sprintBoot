package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.ClasseRequest;
import com.davibilapps.learnsmart.dto.response.ClasseResponse;

import java.util.List;
import java.util.UUID;

public interface ClasseService {

    ClasseResponse save(ClasseRequest classeRequest);

    ClasseResponse update(UUID trackingId, ClasseRequest classeRequest);

    ClasseResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<ClasseResponse> listAll();

    List<ClasseResponse> listAllByNiveau(UUID niveauID);


}
