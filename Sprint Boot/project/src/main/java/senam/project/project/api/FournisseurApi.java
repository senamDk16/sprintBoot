package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Fournisseur;
import senam.project.project.service.FournisseurService;

@RestController
@RequestMapping("/api/fournisseur")
@Controller
public class FournisseurApi {
    private final FournisseurService fournisseurService;

    public FournisseurApi(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping()
    public ResponseEntity readFournisseur(){
        return new ResponseEntity<>(fournisseurService.readFournisseur(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createFournisseur(@RequestBody Fournisseur fournisseur){
        return new ResponseEntity<>(fournisseurService.createFournisseur(fournisseur), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseurDetail){
        return new ResponseEntity<>(fournisseurService.updateFournisseur(id, fournisseurDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteFournisseur(@PathVariable Long id){
        fournisseurService.deleteFournisseur(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
