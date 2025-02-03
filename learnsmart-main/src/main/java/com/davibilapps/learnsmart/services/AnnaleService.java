package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.AnnaleRequest;
import com.davibilapps.learnsmart.dto.response.AnnaleResponse;

import java.util.List;
import java.util.UUID;

public interface AnnaleService {

    AnnaleResponse save(AnnaleRequest annaleRequest);

    AnnaleResponse update(UUID trackingId, AnnaleRequest annaleRequest);

    AnnaleResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<AnnaleResponse> listAll();

    List<AnnaleResponse> listAllByTypeAnnale(UUID typeAnnaleID);

    List<AnnaleResponse> listAllByFilters(UUID matiereID,UUID niveauID);


}
