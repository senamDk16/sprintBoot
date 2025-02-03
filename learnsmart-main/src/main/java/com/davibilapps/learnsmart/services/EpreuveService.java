package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.EpreuveRequest;
import com.davibilapps.learnsmart.dto.response.EpreuveResponse;

import java.util.List;
import java.util.UUID;

public interface EpreuveService {

    EpreuveResponse save(EpreuveRequest epreuveRequest);

    EpreuveResponse update(UUID trackingId, EpreuveRequest epreuveRequest);

    EpreuveResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<EpreuveResponse> listAll();

    List<EpreuveResponse> listAllByAnnale(UUID annaleID);


}
