package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.CoursRequest;
import com.davibilapps.learnsmart.dto.response.CoursResponse;
import com.davibilapps.learnsmart.entity.Cours;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.CoursMapper;
import com.davibilapps.learnsmart.repository.CoursRepository;
import com.davibilapps.learnsmart.repository.MatiereRepository;
import com.davibilapps.learnsmart.services.CoursService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CoursServiceImpl implements CoursService {


    private final CoursRepository coursRepository;

    private final CoursMapper coursMapper;

    private final MatiereRepository matiereRepository;

    public CoursServiceImpl(CoursRepository coursRepository, CoursMapper coursMapper, MatiereRepository matiereRepository) {
        this.coursRepository = coursRepository;
        this.coursMapper = coursMapper;
        this.matiereRepository = matiereRepository;
    }

    @Override
    public CoursResponse save(CoursRequest coursRequest) {

        //UUID matiereId

        Matiere matiere = matiereRepository.findByTrackingId(coursRequest.matiereId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("erreur id ", coursRequest.matiereId())));



        if (coursRepository.existsByTitre(coursRequest.titre())) {
            throw new AlreadyExistException("le titre existe deja");
        }

        Cours cours = coursMapper.toEntity(coursRequest);

        Cours cours1 = coursRepository.save(cours);

        return coursMapper.toResponse(cours1);
    }

    @Override
    @Transactional
    public CoursResponse update(UUID trackingId, CoursRequest coursRequest) {

        Cours cours = coursRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur tracking : %s", trackingId)));

        if (!cours.getTitre().equals(coursRequest.titre())) {
            cours.setTitre(coursRequest.titre());
        }

        if (!cours.getDescription().equals(coursRequest.description())) {
            cours.setDescription(coursRequest.description());
        }

        if (!cours.getMatiere().getTrackingId().equals(coursRequest.matiereId())) {

            Matiere matiere = matiereRepository.findByTrackingId(coursRequest.matiereId())
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("erreur id ", coursRequest.matiereId())));
            cours.setMatiere(matiere);
        }

        return coursMapper.toResponse(cours);
    }

    @Override
    public CoursResponse getOne(UUID trackingId) {

        Cours cours = coursRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur tracking : %s", trackingId)));

        return coursMapper.toResponse(cours);
    }

    @Override
    public void delete(UUID trackingId) {

        Cours cours = coursRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur tracking : %s", trackingId)));

        coursRepository.delete(cours);

    }

    @Override
    @Transactional(readOnly = true)
    public List<CoursResponse> listAll() {

        List<Cours> cours = coursRepository.findAll();

        return cours.stream().map(coursMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoursResponse> listAllByMatiere(UUID matiereID) {

        Matiere matiere = matiereRepository.findByTrackingId(matiereID).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur Id matier : %s", matiereID)));

        List<Cours> cours = coursRepository.findAllByMatiereOrderByTitre(matiere);

        return cours.stream().map(coursMapper::toResponse).toList();
    }
}
