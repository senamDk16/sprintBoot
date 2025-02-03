package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.NiveauRequest;
import com.davibilapps.learnsmart.dto.response.NiveauResponse;

import java.util.List;
import java.util.UUID;

public interface NiveauService {

    NiveauResponse save(NiveauRequest niveauRequest);

    NiveauResponse update(UUID trackingId, NiveauRequest niveauRequest);

    NiveauResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<NiveauResponse> listAll();

    List<NiveauResponse> listAllBySerie(UUID serieID);


}
