package com.apptester_commsservice.controller;

import com.apptester_commsservice.dto.CommserviceIncomingRequest;
import com.apptester_commsservice.model.CommserviceModel;
import com.apptester_commsservice.service.CommserviceRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommsreviceController {
    CommserviceModel commserviceModel = new CommserviceModel();
    CommserviceRestService commserviceRestService = new CommserviceRestService();

    @PostMapping(path = "/input")
    public ResponseEntity<Object> getRequest(@RequestBody CommserviceIncomingRequest request) {
        if (commserviceModel.validateToken(request.token)) {
            return new ResponseEntity<>("Request proceeded", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/output")
    public ResponseEntity<Object> sendRequest() {
        String response = commserviceRestService.sendRequest();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
