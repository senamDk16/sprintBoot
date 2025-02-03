package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.TypeMatiereRequest;
import com.davibilapps.learnsmart.dto.response.TypeMatiereResponse;

import java.util.List;
import java.util.UUID;

public interface TypeMatiereService {

    TypeMatiereResponse save(TypeMatiereRequest typeMatiereRequest);

    TypeMatiereResponse update(UUID trackingId, TypeMatiereRequest typeMatiereRequest);

    TypeMatiereResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<TypeMatiereResponse> listAll();


}
