package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.EtablissementRequest;
import com.davibilapps.learnsmart.dto.response.EtablissementResponse;

import java.util.List;
import java.util.UUID;

public interface EtablissementService {

    EtablissementResponse save(EtablissementRequest nationaliteRequest);

    EtablissementResponse update(UUID trackingId, EtablissementRequest nationaliteRequest);

    EtablissementResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<EtablissementResponse> listAll();


}
