package school.sptech.saferide.service.utils;

import org.springframework.stereotype.Component;
import school.sptech.saferide.model.entity.rota.Rota;
import school.sptech.saferide.model.entity.trajeto.Trajeto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TrajetoArquivoTxt {

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
            corpo += String.format("%04d", trajeto.getId());
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

//    public static void leArquivoTxt(String nomeArq) {
//        BufferedReader entrada = null;
//        String registro, tipoRegistro;
//        Integer id, tipo, horario, dia;
//        Boolean ativo;
//        String nome, artista, duracao, album, genero;
//        int contaRegDados = 0;
//        int qtdRegGravados;
//
//        List<Trajeto> listaLida = new ArrayList<>();
//
//        try {
//            entrada = new BufferedReader((new FileReader(nomeArq)));
//        } catch (IOException erro) {
//            System.out.println("Erro ao abrir o arquivo: " + erro.getMessage());
//        }
//
//        try {
//            registro = entrada.readLine();
//
//            while (registro != null) {
//                tipoRegistro = registro.substring(0, 2);
//
//                if (tipoRegistro.equals("02")) {
//                    id = Integer.parseInt(registro.substring(2, 6));
//                    nome = registro.substring(6, 26).trim();
//                    artista = registro.substring(26, 46).trim();
//                    duracao = registro.substring(46, 51);
//                    album = registro.substring(51, 71).trim();
//                    nota = Double.valueOf(registro.substring(71, 76).replace(',', '.'));
//                    genero = registro.substring(76, 86).trim();
//
//                    Trajeto a = new Trajeto(id, nome, artista, duracao, album, nota, genero);
//                    listaLida.add(a);
//                } else {
//                    System.out.println("Registro inválido");
//                }
//
//                registro = entrada.readLine();
//            }
//        } catch (IOException erro) {
//            erro.printStackTrace();
//        }
//    }
}
