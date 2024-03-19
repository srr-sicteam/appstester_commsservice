package com.apptester_commsservice.dto;

import com.google.gson.JsonObject;
import lombok.Data;

import java.util.List;

@Data
public class MoodleResponse {

    public int attemptId;
    public List<Integer> attemptStepsIds;
}
