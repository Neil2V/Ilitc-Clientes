package com.prueba.ilitc.service.Impl;

import com.prueba.ilitc.entity.Cliente;
import com.prueba.ilitc.exception.ResourceNotFoundException;
import com.prueba.ilitc.repository.ClienteRepository;
import com.prueba.ilitc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> findByEmail(String email){
        return clienteRepository.findByEmail(email);
    };
    @Override
    public Cliente saveCliente(Cliente client) {
        return clienteRepository.save(client);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente updateCliente(Cliente client) {
        return clienteRepository.save(client);
    }

    @Override
    public void deleteCliente(long id) {
        clienteRepository.deleteById(id);
    }

    public boolean existsByEmail(String email){
        return clienteRepository.existsByEmail(email);
    }

    public boolean existsById(Long id){
        return clienteRepository.existsById(id);
    }


}
