package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Adresse;
import senam.project.project.repository.AdresseRepository;

import java.util.List;
@Service
@Transactional
public class AdresseServiceImpl implements AdresseService {
    public final AdresseRepository adresseRepository;
    public AdresseServiceImpl(AdresseRepository adresseRepository){
        this.adresseRepository = adresseRepository;
    }
    @Override
    public Adresse createAdresse(Adresse adresse) {
        return adresseRepository.save(adresse);
    }

    @Override
    public List<Adresse> readAdresse() {

        return adresseRepository.findAll();
    }

    @Override
    public Adresse updateAdresse(Long id, Adresse adresseDetail) {
        Adresse adresse = adresseRepository.findById(id).orElse(null);
        adresse.setCodePostal(adresseDetail.getCodePostal());
        adresse.setPays(adresseDetail.getPays());
        adresse.setRue(adresseDetail.getRue());
        adresse.setVille(adresseDetail.getVille());
        return adresseRepository.save(adresse);
    }

    @Override
    public void deleteAdresse(Long id) {
        adresseRepository.deleteById(id);
    }
}
