package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.NationaliteRequest;
import com.davibilapps.learnsmart.dto.response.NationaliteResponse;
import com.davibilapps.learnsmart.entity.Nationalite;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NationaliteMapper {


    public Nationalite toEntity(NationaliteRequest request) {
        if (request == null) {
            throw new ResourceNotFoundException("Request de Nationalite est null");
        }

        return Nationalite.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .build();
    }



    public  NationaliteResponse toResponse(Nationalite nationalite) {
        if (nationalite == null) {
            throw new ResourceNotFoundException("Nationalite est null");
        }

        return NationaliteResponse.builder()
                .libelle(nationalite.getLibelle())
                .trackingId( nationalite.getTrackingId()).build();
    }


    public static Nationalite toEntityFromResponse(NationaliteResponse response) {
        if (response == null) {
            return null;
        }

        return Nationalite.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle()).build();
    }
}
