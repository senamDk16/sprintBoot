package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.EtablissementRequest;
import com.davibilapps.learnsmart.dto.response.EtablissementResponse;
import com.davibilapps.learnsmart.entity.Etablissement;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EtablissementMapper {


    public Etablissement toEntity(EtablissementRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("EtablissementRequest est null.");
        }

        return Etablissement.builder()
                .trackingId(UUID.randomUUID())
                .nom(request.nom())
                .slogan(request.slogan())
                .telephone1(request.telephone1())
                .telephone2(request.telephone2())
                .email1(request.email1())
                .email2(request.email2())
                .site(request.site())
                .fondateur(request.fondateur())
                .etat(request.etat())
                .build();
    }


    public Etablissement toEntityFromResponse(EtablissementResponse response) {
        if (response == null) {
            throw new IllegalArgumentException("EtablissementResponse est null.");
        }

        return Etablissement.builder()
                .trackingId(response.trackingId())
                .nom(response.nom())
                .slogan(response.slogan())
                .telephone1(response.telephone1())
                .telephone2(response.telephone2())
                .email1(response.email1())
                .email2(response.email2())
                .site(response.site())
                .fondateur(response.fondateur())
                .etat(response.etat())
                .build();
    }


    public EtablissementResponse toResponse(Etablissement etablissement) {
        if (etablissement == null) {
            throw new IllegalArgumentException("Etablissement est null.");
        }

        return EtablissementResponse.builder()
                .trackingId(etablissement.getTrackingId())
                .nom(etablissement.getNom())
                .slogan(etablissement.getSlogan())
                .telephone1(etablissement.getTelephone1())
                .telephone2(etablissement.getTelephone2())
                .email1(etablissement.getEmail1())
                .email2(etablissement.getEmail2())
                .site(etablissement.getSite())
                .fondateur(etablissement.getFondateur())
                .etat(etablissement.isEtat())
                .build();
    }
}
