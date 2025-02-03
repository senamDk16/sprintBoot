package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Avis;
import senam.project.project.service.AvisService;

public class AvisApi {
    private final AvisService avisService;

    public AvisApi(AvisService avisService) {
        this.avisService = avisService;
    }

    @GetMapping()
    public ResponseEntity readAvis(){
        return new ResponseEntity<>(avisService.readAvis(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createAvis(@RequestBody Avis adresse){
        return new ResponseEntity<>(avisService.createAvis(adresse), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateAvis(@PathVariable Long id, @RequestBody Avis avisDetail){
        return new ResponseEntity<>(avisService.updateAvis(id, avisDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAvis(@PathVariable Long id){
        avisService.deleteAvis(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
