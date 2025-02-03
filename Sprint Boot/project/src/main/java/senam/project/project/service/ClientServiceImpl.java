package senam.project.project.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import senam.project.project.entity.Client;
import senam.project.project.repository.ClientRepository;

import java.util.List;
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    public final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> readClient() {

        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long id, Client clientDetail) {
        Client client = clientRepository.findById(id).orElse(null);
        client.setDate_naissance(client.getDate_naissance());
        client.setEmail(client.getEmail());
        client.setNom(client.getNom());
        client.setPrenom(client.getNom());

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
