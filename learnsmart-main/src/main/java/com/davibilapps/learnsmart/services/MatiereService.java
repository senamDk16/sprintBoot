package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.MatiereRequest;
import com.davibilapps.learnsmart.dto.response.MatiereResponse;

import java.util.List;
import java.util.UUID;

public interface MatiereService {

    MatiereResponse save(MatiereRequest matiereRequest);

    MatiereResponse update(UUID trackingId, MatiereRequest matiereRequest);

    MatiereResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<MatiereResponse> listAll();

    List<MatiereResponse> listAllByCategorie(UUID categorieID);

    List<MatiereResponse> listAllByType(UUID typeID);


}
