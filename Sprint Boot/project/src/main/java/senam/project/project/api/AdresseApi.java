package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Adresse;
import senam.project.project.service.AdresseService;

@Controller
@RestController
@RequestMapping("/api/adresse")
public class AdresseApi {
    private final AdresseService adresseService;

    public AdresseApi(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @GetMapping()
    public ResponseEntity readAdresse(){
        return new ResponseEntity<>(adresseService.readAdresse(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createAdresse(@RequestBody Adresse adresse){
        return new ResponseEntity<>(adresseService.createAdresse(adresse), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateAdresse(@PathVariable Long id, @RequestBody Adresse adresseDetail){
        return new ResponseEntity<>(adresseService.updateAdresse(id, adresseDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAdresse(@PathVariable Long id){
        adresseService.deleteAdresse(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
