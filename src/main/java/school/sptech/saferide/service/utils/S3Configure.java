package school.sptech.saferide.service.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Configure {
    private S3Client criarClienteS3() {
        Region region = Region.US_EAST_1;
        return S3Client.builder().region(region).build();
    }

    public byte[] baixarArquivoS3(String nomeArquivo) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket("bucket-foto-perfil")
                    .key(nomeArquivo)
                    .build();
            S3Client s3 = criarClienteS3();
            return s3.getObjectAsBytes(getObjectRequest).asByteArray();
        } catch (NoSuchKeyException e) {
            System.out.println("Erro: Arquivo não encontrado - " + nomeArquivo);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Foto não encontrada");
        }
    }

    public void gravarArquivoS3(String nomeArquivo, byte[] conteudoArquivo) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("bucket-foto-perfil")
                    .key(nomeArquivo)
                    .acl(ObjectCannedACL.PUBLIC_READ)
                    .build();

            software.amazon.awssdk.core.sync.RequestBody requestBody =
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(conteudoArquivo);

            S3Client s3 = criarClienteS3();
            s3.putObject(putObjectRequest, requestBody);
        } catch (Exception erro) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, erro.getMessage());
        }
    }
}
