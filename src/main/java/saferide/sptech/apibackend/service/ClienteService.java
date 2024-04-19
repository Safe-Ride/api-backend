package saferide.sptech.apibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.cliente.ClienteMapper;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestDto;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestUpdateDto;
import saferide.sptech.apibackend.dto.cliente.ClienteResponseDto;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.repository.ClienteRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private static ClienteRepository clienteRepository;
    private static DependenteRepository dependenteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, DependenteRepository dependenteRepository) {
        this.clienteRepository = clienteRepository;
        this.dependenteRepository = dependenteRepository;
    }

    public ClienteResponseDto criar(ClienteRequestDto body) {
        Cliente entity = ClienteMapper.toEntity(body);
        Cliente saveCliente = clienteRepository.save(entity);
        return ClienteMapper.toDto(saveCliente);
    }

    public List<ClienteResponseDto> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return ClienteMapper.toDto(clientes);
    }

    public ClienteResponseDto listarPorId(int id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ClienteMapper.toDto(clienteOpt.get());
    }

    public ClienteResponseDto atualizar(int id, ClienteRequestUpdateDto request) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Cliente entity = ClienteMapper.toEntityAtt(request,clienteOpt.get());
        Cliente saveCliente = clienteRepository.save(entity);
        return ClienteMapper.toDto(saveCliente);
    }

    public Void remover(int id) {
        if (!clienteRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        clienteRepository.deleteById(id);
        return null;
    }
}
