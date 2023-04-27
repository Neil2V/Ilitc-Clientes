package com.prueba.ilitc.service;

import com.prueba.ilitc.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente saveCliente(Cliente client);

    public Optional<Cliente> findByEmail(String email);

    List<Cliente> getAllClientes();

    Optional<Cliente> getClienteById(long id);

    Cliente updateCliente(Cliente client);

    void deleteCliente(long id);

    boolean existsByEmail(String email);

    boolean existsById(Long id);
}
