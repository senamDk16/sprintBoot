package senam.project.project.service;

import senam.project.project.entity.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    List<Client> readClient();
    Client updateClient(Long id, Client clientDetail);
    void deleteClient(Long id);
}
