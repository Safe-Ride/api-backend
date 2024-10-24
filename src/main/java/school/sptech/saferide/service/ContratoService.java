package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.contrato.Contrato;
import school.sptech.saferide.model.entity.contrato.ContratoRequest;
import school.sptech.saferide.model.entity.contrato.ContratoUpdate;
import school.sptech.saferide.model.entity.dependente.Dependente;
import school.sptech.saferide.model.entity.pagamento.Pagamento;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.StatusPagamento;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.ContratoRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository repository;
    private final UsuarioService usuarioService;
    private final DependenteService dependenteService;
    private final PagamentoService pagamentoService;

    public Contrato criar(
            Contrato payload,
            int motoristaId,
            int responsavelId,
            List<Integer> dependentesId) {
        Usuario motorista = usuarioService.listarPorId(motoristaId);
        Usuario responsavel = usuarioService.listarPorId(responsavelId);
        List<Dependente> dependentes = dependentesId.stream()
                .map(dependenteService::listarPorId)
                .toList();
        payload.setMotorista(motorista);
        payload.setResponsavel(responsavel);
        Contrato contrato = repository.save(payload);
        dependentes.forEach(d -> dependenteService.atualizarContrato(d, contrato));

        for (int i = payload.getDataInicio().getMonth().ordinal() + 1; i <= 12; i++) {
            Pagamento pagamento = new Pagamento();
            pagamento.setContrato(contrato);
            pagamento.setDataVencimento(LocalDate.of(payload.getDataInicio().getYear(), i, 10));
            pagamento.setValor(contrato.getValor());
            pagamento.setStatus(StatusPagamento.PENDENTE);
            if (contrato.getPagamentos() == null) {
                contrato.setPagamentos(new ArrayList<>());
            }
            contrato.getPagamentos().add(pagamentoService.criar(pagamento));
        }
        return repository.save(contrato);
    }

    public Contrato listarPorId(int id) {
        Optional<Contrato> list = repository.findById(id);
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return list.get();
    }

    public List<Contrato> listarPorMotorista(int motoristaId) {
        List<Contrato> list = repository.findByMotoristaId(motoristaId);
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return list;
    }

    public List<Contrato> listarPorResponsavel(int responsavelId) {
        List<Contrato> list = repository.findByResponsavelId(responsavelId);
        if (list.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return list;
    }

    public Optional<Contrato> listarPorResponsavelIdAndMotoristaId(int responsavelId, int motoristaId) {
        return repository.findByResponsavelIdAndMotoristaId(responsavelId, motoristaId);
    }


    public Contrato atualizar(ContratoUpdate dto) {
        Contrato contrato = listarPorId(dto.getId());
        dependenteService.listarPorId(dto.getDependenteId());
        contrato.setValor(contrato.getValor() + dto.getValor());
        repository.save(contrato);

        LocalDate dataHoje = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        List<Pagamento> pagamentos = pagamentoService.listarPorContratoIdAndAnoAtual(contrato.getId(), dataHoje.getYear());

        pagamentos.stream()
                .filter(pagamento -> dataHoje.getYear() == pagamento.getDataVencimento().getYear())
                .filter(pagamento -> pagamento.getDataVencimento().isAfter(dataHoje))
                .forEach(pagamento -> {
                    pagamento.setValor(contrato.getValor());
                    pagamentoService.criar(pagamento);
                });

        return repository.save(contrato);
    }
}
