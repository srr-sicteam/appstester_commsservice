package com.apptester_commsservice.controller;

import com.apptester_commsservice.dto.MoodleResponse;
import com.apptester_commsservice.model.CommserviceModel;
import com.apptester_commsservice.service.CommserviceRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class CommsreviceController {
    CommserviceModel commserviceModel = new CommserviceModel();
    CommserviceRestService commserviceRestService = new CommserviceRestService();


    @GetMapping(path = "/init")
    public ResponseEntity<Object> startRequesting() {
        String generalResponse;
        while (true) {
            generalResponse = commserviceRestService.checkForRequests();
            if (!generalResponse.isEmpty()) {
//                List<String> taskResponses = new ArrayList<>();
//                MoodleResponse moodleResponse = commserviceModel.convertResponse(generalResponse);
//                for (int i = 0; i < moodleResponse.attemptStepsIds.size(); i++) {
//                    taskResponses.add(commserviceRestService.receiveTask(moodleResponse.attemptStepsIds.get(i)));
//                }
//                generalResponse = commserviceModel.uniteResponses(generalResponse, taskResponses);
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
