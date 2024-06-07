package saferide.sptech.apibackend.service;

import  lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import saferide.sptech.apibackend.dto.trajeto.TrajetoMapper;
import saferide.sptech.apibackend.dto.trajeto.TrajetoRequestUpdate;
import saferide.sptech.apibackend.dto.trajeto.TrajetoResponse;
import saferide.sptech.apibackend.entity.*;
import saferide.sptech.apibackend.repository.RotaRepository;
import saferide.sptech.apibackend.repository.TrajetoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class    TrajetoService {

    private final TrajetoRepository repository;
    private final EscolaService escolaService;
    private final UsuarioService usuarioService;
    private final RotaRepository rotaRepository;

    public Trajeto criar(
            Trajeto payload,
            int motoristaId,
            int escolaId) {
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        Escola escola = escolaService.listarPorId(escolaId);
        payload.setMotorista(motorista);
        payload.setEscola(escola);
        return repository.save(payload);
    }

    public List<Trajeto> listar() {
        List<Trajeto> trajetos = repository.findAll();
        if (trajetos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return trajetos;
    }

    public Trajeto listarPorId(int id) {
        Optional<Trajeto> trajetoOpt = repository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return trajetoOpt.get();
    }

    public Trajeto atualizar(int id, TrajetoRequestUpdate request) {
        Optional<Trajeto> trajetoOpt = repository.findById(id);
        if (trajetoOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Trajeto entity = TrajetoMapper.toEntityAtt(request, trajetoOpt.get());
        return repository.save(entity);
    }

    public Void remover(int id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return null;
    }

    public TrajetoResponse listarTrajetoCompleto(Integer trajetoId) {
        Trajeto trajeto = listarPorId(trajetoId);

        List<Rota> rotas = rotaRepository.findByTrajetoId(trajetoId);

        List<TrajetoResponse.DependenteComEndereco> dependentesDTO = rotas.stream().map(
                rota -> TrajetoResponse.DependenteComEndereco.builder()
                .id(rota.getDependente().getId())
                .nome(rota.getDependente().getNome())
                .serie(rota.getDependente().getSerie())
                .endereco(TrajetoResponse.DependenteComEndereco.Endereco.builder()
                        .id(rota.getEndereco().getId())
                        .cep(rota.getEndereco().getCep())
                        .numero(rota.getEndereco().getNumero())
                        .complemento(rota.getEndereco().getComplemento())
                        .build())
                .build()).collect(Collectors.toList());

        TrajetoResponse trajetoDTO = new TrajetoResponse();
        trajetoDTO.setId(trajeto.getId());
        trajetoDTO.setTipo(trajeto.getTipo());
        trajetoDTO.setDiaSemana(trajeto.getDiaSemana());
        trajetoDTO.setEscola(TrajetoResponse.Escola.builder()
                .id(trajeto.getEscola().getId())
                .nome(trajeto.getEscola().getNome())
                .endereco(TrajetoResponse.Escola.Endereco.builder()
                        .id(trajeto.getEscola().getEndereco().getId())
                        .cep(trajeto.getEscola().getEndereco().getCep())
                        .numero(trajeto.getEscola().getEndereco().getNumero())
                        .build()).build());
        trajetoDTO.setMotorista(TrajetoResponse.Motorista.builder()
                .id(trajeto.getMotorista().getId())
                .nome(trajeto.getMotorista().getNome())
                .build());
        trajetoDTO.setDependentes(dependentesDTO);

        return trajetoDTO;
    }

}
