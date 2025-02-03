package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.AnneeScolaireRequest;
import com.davibilapps.learnsmart.dto.response.AnneeScolaireResponse;
import com.davibilapps.learnsmart.entity.AnneeScolaire;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.AnneeScolaireMapper;
import com.davibilapps.learnsmart.repository.AnneeScolaireRepository;
import com.davibilapps.learnsmart.services.AnneeScolaireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AnneeScolaireImpl implements AnneeScolaireService {

    private final AnneeScolaireRepository anneeScolaireRepository;
    private final AnneeScolaireMapper anneeScolaireMapper;

    @Override
    @Transactional
    public AnneeScolaireResponse save(@Valid AnneeScolaireRequest anneeScolaireRequest) {
        log.info("Tentative de sauvegarde de l'année scolaire avec libelle : {}", anneeScolaireRequest.libelle());


        if (anneeScolaireRepository.existsByLibelle(anneeScolaireRequest.libelle())) {
            throw new AlreadyExistException("Cette année scolaire existe déjà");
        }

        AnneeScolaire anneeScolaire = anneeScolaireMapper.toEntity(anneeScolaireRequest);
        anneeScolaire = anneeScolaireRepository.save(anneeScolaire);

        log.info("Année scolaire sauvegardée avec succès. ID: {}", anneeScolaire.getTrackingId());
        return anneeScolaireMapper.toResponse(anneeScolaire);
    }

    @Override
    @Transactional
    public AnneeScolaireResponse update(UUID trackingId, AnneeScolaireRequest anneeScolaireRequest) {

        log.info("Modification de l'année scolaire avec ID : {}", trackingId);

        AnneeScolaire anneeScolaire = anneeScolaireRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Aucune année scolaire trouvée avec ID : %s", trackingId))
                );

        if (!anneeScolaire.getLibelle().equals(anneeScolaireRequest.libelle())) {
            anneeScolaire.setLibelle(anneeScolaireRequest.libelle());
        }
        if (!anneeScolaire.getDateDebut().isEqual(anneeScolaireRequest.dateDebut())) {
            anneeScolaire.setDateDebut(anneeScolaireRequest.dateDebut());
        }
        if (!anneeScolaire.getDateFin().isEqual(anneeScolaireRequest.dateFin())) {
            anneeScolaire.setDateFin(anneeScolaireRequest.dateFin());
        }

        return anneeScolaireMapper.toResponse(anneeScolaire);
    }


    @Transactional(readOnly = true)
    @Override
    public AnneeScolaireResponse getOne(UUID trackingId) {
        log.info("Récupération de l'année scolaire avec ID : {}", trackingId);

        return anneeScolaireRepository.findByTrackingId(trackingId)
                .map(anneeScolaireMapper::toResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Aucune année scolaire trouvée avec ID : %s", trackingId))
                );
    }



    @Transactional(readOnly = true)
    @Override
    public AnneeScolaireResponse getAnneeTrue() {
        log.info("Récupération de l'année scolaire active ");

        return anneeScolaireRepository.findByEtatIsTrue()
                .map(anneeScolaireMapper::toResponse)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Aucune année scolaire active trouvée ")
                );
    }

    @Override
    public void enable(UUID trackingId) {

        if (trackingId == null) {
            throw new IllegalArgumentException("L'ID de suivi ne peut pas être nul");
        }

        int isUpdated = anneeScolaireRepository.setEtatForIdAndResetOthers(trackingId);

        if (isUpdated==0) {
            throw new ResourceNotFoundException("Aucune année scolaire trouvée avec le tracking ID : " + trackingId);
        }

        log.info("L'année scolaire avec le tracking ID {} a été activée et les autres années scolaires ont été désactivées", trackingId);


    }

    @Override
    public void delete(UUID trackingId) {

        AnneeScolaire anneeScolaire = anneeScolaireRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("AnneeScolaire introuvable avec l'ID : %s", trackingId)
                ));

        log.info("Suppression de l'année scolaire: {}", anneeScolaire.getLibelle());

        anneeScolaireRepository.delete(anneeScolaire);

    }

    @Override
    public List<AnneeScolaireResponse> listAll() {
        return anneeScolaireRepository.findAllByOrderByIdDesc().stream()
                .map(anneeScolaireMapper::toResponse)
                .toList();
    }
}
