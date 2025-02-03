package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.CycleRequest;
import com.davibilapps.learnsmart.dto.response.CycleResponse;

import java.util.List;
import java.util.UUID;

public interface CycleService {

    CycleResponse save(CycleRequest cycleRequest);

    CycleResponse update(UUID trackingId, CycleRequest cycleRequest);

    CycleResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<CycleResponse> listAll();

    List<CycleResponse> listAllByEtablissement(UUID etablissement);


}
