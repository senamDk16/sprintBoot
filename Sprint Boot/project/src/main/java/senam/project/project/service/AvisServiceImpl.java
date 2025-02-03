package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Avis;
import senam.project.project.repository.AvisRepository;

import java.util.List;
@Service
@Transactional
public class AvisServiceImpl implements AvisService {
    public final AvisRepository  avisRepository;

    public AvisServiceImpl(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public List<Avis> readAvis() {

        return avisRepository.findAll();
    }

    @Override
    public Avis updateAvis(Long id, Avis avisDetail) {
        Avis avis = avisRepository.findById(id).orElse(null);
        avis.setCommentaire(avis.getCommentaire());
        avis.setNote(avisDetail.getNote());

        return avisRepository.save(avis);
    }

    @Override
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }
}
