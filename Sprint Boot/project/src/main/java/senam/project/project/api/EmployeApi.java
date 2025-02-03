package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Adresse;
import senam.project.project.entity.Employe;
import senam.project.project.service.EmployeService;

@Controller
@RestController
@RequestMapping("/api/employes")
public class EmployeApi {
    private final EmployeService employeService;

    public EmployeApi(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping()
    public ResponseEntity readEmploye(){
        return new ResponseEntity<>(employeService.readEmploye(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createEmploye(@RequestBody Employe employe){
        return new ResponseEntity<>(employeService.createEmploye(employe), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateEmploye(@PathVariable Long id, @RequestBody Employe employeDetail){
        return new ResponseEntity<>(employeService.updateEmploye(id, employeDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmploye(@PathVariable Long id){
        employeService.delteEmploye(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
