package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.ReponseEpreuveRequest;
import com.davibilapps.learnsmart.dto.response.ReponseEpreuveResponse;

import java.util.List;
import java.util.UUID;

public interface ReponseEpreuveService {

    ReponseEpreuveResponse save(ReponseEpreuveRequest reponseEpreuveRequest);

    ReponseEpreuveResponse update(UUID trackingId, ReponseEpreuveRequest reponseEpreuveRequest);

    ReponseEpreuveResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<ReponseEpreuveResponse> listAll();

    List<ReponseEpreuveResponse> listAllByEpreuve(UUID epreuveID);



}
