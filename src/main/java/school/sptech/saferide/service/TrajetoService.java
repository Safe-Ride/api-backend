package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.RotaRepository;
import school.sptech.saferide.repository.TrajetoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrajetoService {

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

    public Trajeto listarPorId(int id) {
        Optional<Trajeto> trajetoOpt = repository.findById(id);
        if (trajetoOpt.isEmpty()) throw new NotFoundException("Trajeto");
        return trajetoOpt.get();
    }

    public Void remover(int id) {
        listarPorId(id);
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
                        .status(rota.getStatus())
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
