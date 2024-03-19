package com.apptester_commsservice.model;

import com.apptester_commsservice.dto.MoodleResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CommserviceModel {

    public List<Integer> gateAttemptStepsIds(String response) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<MoodleResponse>>(){}.getType();
        List<MoodleResponse> moodleResponse = gson.fromJson(response, type);

        return moodleResponse.getFirst().GetAttemptStepsIds();
    }

    public String uniteResponses(String generalResponse, List<String> taskResponses) {
        StringBuilder generalResponseBuilder = new StringBuilder(generalResponse);
        for (String taskResponse : taskResponses) {
            generalResponseBuilder.append("\n").append(taskResponse);
        }
        return generalResponseBuilder.toString();
    }
}
