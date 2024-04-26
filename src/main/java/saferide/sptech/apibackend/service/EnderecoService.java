package saferide.sptech.apibackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.endereco.EnderecoMapper;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequest;
import saferide.sptech.apibackend.dto.endereco.EnderecoRequestUpdate;
import saferide.sptech.apibackend.dto.endereco.EnderecoResponse;
import saferide.sptech.apibackend.entity.Usuario;
import saferide.sptech.apibackend.entity.Endereco;
import saferide.sptech.apibackend.repository.UsuarioRepository;
import saferide.sptech.apibackend.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;

    public EnderecoResponse criar(EnderecoRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(request.getUsuarioId());
        if (usuarioOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Endereco entity = EnderecoMapper.toEntity(request, usuarioOpt.get());
        Endereco saveEndereco = enderecoRepository.save(entity);
        return EnderecoMapper.toDto(saveEndereco);
    }

    public List<EnderecoResponse> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return EnderecoMapper.toDto(enderecos);
    }

    public EnderecoResponse listarPorId(int id) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
        if (enderecoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return EnderecoMapper.toDto(enderecoOpt.get());
    }

    public EnderecoResponse atualizar(int id, EnderecoRequestUpdate request) {
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(id);
        if (enderecoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Endereco entity = EnderecoMapper.toEntityAtt(request, enderecoOpt.get());
        Endereco saveEndereco = enderecoRepository.save(entity);
        return EnderecoMapper.toDto(saveEndereco);
    }

    public Void remover(int id) {
        if (!enderecoRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        enderecoRepository.deleteById(id);
        return null;
    }
}
