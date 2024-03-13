package com.apptester_commsservice.controller;

import com.apptester_commsservice.dto.CommserviceIncomingRequest;
import com.apptester_commsservice.model.CommserviceModel;
import com.apptester_commsservice.service.CommserviceRestService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class CommsreviceController {
    CommserviceModel commserviceModel = new CommserviceModel();
    CommserviceRestService commserviceRestService = new CommserviceRestService();


    @GetMapping(path = "/init")
    public ResponseEntity<Object> startRequesting() {

        while (true) {
            String response = commserviceRestService.sendRequest();
            if (!response.isEmpty()) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>("Done!", HttpStatus.OK);
    }
}
