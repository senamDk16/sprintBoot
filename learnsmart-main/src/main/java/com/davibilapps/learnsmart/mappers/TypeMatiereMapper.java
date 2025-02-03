package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.TypeMatiereRequest;
import com.davibilapps.learnsmart.dto.response.TypeMatiereResponse;
import com.davibilapps.learnsmart.entity.TypeMatiere;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TypeMatiereMapper {


    public TypeMatiere toEntity(TypeMatiereRequest request) {
        if (request == null) {
            throw new ResourceNotFoundException("Request de TypeMatiere est null");
        }

        return TypeMatiere.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .build();
    }



    public  TypeMatiereResponse toResponse(TypeMatiere tpeMatiere) {
        if (tpeMatiere == null) {
            throw new ResourceNotFoundException("TypeMatiere est null");
        }

        return TypeMatiereResponse.builder()
                .libelle(tpeMatiere.getLibelle())
                .trackingId( tpeMatiere.getTrackingId()).build();
    }


    public static TypeMatiere toEntityFromResponse(TypeMatiereResponse response) {
        if (response == null) {
            return null;
        }

        return TypeMatiere.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle()).build();
    }
}
