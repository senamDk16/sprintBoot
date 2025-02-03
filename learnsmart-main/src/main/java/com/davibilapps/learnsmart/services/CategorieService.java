package com.davibilapps.learnsmart.services;


import com.davibilapps.learnsmart.dto.request.CategorieRequest;
import com.davibilapps.learnsmart.dto.response.CategorieResponse;

import java.util.List;
import java.util.UUID;

public interface CategorieService {

    CategorieResponse save(CategorieRequest categorieRequest);

    CategorieResponse update(UUID trackingId, CategorieRequest categorieRequest);

    CategorieResponse getOne(UUID trackingId);

    void delete(UUID trackingId);

    List<CategorieResponse> listAll();


}
