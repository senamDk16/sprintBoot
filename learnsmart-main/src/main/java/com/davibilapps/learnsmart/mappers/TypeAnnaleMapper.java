package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.TypeAnnaleRequest;
import com.davibilapps.learnsmart.dto.response.TypeAnnaleResponse;
import com.davibilapps.learnsmart.entity.annaltest.TypeAnnale;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TypeAnnaleMapper {


    public TypeAnnale toEntity(TypeAnnaleRequest request) {
        if (request == null) {
            throw new ResourceNotFoundException("Request de TypeAnnale est null");
        }

        return TypeAnnale.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .build();
    }



    public  TypeAnnaleResponse toResponse(TypeAnnale typeAnnale) {
        if (typeAnnale == null) {
            throw new ResourceNotFoundException("TypeAnnale est null");
        }

        return TypeAnnaleResponse.builder()
                .libelle(typeAnnale.getLibelle())
                .trackingId( typeAnnale.getTrackingId()).build();
    }


    public static TypeAnnale toEntityFromResponse(TypeAnnaleResponse response) {
        if (response == null) {
            return null;
        }

        return TypeAnnale.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle()).build();
    }
}
