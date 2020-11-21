package vananh.example.common.multitenant;

import vananh.example.common.multitenant.model.DataSourceConfigMessage;
import vananh.example.common.payload.request.LoginRequest;
import vananh.example.common.payload.response.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSourceConfigFactory {
    private static final String URI_LOGIN = "http://localhost:8082/auth/login";
    private static final String URI_DATA_SOURCE = "http://localhost:8082/datasource/";

    private AuthResponse authResponse = new AuthResponse();

    @Autowired
    RestTemplate restTemplate;

    @PostConstruct
    public void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("admin@example.com");
        loginRequest.setPassword("admin");
        String jsonStr = restTemplate.postForObject(URI_LOGIN, loginRequest, String.class);
        try {
            authResponse = new ObjectMapper().readValue(jsonStr, AuthResponse.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<DataSourceConfigMessage> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authResponse.getTokenType() + " " + authResponse.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<DataSourceConfigMessage[]> responseEntity  = restTemplate.exchange(URI_DATA_SOURCE, HttpMethod.GET, entity, DataSourceConfigMessage[].class);
        List<DataSourceConfigMessage> result = Arrays.asList(responseEntity.getBody());
        return result;
    }

    public DataSourceConfigMessage get(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", authResponse.getTokenType() + " " + authResponse.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        DataSourceConfigMessage dataSourceConfigResponse = null;

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URI_DATA_SOURCE + "/" + name, HttpMethod.GET, entity, String.class);
            String jsonStr = responseEntity.getBody();
            dataSourceConfigResponse = new ObjectMapper().readValue(jsonStr, DataSourceConfigMessage.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dataSourceConfigResponse;
    }
}
