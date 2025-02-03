package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.SerieRequest;
import com.davibilapps.learnsmart.dto.response.SerieResponse;

import java.util.List;
import java.util.UUID;

public interface SerieService {

    SerieResponse save(SerieRequest serieRequest);

    SerieResponse update(UUID trackingId, SerieRequest serieRequest);

    SerieResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<SerieResponse> listAll();

    List<SerieResponse> listAllByCycle(UUID cycle);


}
