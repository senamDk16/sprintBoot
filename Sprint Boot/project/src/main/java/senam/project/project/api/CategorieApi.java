package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Categorie;
import senam.project.project.service.CategorieService;

@Controller
@RequestMapping("/api/categorie")
@RestController
public class CategorieApi {
    private final CategorieService categorieService;

    public CategorieApi(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping()
    public ResponseEntity readCategorie(){
        return new ResponseEntity<>(categorieService.readCategorie(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createCategorie(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categorieService.createCategorie(categorie), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateCategorie(@PathVariable Long id, @RequestBody Categorie categorieDetail){
        return new ResponseEntity<>(categorieService.updateCategorie(id, categorieDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategorie(@PathVariable Long id){
        categorieService.deleteCategorie(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
