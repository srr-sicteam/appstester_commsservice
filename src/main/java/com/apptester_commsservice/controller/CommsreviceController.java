package com.apptester_commsservice.controller;

import com.apptester_commsservice.model.CommserviceModel;
import com.apptester_commsservice.service.CommserviceRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CommsreviceController {
    CommserviceModel commserviceModel = new CommserviceModel();
    CommserviceRestService commserviceRestService = new CommserviceRestService();

    Logger commserviceControllerLogger = Logger.getLogger("CommserviceController");


    @GetMapping(path = "/init")
    public ResponseEntity<Object> startRequesting() {
        String generalResponse;
        while (true) {
            commserviceControllerLogger.log(Level.INFO, "Requesting...");
            generalResponse = commserviceRestService.checkForRequests();
            List<String> taskResponses = new ArrayList<>();
            if (!generalResponse.isEmpty()) {
                commserviceControllerLogger.log(Level.INFO, "Moodle returned response.");
                List<Integer> attemptStepsIds = commserviceModel.getAttemptStepsIds(generalResponse);
                for (Integer attemptStepsId : attemptStepsIds) {
                    taskResponses.add(commserviceRestService.receiveTask(attemptStepsId));
                }
                commserviceModel.parseTaskResponses(taskResponses);
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>(generalResponse, HttpStatus.OK);
    }
}
