package com.apptester_commsservice.model;

import com.apptester_commsservice.model.taskParameters.StudentAnswerType;
import com.apptester_commsservice.model.taskParameters.TaskType;
import com.apptester_commsservice.model.taskParameters.TeacherAnswerType;
import lombok.Builder;

@Builder
public class AttemptModel {
    private int attemptStepId;

    private TaskType taskType;

    private StudentAnswerType studentAnswertype;

    private String studentAnswer;

    private TeacherAnswerType teacherAnswertype;

    private String teacherAnswer;

    private String parameters;
}
