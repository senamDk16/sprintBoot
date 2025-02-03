package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Produit;
import senam.project.project.service.ProduitService;

@RestController
@RequestMapping("/api/produits")
@Controller
public class ProduitApi {
    private final ProduitService produitService;

    public ProduitApi(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping()
    public ResponseEntity readProduit(){
        return new ResponseEntity<>(produitService.readProduit(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createProduit(@RequestBody Produit produit){
        return new ResponseEntity<>(produitService.createProduit(produit), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateProduit(@PathVariable Long id, @RequestBody Produit produitDetail){
        return new ResponseEntity<>(produitService.updateProduit(id, produitDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduit(@PathVariable Long id){
        produitService.deleteProduit(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
