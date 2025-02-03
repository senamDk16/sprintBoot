package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.ParametrageMatiereRequest;
import com.davibilapps.learnsmart.dto.response.ParametrageMatiereResponse;
import com.davibilapps.learnsmart.entity.AnneeScolaire;
import com.davibilapps.learnsmart.entity.Classe;
import com.davibilapps.learnsmart.entity.ParametrageMatiere;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.ParametrageMatiereMapper;
import com.davibilapps.learnsmart.repository.AnneeScolaireRepository;
import com.davibilapps.learnsmart.repository.ClasseRepository;
import com.davibilapps.learnsmart.repository.ParametrageMatiereRepository;
import com.davibilapps.learnsmart.services.ParametreMatiereService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParametreMatiereServiceImpl implements ParametreMatiereService {

    private final ParametrageMatiereRepository parametrageMatiereRepository;

    private final ParametrageMatiereMapper parametrageMatiereMapper;

    private final AnneeScolaireRepository anneeScolaireRepository;

    private final ClasseRepository classeRepository;

    public ParametreMatiereServiceImpl(ParametrageMatiereRepository parametrageMatiereRepository, ParametrageMatiereMapper parametrageMatiereMapper, AnneeScolaireRepository anneeScolaireRepository, ClasseRepository classeRepository) {
        this.parametrageMatiereRepository = parametrageMatiereRepository;
        this.parametrageMatiereMapper = parametrageMatiereMapper;
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.classeRepository = classeRepository;
    }


    @Override
    public ParametrageMatiereResponse save(ParametrageMatiereRequest parametreMatiereRequest) {

        Classe classe = classeRepository.findByTrackingId(parametreMatiereRequest.classeId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : classe avec trackingId %s introuvable.", parametreMatiereRequest.classeId())));


        //       il reste 4 a faire  UUID matiereId,
        //        UUID enseignantId,
        //        UUID etablissementId,
        //        UUID anneeScolaireId


        ParametrageMatiere matiere = parametrageMatiereMapper.toEntity(parametreMatiereRequest);

        ParametrageMatiere matiere1 = parametrageMatiereRepository.save(matiere);


        return parametrageMatiereMapper.toResponse(matiere1);
    }

    @Override
    @Transactional
    public ParametrageMatiereResponse update(UUID trackingId, ParametrageMatiereRequest parametreMatiereRequest) {

        ParametrageMatiere parametrageMatiere = parametrageMatiereRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : classe avec trackingId %s introuvable.", trackingId)));

        if (parametrageMatiere.getCoef()== parametreMatiereRequest.coef()){
            parametrageMatiere.setCoef(parametreMatiereRequest.coef());
        }

        //        il reste a faire
        //        UUID matiereId,
        //        UUID classeId,
        //        UUID enseignantId,
        //        UUID etablissementId,
        //        UUID anneeScolaireId



        return parametrageMatiereMapper.toResponse(parametrageMatiere);
    }

    @Override
    public ParametrageMatiereResponse getOne(UUID trackingId) {

        ParametrageMatiere parametrageMatiere = parametrageMatiereRepository.findByTrackingId(trackingId).orElseThrow();

        return parametrageMatiereMapper.toResponse(parametrageMatiere);
    }

    @Override
    public void delete(UUID trackingId) {

        ParametrageMatiere parametrageMatiere = parametrageMatiereRepository.findByTrackingId(trackingId).orElseThrow();

        parametrageMatiereRepository.delete(parametrageMatiere);

    }

    @Override
    public List<ParametrageMatiereResponse> listAll() {

        List<ParametrageMatiere> matieres = parametrageMatiereRepository.findAll();

        return matieres.stream().map(parametrageMatiereMapper::toResponse).toList();
    }

    @Override
    public List<ParametrageMatiereResponse> listAllByClasse(UUID classeID) {

        Optional<AnneeScolaire> anneeScolaire = anneeScolaireRepository.findByEtatIsTrue();

        Classe classe = classeRepository.findByTrackingId(classeID).orElseThrow(() -> new ResourceNotFoundException(String.format("Erreur : classe avec trackingId %s introuvable.", classeID)));


        List<ParametrageMatiere> matieres = parametrageMatiereRepository.findAllByClasseAndAnneeScolaire_Etat(classe, anneeScolaire.isPresent());

        return matieres.stream().map(parametrageMatiereMapper::toResponse).toList();
    }
}
