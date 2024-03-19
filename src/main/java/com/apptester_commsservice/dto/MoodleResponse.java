package com.apptester_commsservice.dto;

import com.google.gson.JsonObject;
import lombok.Data;

import java.util.List;

@Data
public class MoodleResponse {

    private int attemptId;
    private List<Integer> attemptStepsIds;

    public List<Integer> GetAttemptStepsIds() {
        return attemptStepsIds;
    }
}
