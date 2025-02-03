package com.davibilapps.learnsmart.services;

import com.davibilapps.learnsmart.dto.request.AnneeScolaireRequest;
import com.davibilapps.learnsmart.dto.response.AnneeScolaireResponse;

import java.util.List;
import java.util.UUID;

public interface AnneeScolaireService {
    
    AnneeScolaireResponse save(AnneeScolaireRequest anneeScolaireRequest);
    AnneeScolaireResponse update(UUID trackingId, AnneeScolaireRequest anneeScolaireRequest);
    AnneeScolaireResponse getOne(UUID trackingId);
    AnneeScolaireResponse getAnneeTrue();
    void enable(UUID trackingId);
    void delete(UUID trackingId);
    List<AnneeScolaireResponse> listAll();
    
    
}
