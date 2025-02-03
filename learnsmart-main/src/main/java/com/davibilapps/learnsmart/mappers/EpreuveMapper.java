package com.davibilapps.learnsmart.mappers;

import com.davibilapps.learnsmart.dto.request.EpreuveRequest;
import com.davibilapps.learnsmart.dto.response.EpreuveResponse;
import com.davibilapps.learnsmart.entity.annaltest.Epreuve;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.repository.annaltest.AnnaleRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EpreuveMapper {

    private final AnnaleRepository annaleRepository;

    public EpreuveMapper(AnnaleRepository annaleRepository) {
        this.annaleRepository = annaleRepository;
    }


    public EpreuveResponse toResponse(Epreuve epreuve) {
        if (epreuve == null) {
            throw new IllegalArgumentException("L'épreuve est nulle.");
        }

        return EpreuveResponse.builder()
                .trackingId(epreuve.getTrackingId())
                .titre(epreuve.getTitre())
                .contenu(epreuve.getContenu())
                .annaleId(epreuve.getAnnale() != null ? epreuve.getAnnale().getTrackingId() : null)              .build();
    }


    public Epreuve toEntity(EpreuveRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("La requête EpreuveRequest est nulle.");
        }

        return Epreuve.builder()
                .titre(request.titre())
                .trackingId(UUID.randomUUID())
                .contenu(request.contenu())
                .annale(annaleRepository.findByTrackingId(request.annaleId())
                        .orElseThrow(() -> new ResourceNotFoundException("Annale non trouvée")))
                .build();
    }
}
