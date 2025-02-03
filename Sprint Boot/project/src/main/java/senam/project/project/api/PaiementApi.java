package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Paiement;
import senam.project.project.service.PaiementService;

@RestController
@RequestMapping("/api/paiements")
@Controller
public class PaiementApi {
    private final PaiementService paiementService;

    public PaiementApi(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @GetMapping()
    public ResponseEntity readPaiement(){
        return new ResponseEntity<>(paiementService.readPaiement(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createPaiement(@RequestBody Paiement paiement){
        return new ResponseEntity<>(paiementService.createPaiement(paiement), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updatePaiement(@PathVariable Long id, @RequestBody Paiement paiementDetail){
        return new ResponseEntity<>(paiementService.updatePaiement(id, paiementDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePaiement(@PathVariable Long id){
        paiementService.deletePaiement(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
