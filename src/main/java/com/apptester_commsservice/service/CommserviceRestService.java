package com.apptester_commsservice.service;

import com.apptester_commsservice.dto.CommserviceOutgoingRequest;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommserviceRestService {

    RestTemplate restTemplate = new RestTemplate();
    Gson gson = new Gson();

    public String sendRequest() {
        CommserviceOutgoingRequest request = CommserviceOutgoingRequest.builder()
                .message("Hello world!")
                .build();
        String json = gson.toJson(request);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("http://example.com", HttpMethod.POST, requestEntity, String.class);
        return response.getBody();
    }
}
