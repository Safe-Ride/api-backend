package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.endereco.EnderecoMapper;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequest;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequestUpdate;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;

    public Endereco criar(EnderecoRequest request) {
        Usuario usuario = usuarioService.listarPorId(request.getUsuarioId());
        Endereco entity = EnderecoMapper.toEntity(request, usuario);
        return repository.save(entity);
    }

    public List<Endereco> listar() {
        List<Endereco> enderecos = repository.findAll();
        if (enderecos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return enderecos;
    }

    public Endereco listarPorId(int id) {
        Optional<Endereco> enderecoOpt = repository.findById(id);
        if (enderecoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return enderecoOpt.get();
    }

    public Endereco atualizar(int id, EnderecoRequestUpdate request) {
        Optional<Endereco> enderecoOpt = repository.findById(id);
        if (enderecoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Endereco entity = EnderecoMapper.toEntityAtt(request, enderecoOpt.get());
        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }

}
