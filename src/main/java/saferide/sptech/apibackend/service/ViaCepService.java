package saferide.sptech.apibackend.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import saferide.sptech.apibackend.dto.endereco.ViaCepResponse;

import java.io.IOException;

@Service
public class ViaCepService {

    public ViaCepResponse getEndereco(String cep) throws ClientProtocolException, IOException{
        ViaCepResponse endereco = null;

        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);

                Gson gson = new Gson();

                endereco = gson.fromJson(result, ViaCepResponse.class);
            }
        }

        return endereco;
    }

}
