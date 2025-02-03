package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.EnseignantRequest;
import com.davibilapps.learnsmart.dto.response.EnseignantResponse;

import java.util.List;
import java.util.UUID;

public interface EnseignantService {

    EnseignantResponse save(EnseignantRequest nationaliteRequest);

    EnseignantResponse update(UUID trackingId, EnseignantRequest nationaliteRequest);

    EnseignantResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<EnseignantResponse> listAll();


}
