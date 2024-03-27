package com.appstester_commsservice.controller;

import com.appstester_commsservice.model.CommserviceModel;
import com.appstester_commsservice.service.CommserviceRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CommserviceController {
    CommserviceModel commserviceModel = new CommserviceModel();
    CommserviceRestService commserviceRestService = new CommserviceRestService();

    Logger commserviceControllerLogger = Logger.getLogger("CommserviceController");


    @GetMapping(path = "/requesting")
    public ResponseEntity<String> requestMoodle() {
        String moodleResponse = commserviceRestService.checkForRequests();
        return new ResponseEntity<>(moodleResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/processing")
    public ResponseEntity<Object> requestMoodleTasks(@RequestBody String moodleResponse) {
        List<String> taskResponses = new ArrayList<>();
        List<Integer> attemptStepsIds = commserviceModel.getAttemptStepsIds(moodleResponse);
        for (Integer attemptStepsId : attemptStepsIds) {
            taskResponses.add(commserviceRestService.receiveTask(attemptStepsId));
        }
        String ctrlResponse = commserviceModel.makeResponseForCtrlservice(taskResponses);
        return new ResponseEntity<>(ctrlResponse, HttpStatus.OK);
    }
}
