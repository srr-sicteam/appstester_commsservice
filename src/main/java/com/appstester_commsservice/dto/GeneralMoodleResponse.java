package com.appstester_commsservice.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GeneralMoodleResponse {

    private int attemptId;

    private List<Integer> attemptStepsIds;
}
