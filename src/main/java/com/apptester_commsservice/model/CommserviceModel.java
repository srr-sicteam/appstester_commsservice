package com.apptester_commsservice.model;

import com.apptester_commsservice.dto.MoodleResponse;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class CommserviceModel {

    private static <T> T parseJson(String response, Class<T> fin) {
        Gson gson = new Gson();

        return gson.fromJson(response, (Type) fin);
    }

    public MoodleResponse convertResponse(String response) {
        return parseJson(response, MoodleResponse.class);
    }

    public String uniteResponses(String generalResponse, List<String> taskResponses) {
        StringBuilder generalResponseBuilder = new StringBuilder(generalResponse);
        for (String taskResponse : taskResponses) {
            generalResponseBuilder.append("\n").append(taskResponse);
        }
        return generalResponseBuilder.toString();
    }
}
