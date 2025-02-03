package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.ExerciceRequest;
import com.davibilapps.learnsmart.dto.response.ExerciceResponse;
import com.davibilapps.learnsmart.entity.Exercise.Exercice;
import com.davibilapps.learnsmart.entity.Matiere;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.ExerciceMapper;
import com.davibilapps.learnsmart.repository.MatiereRepository;
import com.davibilapps.learnsmart.repository.NiveauRepository;
import com.davibilapps.learnsmart.repository.exercise.ExerciceRepository;
import com.davibilapps.learnsmart.services.ExerciceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciceServiceImpl implements ExerciceService {

    private final ExerciceRepository exerciceRepository;

    private final ExerciceMapper exerciceMapper;

    private final MatiereRepository matiereRepository;

    private final NiveauRepository niveauRepository;

    public ExerciceServiceImpl(ExerciceRepository exerciceRepository, ExerciceMapper exerciceMapper, MatiereRepository matiereRepository, NiveauRepository niveauRepository) {
        this.exerciceRepository = exerciceRepository;
        this.exerciceMapper = exerciceMapper;
        this.matiereRepository = matiereRepository;
        this.niveauRepository = niveauRepository;
    }

    @Override
    public ExerciceResponse save(ExerciceRequest matiereRequest) {

        Matiere matiere = matiereRepository.findByTrackingId(matiereRequest.matiereId()).orElseThrow(() -> new ResourceNotFoundException("erreur UUID " + matiereRequest.matiereId()));

        Niveau niveau = niveauRepository.findByTrackingId(matiereRequest.niveauId()).orElseThrow(() -> new ResourceNotFoundException("erreur UUID " + matiereRequest.niveauId()));


        if (exerciceRepository.existsByTitre(matiereRequest.titre())) {
            throw new AlreadyExistException("il existe deja");
        }

        Exercice exercice = exerciceMapper.toEntity(matiereRequest);
        Exercice exercice1 = exerciceRepository.save(exercice);

        return exerciceMapper.toResponse(exercice1);
    }

    @Override
    @Transactional
    public ExerciceResponse update(UUID trackingId, ExerciceRequest matiereRequest) {

        Exercice exercice = exerciceRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID : %s", trackingId)));

        if (!exercice.getDateDebut().isEqual(matiereRequest.dateDebut())) {
            exercice.setDateDebut(matiereRequest.dateDebut());
        }

        if (!exercice.getDateFin().isEqual(matiereRequest.dateFin())) {
            exercice.setDateFin(matiereRequest.dateFin());
        }
        //a corrige pour les autres
        if (!exercice.getMatiere().getTrackingId().equals(matiereRequest.matiereId())) {
            Matiere matiere = matiereRepository.findByTrackingId(matiereRequest.matiereId()).orElseThrow(() -> new ResourceNotFoundException("erreur UUID " + matiereRequest.matiereId()));
            exercice.setMatiere(matiere);
        }

        if (!exercice.getNiveau().getTrackingId().equals(matiereRequest.niveauId())) {
            Niveau niveau = niveauRepository.findByTrackingId(matiereRequest.niveauId()).orElseThrow(() -> new ResourceNotFoundException("erreur UUID " + matiereRequest.niveauId()));
            exercice.setNiveau(niveau);
        }

        return exerciceMapper.toResponse(exercice);
    }

    @Override
    public ExerciceResponse getOne(UUID trackingId) {
        Exercice exercice = exerciceRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID : %s", trackingId)));

        return exerciceMapper.toResponse(exercice);
    }

    @Override
    public void delete(UUID trackingId) {
        Exercice exercice = exerciceRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("erreur UUID : %s", trackingId)));

        exerciceRepository.delete(exercice);

    }

    @Override
    public List<ExerciceResponse> listAll() {

        List<Exercice> exercices = exerciceRepository.findAll();

        return exercices.stream().map(exerciceMapper::toResponse).toList();
    }

    @Override
    public List<ExerciceResponse> listAllByNiveauAndMatiere(UUID niveauID, UUID matiereID) {
        List<Exercice> exercices = exerciceRepository.findAllByFilters(niveauID, matiereID);

        return exercices.stream().map(exerciceMapper::toResponse).toList();
    }
}
