package senam.project.project.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import senam.project.project.entity.Client;
import senam.project.project.service.ClientService;

@Controller
@RestController
@RequestMapping("/api/clients")
public class ClientApi {
    private final ClientService clientService;

    public ClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity readClient(){
        return new ResponseEntity<>(clientService.readClient(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client){
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity  updateClient(@PathVariable Long id, @RequestBody Client clientDetail){
        return new ResponseEntity<>(clientService.updateClient(id, clientDetail), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>("delete ok", HttpStatus.OK);
    }
}
