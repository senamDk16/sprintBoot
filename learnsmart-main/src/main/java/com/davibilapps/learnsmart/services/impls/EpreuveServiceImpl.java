package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.EpreuveRequest;
import com.davibilapps.learnsmart.dto.response.EpreuveResponse;
import com.davibilapps.learnsmart.entity.annaltest.Annale;
import com.davibilapps.learnsmart.entity.annaltest.Epreuve;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.EpreuveMapper;
import com.davibilapps.learnsmart.repository.annaltest.AnnaleRepository;
import com.davibilapps.learnsmart.repository.annaltest.EpreuveRepository;
import com.davibilapps.learnsmart.services.EpreuveService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EpreuveServiceImpl implements EpreuveService {

    private final EpreuveRepository epreuveRepository;

    private final EpreuveMapper epreuveMapper;

    private final AnnaleRepository annaleRepository;

    public EpreuveServiceImpl(EpreuveRepository epreuveRepository, EpreuveMapper epreuveMapper, AnnaleRepository annaleRepository) {
        this.epreuveRepository = epreuveRepository;
        this.epreuveMapper = epreuveMapper;
        this.annaleRepository = annaleRepository;
    }

    @Override
    public EpreuveResponse save(EpreuveRequest epreuveRequest) {

        if (epreuveRepository.existsByTitre(epreuveRequest.titre())) {
            throw new AlreadyExistException("l'epreuve existe deja");
        }

        Epreuve epreuve = epreuveMapper.toEntity(epreuveRequest);
        Epreuve epreuve1 = epreuveRepository.save(epreuve);

        return epreuveMapper.toResponse(epreuve1);
    }

    @Override
    @Transactional
    public EpreuveResponse update(UUID trackingId, EpreuveRequest epreuveRequest) {

        Epreuve epreuve = epreuveRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID :%s ", trackingId)));

        if (epreuve.getTitre().equals(epreuveRequest.titre())) {
            epreuve.setTitre(epreuveRequest.titre());
        }

        if (epreuve.getAnnale().equals(epreuveRequest.annaleId())) {

            Annale annale = annaleRepository.findByTrackingId(epreuveRequest.annaleId()).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID : %s", epreuveRequest.annaleId())));

            epreuve.setAnnale(annale);
        }

        if (epreuve.getContenu().equals(epreuveRequest.contenu())) {
            epreuve.setContenu(epreuveRequest.contenu());
        }

        return epreuveMapper.toResponse(epreuve);
    }

    @Override
    public EpreuveResponse getOne(UUID trackingId) {

        Epreuve epreuve = epreuveRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID :%s ", trackingId)));


        return epreuveMapper.toResponse(epreuve);
    }

    @Override
    public void delete(UUID trackingId) {

        Epreuve epreuve = epreuveRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID :%s ", trackingId)));

        epreuveRepository.delete(epreuve);
    }

    @Override
    public List<EpreuveResponse> listAll() {

        List<Epreuve> epreuves = epreuveRepository.findAll();

        return epreuves.stream().map(epreuveMapper::toResponse).toList();
    }

    @Override
    public List<EpreuveResponse> listAllByAnnale(UUID annaleID) {

        Annale annale = annaleRepository.findByTrackingId(annaleID).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID : %s", annaleID)));


        List<Epreuve> epreuves = epreuveRepository.findALlByAnnale(annale);
        return epreuves.stream().map(epreuveMapper::toResponse).toList();
    }
}
