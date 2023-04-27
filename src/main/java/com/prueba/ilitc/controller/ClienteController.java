package com.prueba.ilitc.controller;

import com.prueba.ilitc.dto.ClienteDto;
import com.prueba.ilitc.entity.Cliente;
import com.prueba.ilitc.service.ClienteService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/ilitc/cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/create")
    public ResponseEntity<?> createCliente(@RequestBody ClienteDto clienteDto) {

        System.out.println(clienteDto.toString());
        LocalDate fecha = LocalDate.parse(clienteDto.getFechaNacimiento(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        if (StringUtils.isBlank(clienteDto.getNombres()))
            return new ResponseEntity("El nombre es requerido", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(clienteDto.getApellidoMaterno()))
            return new ResponseEntity("El apellido materno es requerido", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(clienteDto.getApellidoPaterno()))
            return new ResponseEntity("El apellido paterno es requerido", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(clienteDto.getEmail()))
            return new ResponseEntity("El email es requerido", HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(clienteDto.getDireccion()))
            return new ResponseEntity("La direccion es requerida", HttpStatus.BAD_REQUEST);
        if (clienteDto.getSexo() == null)
            return new ResponseEntity("El sexo es requerido", HttpStatus.BAD_REQUEST);
        if (clienteService.existsByEmail(clienteDto.getEmail()))
            return new ResponseEntity("Ya existe un cliente con ese correo", HttpStatus.BAD_REQUEST);

        Cliente cliente = Cliente.builder()
                .nombres(clienteDto.getNombres())
                .apellidoPaterno(clienteDto.getApellidoPaterno())
                .apellidoMaterno(clienteDto.getApellidoMaterno())
                .sexo(clienteDto.getSexo())
                .fechaNacimiento(java.sql.Date.valueOf(fecha))
                .direccion(clienteDto.getDireccion())
                .email(clienteDto.getEmail()).build();

        clienteService.saveCliente(cliente);

        return new ResponseEntity("Cliente registrado", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cliente>> listClientes(){
        List<Cliente> clientes = clienteService.getAllClientes();

        return new ResponseEntity(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable("id") Long id){
        if(!clienteService.existsById(id))
            return new ResponseEntity("El Cliente no existe", HttpStatus.NOT_FOUND);

        return new ResponseEntity(clienteService.getClienteById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@RequestBody ClienteDto clienteDto, @PathVariable(value = "id") Long id){
        if(!clienteService.existsById(id))
            return new ResponseEntity("El cliente no existe", HttpStatus.NOT_FOUND);

        Cliente cliente = clienteService.getClienteById(id).get();
        cliente.setNombres(clienteDto.getNombres());
        cliente.setApellidoPaterno(clienteDto.getApellidoPaterno());
        cliente.setApellidoMaterno(clienteDto.getApellidoMaterno());
        cliente.setSexo(clienteDto.getSexo());
        cliente.setFechaNacimiento(java.sql.Date.valueOf(clienteDto.getFechaNacimiento()));
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setEmail(clienteDto.getEmail());

        clienteService.saveCliente(cliente);

        return new ResponseEntity("Cliente Actualizado",  HttpStatus.OK);
    }
/*
    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateByEmail(@RequestBody ClienteDto clienteDto, @PathVariable(value = "email") String email){
        if(!clienteService.existsByEmail(email))
            return new ResponseEntity("El cliente no existe", HttpStatus.NOT_FOUND);

        Cliente cliente = clienteService.findByEmail(email).get();
        cliente.setNombres(clienteDto.getNombres());
        cliente.setApellidoPaterno(clienteDto.getApellidoPaterno());
        cliente.setApellidoMaterno(clienteDto.getApellidoMaterno());
        cliente.setSexo(clienteDto.getSexo());
        cliente.setFechaNacimiento(java.sql.Date.valueOf(clienteDto.getFechaNacimiento()));
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setEmail(clienteDto.getEmail());

        clienteService.saveCliente(cliente);

        return new ResponseEntity("Cliente Actualizado",  HttpStatus.OK);
    }
*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!clienteService.existsById(id))
            return new ResponseEntity("El cliente no existe", HttpStatus.NOT_FOUND);

        clienteService.deleteCliente(id);

        return new ResponseEntity("Cliente eliminado", HttpStatus.OK);
    }
}
