package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.pagamento.Pagamento;
import school.sptech.saferide.model.view.ListarStatusPagamentosCsvView;
import school.sptech.saferide.model.view.PagamentoStatusView;
import school.sptech.saferide.model.view.PagamentosTotalEfetuadosView;
import school.sptech.saferide.model.view.RendaBrutaMesView;
import school.sptech.saferide.repository.PagamentoRepository;
import school.sptech.saferide.repository.view.ListarStatusPagamentosCsvViewRepository;
import school.sptech.saferide.repository.view.PagamentoStatusViewRepository;
import school.sptech.saferide.repository.view.PagamentosTotalEfetuadosViewRepository;
import school.sptech.saferide.repository.view.RendaBrutaMesViewRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.ByteArrayInputStream;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;
    private final ListarStatusPagamentosCsvViewRepository listarStatusPagamentosCsvViewRepository;
    private final PagamentoStatusViewRepository pagamentoStatusView;
    private final RendaBrutaMesViewRepository rendaBrutaMesViewRepository;
    private final PagamentosTotalEfetuadosViewRepository pagamentosTotalEfetuadosViewRepository;

    public Pagamento criar(Pagamento payload) {
        return repository.save(payload);
    }

    public PagamentoStatusView listarPagamentoStatusView() {
        return pagamentoStatusView.findAll().get(0);
    }

    public List<RendaBrutaMesView> listarRendaBrutaMesView() {
        return rendaBrutaMesViewRepository.findAll();
    }

    public List<PagamentosTotalEfetuadosView> listarPagamentosTotalEfetuadosView() {
        return pagamentosTotalEfetuadosViewRepository.findAll();
    }

    public InputStreamResource baixarCsv(int motoristaId) {
        List<ListarStatusPagamentosCsvView> pagamentos = listarStatusPagamentosCsvViewRepository.findByMotoristaId(motoristaId);
        if (pagamentos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        byte[] arquivoCsv = geraArquivoCsv(pagamentos);

        return new InputStreamResource(new ByteArrayInputStream(arquivoCsv));
    }


    public byte[] geraArquivoCsv(List<ListarStatusPagamentosCsvView> lista) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Cabeçalho do CSV
        String header = "Responsavel Nome,Responsavel Email,Pagamento Data Criação,Pagamento Data Vencimento,Pagamento Data Efetuação,Pagamento Tipo,Pagamento Valor,Pagamento Status,Motorista ID\n";
        try {
            outputStream.write(header.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Corpo do CSV
        for (ListarStatusPagamentosCsvView pagamento : lista) {
            String linha = String.join(",",
                    pagamento.getResponsavelNome(),
                    pagamento.getResponsavelEmail(),
                    pagamento.getPagamentoDataCriacao() != null ? pagamento.getPagamentoDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                    pagamento.getPagamentoDataVencimento() != null ? pagamento.getPagamentoDataVencimento() : "",
                    pagamento.getPagamentoDataEfetuacao() != null ? pagamento.getPagamentoDataEfetuacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "",
                    pagamento.getPagamentoTipo(),
                    pagamento.getPagamentoValor() != null ? String.valueOf(pagamento.getPagamentoValor()) : "0.0",
                    pagamento.getPagamentoStatus(),
                    pagamento.getMotoristaId() != null ? String.valueOf(pagamento.getMotoristaId()) : ""
            ) + "\n";

            try {
                outputStream.write(linha.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return outputStream.toByteArray();
    }

}
