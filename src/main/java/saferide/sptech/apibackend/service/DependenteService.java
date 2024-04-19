package saferide.sptech.apibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.cliente.ClienteMapper;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestDto;
import saferide.sptech.apibackend.dto.cliente.ClienteRequestUpdateDto;
import saferide.sptech.apibackend.dto.cliente.ClienteResponseDto;
import saferide.sptech.apibackend.dto.dependente.DependenteMapper;
import saferide.sptech.apibackend.dto.dependente.DependenteRequest;
import saferide.sptech.apibackend.dto.dependente.DependenteRequestUpdate;
import saferide.sptech.apibackend.dto.dependente.DependenteResponse;
import saferide.sptech.apibackend.entity.Cliente;
import saferide.sptech.apibackend.entity.Dependente;
import saferide.sptech.apibackend.repository.ClienteRepository;
import saferide.sptech.apibackend.repository.DependenteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DependenteService {

    private static DependenteRepository dependenteRepository;
    private static ClienteRepository clienteRepository;

    @Autowired
    public DependenteService(DependenteRepository dependenteRepository, ClienteRepository clienteRepository) {
        this.dependenteRepository = dependenteRepository;
        this.clienteRepository = clienteRepository;
    }

    public DependenteResponse criar(DependenteRequest request) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(request.getClienteId());
        if (clienteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Dependente entity = DependenteMapper.toEntity(request, clienteOpt.get());
        Dependente saveCliente = dependenteRepository.save(entity);
        return DependenteMapper.toDto(saveCliente);
    }

    public List<DependenteResponse> listar() {
        List<Dependente> dependentes = dependenteRepository.findAll();
        if (dependentes.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return DependenteMapper.toDto(dependentes);
    }

    public DependenteResponse listarPorId(int id) {
        Optional<Dependente> dependenteOpt = dependenteRepository.findById(id);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return DependenteMapper.toDto(dependenteOpt.get());
    }

    public DependenteResponse atualizar(int id, DependenteRequestUpdate request) {
        Optional<Dependente> dependenteOpt = dependenteRepository.findById(id);
        if (dependenteOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Dependente entity = DependenteMapper.toEntityAtt(request,dependenteOpt.get());
        Dependente saveCliente = dependenteRepository.save(entity);
        return DependenteMapper.toDto(saveCliente);
    }

    public Void remover(int id) {
        if (!dependenteRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        dependenteRepository.deleteById(id);
        return null;
    }
}
