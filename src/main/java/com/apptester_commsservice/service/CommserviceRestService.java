package com.apptester_commsservice.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommserviceRestService {

    RestTemplate restTemplate = new RestTemplate();

    public String checkForRequests() {
        HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("https://sicteam.ru/stage/webservice/rest/server.php" +
                "?moodlewsrestformat=json&wstoken=0a89a62b754d8dfedf9338fd33cab9f3" +
                "&wsfunction=qtype_adtesting_get_submissions_to_check", HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }

    public String receiveTask(int attemptStepId) {
        HttpEntity<String> requestEntity = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange("https://sicteam.ru/stage/webservice/rest/server.php" +
                "?moodlewsrestformat=json&wstoken=0a89a62b754d8dfedf9338fd33cab9f3" +
                "&wsfunction=qtype_adtesting_get_submission&attemptstepid=" + attemptStepId, HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }
}
