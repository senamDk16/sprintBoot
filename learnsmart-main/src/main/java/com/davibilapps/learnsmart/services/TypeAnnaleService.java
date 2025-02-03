package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.TypeAnnaleRequest;
import com.davibilapps.learnsmart.dto.response.TypeAnnaleResponse;

import java.util.List;
import java.util.UUID;

public interface TypeAnnaleService {

    TypeAnnaleResponse save(TypeAnnaleRequest typeAnnaleRequest);

    TypeAnnaleResponse update(UUID trackingId, TypeAnnaleRequest typeAnnaleRequest);

    TypeAnnaleResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<TypeAnnaleResponse> listAll();


}
