package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.NationaliteRequest;
import com.davibilapps.learnsmart.dto.response.NationaliteResponse;

import java.util.List;
import java.util.UUID;

public interface NationaliteService {

    NationaliteResponse save(NationaliteRequest nationaliteRequest);

    NationaliteResponse update(UUID trackingId, NationaliteRequest nationaliteRequest);

    NationaliteResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<NationaliteResponse> listAll();


}
