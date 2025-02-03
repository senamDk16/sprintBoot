package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.ReponseRequest;
import com.davibilapps.learnsmart.dto.response.ReponseResponse;

import java.util.List;
import java.util.UUID;

public interface ReponseService {

    ReponseResponse save(ReponseRequest reponseRequest);

    ReponseResponse update(UUID trackingId, ReponseRequest reponseRequest);

    ReponseResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<ReponseResponse> listAll();

    List<ReponseResponse> listAllByQuestion(UUID questionID);



}
