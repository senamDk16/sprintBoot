package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.EnseignantRequest;
import com.davibilapps.learnsmart.dto.response.EnseignantResponse;
import com.davibilapps.learnsmart.entity.Enseignant;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.EnseignantMapper;
import com.davibilapps.learnsmart.repository.EnseignantRepository;
import com.davibilapps.learnsmart.services.EnseignantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantRepository enseignantRepository;

    private final EnseignantMapper enseignantMapper;

    public EnseignantServiceImpl(EnseignantRepository enseignantRepository, EnseignantMapper enseignantMapper) {
        this.enseignantRepository = enseignantRepository;
        this.enseignantMapper = enseignantMapper;
    }

    @Override
    public EnseignantResponse save(EnseignantRequest nationaliteRequest) {

        if (enseignantRepository.existsByContact(nationaliteRequest.contact())) {
            throw new AlreadyExistException("le numero existe deja");
        }

        Enseignant enseignant = enseignantMapper.toEntity(nationaliteRequest);
        Enseignant enseignant1 = enseignantRepository.save(enseignant);

        return enseignantMapper.toResponse(enseignant1);
    }

    @Override
    @Transactional
    public EnseignantResponse update(UUID trackingId, EnseignantRequest nationaliteRequest) {

        Enseignant enseignant = enseignantRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("introuvable :%s", trackingId)));

        if (!enseignant.getNom().equals(nationaliteRequest.nom())) {
            enseignant.setNom(nationaliteRequest.nom());
        }

        if (!enseignant.getPrenom().equals(nationaliteRequest.prenom())) {
            enseignant.setPrenom(nationaliteRequest.prenom());
        }

        if (!enseignant.getContact().equals(nationaliteRequest.contact())) {
            enseignant.setContact(nationaliteRequest.contact());
        }

        return enseignantMapper.toResponse(enseignant);
    }

    @Override
    public EnseignantResponse getOne(UUID trackingId) {

        Enseignant enseignant = enseignantRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("introuvable :%s", trackingId)));

        return enseignantMapper.toResponse(enseignant);
    }

    @Override
    public void delete(UUID trackingId) {

        Enseignant enseignant = enseignantRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("introuvable :%s", trackingId)));

        enseignantRepository.delete(enseignant);

    }

    @Override
    public List<EnseignantResponse> listAll() {

        List<Enseignant> enseignants = enseignantRepository.findAll();

        return enseignants.stream().map(enseignantMapper::toResponse).toList();
    }
}
