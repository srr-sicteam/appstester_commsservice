package com.apptester_commsservice.dto;

import lombok.Getter;

@Getter
public class AttemptMoodleResponse {

    private int attempstepid;

    private String tasktype;

    private String student_answertype;

    private String student_answer;

    private String teacher_answertype;

    private String teacher_answer;

    private String parameters;

}
