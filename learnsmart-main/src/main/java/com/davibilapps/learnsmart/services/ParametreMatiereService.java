package com.davibilapps.learnsmart.services;



import com.davibilapps.learnsmart.dto.request.ParametrageMatiereRequest;
import com.davibilapps.learnsmart.dto.response.ParametrageMatiereResponse;

import java.util.List;
import java.util.UUID;

public interface ParametreMatiereService {

    ParametrageMatiereResponse save(ParametrageMatiereRequest parametreMatiereRequest);

    ParametrageMatiereResponse update(UUID trackingId, ParametrageMatiereRequest parametreMatiereRequest);

    ParametrageMatiereResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<ParametrageMatiereResponse> listAll();

    List<ParametrageMatiereResponse> listAllByClasse(UUID classeID);



}
