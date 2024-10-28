package school.sptech.saferide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.saferide.model.entity.escola.Escola;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.trajeto.Trajeto;
import school.sptech.saferide.model.entity.trajeto.TrajetoMapper;
import school.sptech.saferide.model.entity.trajeto.TrajetoRequest;
import school.sptech.saferide.model.entity.trajeto.TrajetoResponse;
import school.sptech.saferide.model.entity.usuario.Usuario;
import school.sptech.saferide.model.enums.DiaSemana;
import school.sptech.saferide.model.enums.HorarioTrajeto;
import school.sptech.saferide.model.enums.TipoTrajeto;
import school.sptech.saferide.model.exception.NotFoundException;
import school.sptech.saferide.repository.TrajetoRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrajetoService {

    private final TrajetoRepository repository;
    private final EscolaService escolaService;
    private final UsuarioService usuarioService;

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

    public List<TrajetoResponse> listarTrajetosPorMotorista(Integer motoristaId) {
        List<Trajeto> trajetos = repository.findTrajetosByMotoristaId(motoristaId);

        return trajetos.stream()
                .map(TrajetoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Trajeto atualizarAtivo(int trajetoId, Boolean ativo) {
        Trajeto trajeto = listarPorId(trajetoId);
        trajeto.setAtivo(ativo);
        return repository.save(trajeto);
    }

    public Void remover(int id) {
        listarPorId(id);
        repository.deleteById(id);
        return null;
    }

    public InputStreamResource baixarTrajetos(int motoristaId) {
        List<Trajeto> trajetos = repository.findTrajetosByMotoristaId(motoristaId);
        if (trajetos.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        byte[] arquivoTxt = geraArquivoTxt(trajetos);

        return new InputStreamResource(new ByteArrayInputStream(arquivoTxt));
    }

    public byte[] geraArquivoTxt(List<Trajeto> lista) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int contaRegDados = 0;

        // Gerar o cabeçalho
        String header = "00TRAJETOS";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        header += "01\n";
        try {
            outputStream.write(header.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Gerar o corpo
        for (Trajeto trajeto : lista) {
            String corpo = "02";
            corpo += String.format("%04d", trajeto.getMotorista().getId());
            corpo += String.format("%-20.20S", trajeto.getMotorista().getNome());
            corpo += String.format("%04d", trajeto.getEscola().getId());
            corpo += String.format("%-20.20S", trajeto.getEscola().getNome());
            corpo += String.format("%1d", trajeto.getTipo().ordinal());
            corpo += String.format("%1d", trajeto.getHorario().ordinal());
            corpo += String.format("%1d", trajeto.getDiaSemana().ordinal());
            corpo += "\n";

            try {
                outputStream.write(corpo.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }

            contaRegDados++;

            for (Rota rota : trajeto.getRotas()) {
                corpo = "03";
                corpo += String.format("%04d", rota.getId());
                corpo += String.format("%04d", rota.getDependente().getId());
                corpo += String.format("%-20.20S", rota.getDependente().getNome());
                corpo += String.format("%04d", rota.getEndereco().getId());
                corpo += String.format("%-20.20S", rota.getEndereco().getCep());
                corpo += String.format("%-5.5S", "12:00");
                corpo += String.format("%04d", rota.getDependente().getResponsavel().getId());
                corpo += String.format("%-20.20S", rota.getDependente().getResponsavel().getNome());
                corpo += "\n";

                try {
                    outputStream.write(corpo.getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                contaRegDados++;
            }
        }

        // Gerar o trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        trailer += "\n";

        try {
            outputStream.write(trailer.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    public void lerTrajetos(InputStream inputStream) {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String registro;
            Trajeto trajetoDaVez = null;

            registro = entrada.readLine();

            while ((registro = entrada.readLine()) != null) {
                String tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("02")) {
                    try {
                        Integer morotistaId = Integer.parseInt(registro.substring(2, 6));
                        Integer escolaId = Integer.parseInt(registro.substring(26, 30));
                        TipoTrajeto tipo = TipoTrajeto.mapNumber(Integer.parseInt(registro.substring(50, 51)) + 1);
                        HorarioTrajeto horario = HorarioTrajeto.mapNumber(Integer.parseInt(registro.substring(51, 52)) + 1);
                        DiaSemana diaSemana = DiaSemana.mapNumber(Integer.parseInt(registro.substring(52, 53)) + 1);

                        trajetoDaVez = criar(
                                TrajetoMapper.toEntity(
                                        new TrajetoRequest(tipo, horario, diaSemana, escolaId, morotistaId)
                                ),
                                escolaId,
                                morotistaId);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter número: " + e.getMessage());
                    }

                }

//                if (tipoRegistro.equals("04")) {
//                    Integer dependenteId = Integer.parseInt(registro.substring(2,6));
//                    Integer enderecoId = Integer.parseInt(registro.substring(26,30));
//                    String horario = registro.substring(50, 55);
//
//                    assert trajetoDaVez != null;
//
//                    rotaService.criar(
//                            RotaMapper.toEntity(
//                                    new RotaRequest()
//                            ),
//                            trajetoDaVez.getId(),
//                            dependenteId,
//                            enderecoId);
//
//                } else {
//                    System.out.println("Registro inválido");
//                }

                registro = entrada.readLine();
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler o arquivo", e);
        }
    }

}
