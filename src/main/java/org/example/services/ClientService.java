package org.example.services;

import org.example.exception.BadCredentialsException;
import org.example.exception.NotFoundException;
import org.example.models.Client;
import org.example.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

public class ClientService implements AutoCloseable {

    private ClientRepository clientRepository = new ClientRepository();

    public void register(Client newClient) {
        Boolean exists = clientRepository.existsByEmail(newClient.getEmail());
        if (exists) {
            throw new IllegalStateException(
                    String.format("client with email = %s already in use!", newClient.getEmail())
            );
        }
        clientRepository.save(newClient);

    }

    public boolean login(String email, String password) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(
                        "client with email =" + email + "not found!"
                ));
        if (password.equals(client.getPassword())) {
            return true;
        }
        throw new BadCredentialsException(
                "incorrect password"
        );
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public void close() throws Exception {
        clientRepository.close();
    }
}
