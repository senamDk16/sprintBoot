package com.davibilapps.learnsmart.services.impls;

import com.davibilapps.learnsmart.dto.request.ClasseRequest;
import com.davibilapps.learnsmart.dto.response.ClasseResponse;
import com.davibilapps.learnsmart.entity.Classe;
import com.davibilapps.learnsmart.entity.Niveau;
import com.davibilapps.learnsmart.exception.AlreadyExistException;
import com.davibilapps.learnsmart.exception.ResourceNotFoundException;
import com.davibilapps.learnsmart.mappers.ClasseMapper;
import com.davibilapps.learnsmart.repository.ClasseRepository;
import com.davibilapps.learnsmart.repository.NiveauRepository;
import com.davibilapps.learnsmart.services.ClasseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    private final NiveauRepository niveauRepository;

    private final ClasseMapper classeMapper;

    public ClasseServiceImpl(ClasseRepository classeRepository, NiveauRepository niveauRepository, ClasseMapper classeMapper) {
        this.classeRepository = classeRepository;
        this.niveauRepository = niveauRepository;
        this.classeMapper = classeMapper;
    }

    @Override
    public ClasseResponse save(ClasseRequest classeRequest) {


        Niveau niveau = niveauRepository.findByTrackingId(classeRequest.niveauId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Aucun niveau trouvé avec l'UUID : %s", classeRequest.niveauId())));


        if (classeRepository.existsByLibelle(classeRequest.libelle())) {
            throw new AlreadyExistException("La classe existe déjà");
        }

        Classe classe = classeMapper.toEntity(classeRequest);


        Classe classe1 = classeRepository.save(classe);

        return classeMapper.toResponse(classe1);

    }

    @Override
    @Transactional
    public ClasseResponse update(UUID trackingId, ClasseRequest classeRequest) {

        Classe classe = classeRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Aucune classe trouvé avec l'ID : %s", trackingId)));

        if (!classe.getLibelle().equals(classeRequest.libelle())) {
            classe.setLibelle(classeRequest.libelle());
        }

        if (!classe.getNiveau().equals(classeRequest.niveauId())) {

            Niveau niveau = niveauRepository.findByTrackingId(classeRequest.niveauId()).orElseThrow(() -> new ResourceNotFoundException(String.format("introuvable : %s", classeRequest.niveauId())));

            classe.setNiveau(niveau);
        }

        if (!classe.getCode().equals(classeRequest.code())) {
            classe.setCode(classeRequest.code());
        }

        return classeMapper.toResponse(classe);
    }

    @Override
    @Transactional(readOnly = true)
    public ClasseResponse getOne(UUID trackingId) {

        Classe classe = classeRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Rien trouver pour l'ID : ", trackingId)));

        return classeMapper.toResponse(classe);
    }

    @Override
    public void delete(UUID trackingId) {

        Classe classe = classeRepository.findByTrackingId(trackingId).orElseThrow(() -> new ResourceNotFoundException(String.format("Rien trouver pour l'ID : ", trackingId)));

        classeRepository.delete(classe);

    }

    @Override
    @Transactional(readOnly = true)
    public List<ClasseResponse> listAll() {

        List<Classe> classe = classeRepository.findAll();

        return classe.stream().map(classeMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClasseResponse> listAllByNiveau(UUID niveauID) {

        Niveau niveau = niveauRepository.findByTrackingId(niveauID).orElseThrow(() -> new ResourceNotFoundException(String.format("introuvable : %s", niveauID)));


        List<Classe> classe = classeRepository.findAllByNiveauOrderByLibelle(niveau);

        return classe.stream().map(classeMapper::toResponse).toList();
    }
}
