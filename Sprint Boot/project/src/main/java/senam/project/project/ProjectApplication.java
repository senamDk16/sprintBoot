package senam.project.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import senam.project.project.entity.*;
import senam.project.project.repository.*;

import java.util.Date;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(
			AdresseRepository adresseRepository,
			AvisRepository avisRepository,
			CategorieRepository categorieRepository,
			ClientRepository clientRepository,
			EmployeRepository employeRepository,
			FournisseurRepository fournisseurRepository,
			PaiementRepository paiementRepository,
			ProduitRepository produitRepository
	){
		return args -> {
			adresseRepository.save(new Adresse(null, "21Road","Lome","3232","Togo"));
			avisRepository.save(new Avis(null,12,"cool"));
			categorieRepository.save(new Categorie(null,"some","cool"));
			clientRepository.save(new Client(null,"senam","Jean", new Date(), "se@gmail.com"));
			employeRepository.save(new Employe(null,"se","claude","92134247","s@gmail.com","3234",true));
			fournisseurRepository.save(new Fournisseur(null,"kkk","oooo","se@gmail.com","322322323",true));
			paiementRepository.save(new Paiement(null,new Date(), 200000));
			produitRepository.save(new Produit(null,"Mac","i like it",20000));
		} ;
	}

}
