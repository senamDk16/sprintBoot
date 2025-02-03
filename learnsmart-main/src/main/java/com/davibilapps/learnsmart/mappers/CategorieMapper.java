package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.CategorieRequest;
import com.davibilapps.learnsmart.dto.response.CategorieResponse;
import com.davibilapps.learnsmart.entity.Categorie;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CategorieMapper {


    public Categorie toEntity(CategorieRequest request) {
        if (request == null) {
            throw new ResourceNotFoundException("Request de Categorie est null");
        }

        return Categorie.builder()
                .trackingId(UUID.randomUUID())
                .libelle(request.libelle())
                .build();
    }



    public  CategorieResponse toResponse(Categorie categorie) {
        if (categorie == null) {
            throw new ResourceNotFoundException("Categorie est null");
        }

        return CategorieResponse.builder()
                .libelle(categorie.getLibelle())
                .trackingId( categorie.getTrackingId()).build();
    }


    public static Categorie toEntityFromResponse(CategorieResponse response) {
        if (response == null) {
            return null;
        }

        return Categorie.builder()
                .trackingId(response.trackingId())
                .libelle(response.libelle()).build();
    }
}
