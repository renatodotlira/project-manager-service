package com.agnes.pm.service;

import com.agnes.pm.dto.ClientDTO;
import com.agnes.pm.repository.ClientRepository;
import com.agnes.pm.model.Client;
import com.agnes.pm.translate.ClientTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Optional<Client> create(@NotNull Client client) {
        return Optional.of(repository.save(client));
    }

    public Optional<Client> update(@NotNull Client client) {
        repository.findById(client.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client.not.found"));
        return Optional.of(repository.save(client));
    }

    public ClientDTO findById(@NotNull Long id) {
        return repository.findById(id)
                .map(ClientTranslator::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "client.not.found"));
    }

    public void delete(@NotNull Long id) {
        this.findById(id);
        repository.deleteById(id);
    }

    public List<Client> list() {
        return repository.findAll();
    }

}