package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.CoursRequest;
import com.davibilapps.learnsmart.dto.response.CoursResponse;

import java.util.List;
import java.util.UUID;

public interface CoursService {

    CoursResponse save(CoursRequest coursRequest);

    CoursResponse update(UUID trackingId, CoursRequest coursRequest);

    CoursResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<CoursResponse> listAll();

    List<CoursResponse> listAllByMatiere(UUID matiereID);



}
