package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.NiveauRequest;
import com.davibilapps.learnsmart.dto.response.NiveauResponse;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.entity.Serie;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.NiveauMapper;
import com.davibilapps.learnsmart.repository.NiveauRepository;
import com.davibilapps.learnsmart.repository.SerieRepository;
import com.davibilapps.learnsmart.services.NiveauService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class NiveauServiceImpl implements NiveauService {

    private final NiveauRepository niveauRepository;

    private final NiveauMapper niveauMapper;

    private final SerieRepository serieRepository;

    public NiveauServiceImpl(NiveauRepository niveauRepository, NiveauMapper niveauMapper, SerieRepository serieRepository) {
        this.niveauRepository = niveauRepository;
        this.niveauMapper = niveauMapper;
        this.serieRepository = serieRepository;
    }

    @Override
    public NiveauResponse save(NiveauRequest niveauRequest) {

        Serie serie = serieRepository.findByTrackingId(niveauRequest.serieId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Serie avec trackingId %s introuvable.", niveauRequest.serieId())));

        if (niveauRepository.existsByLibelle(niveauRequest.libelle())) {
            throw new AlreadyExistException("existe deja ");
        }

        Niveau niveau = niveauMapper.toEntity(niveauRequest);

        Niveau niveau1 = niveauRepository.save(niveau);

        return niveauMapper.toResponse(niveau1);
    }

    @Override
    @Transactional
    public NiveauResponse update(UUID trackingId, NiveauRequest niveauRequest) {
        Niveau niveau = niveauRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Niveau avec trackingId %s introuvable.", trackingId)));

        if (!niveau.getLibelle().equals(niveauRequest.libelle())) {
            niveau.setLibelle(niveauRequest.libelle());
        }

        if (!niveau.getSerie().getTrackingId().equals(niveau.getSerie())) {
            Serie serie = serieRepository.findByTrackingId(niveauRequest.serieId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Serie avec trackingId %s introuvable.", trackingId)));

            niveau.setSerie(serie);

        }

        return niveauMapper.toResponse(niveau);
    }

    @Override
    public NiveauResponse getOne(UUID trackingId) {
        Niveau niveau = niveauRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Niveau avec trackingId %s introuvable.", trackingId)));

        return niveauMapper.toResponse(niveau);
    }

    @Override
    public void delete(UUID trackingId) {
        Niveau niveau = niveauRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Niveau avec trackingId %s introuvable.", trackingId)));

        niveauRepository.delete(niveau);

    }

    @Override
    public List<NiveauResponse> listAll() {
        List<Niveau> niveaus = niveauRepository.findAll();
        return niveaus.stream().map(niveauMapper::toResponse).toList();
    }

    @Override
    public List<NiveauResponse> listAllBySerie(UUID serieID) {

        Serie serie = serieRepository.findByTrackingId(serieID).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : Serie avec trackingId %s introuvable.", serieID)));

        List<Niveau> niveaus = niveauRepository.findAllBySerie(serie);

        return niveaus.stream().map(niveauMapper::toResponse).toList();
    }
}
