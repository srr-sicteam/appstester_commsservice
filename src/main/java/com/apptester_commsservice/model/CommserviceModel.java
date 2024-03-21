package com.apptester_commsservice.model;

import com.apptester_commsservice.dto.AttemptMoodleResponse;
import com.apptester_commsservice.dto.GeneralMoodleResponse;
import com.apptester_commsservice.model.taskParameters.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommserviceModel {

    Logger commserviceModelLogger = Logger.getLogger("CommserviceModel");

    private <T, Y> T parseJson(String json, Class<Y> requestedClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, (Type) requestedClass);
    }

    public List<Integer> getAttemptStepsIds(String response) {
        Type type = new TypeToken<List<GeneralMoodleResponse>>(){}.getType();
        List<GeneralMoodleResponse> generalMoodleResponse = parseJson(response, type.getClass());

        return generalMoodleResponse.getFirst().getAttemptStepsIds();
    }

    public void parseTaskResponses(List<String> taskResponses) {
        List<AttemptModel> attemptModels = new ArrayList<>();
        for (String taskResponse: taskResponses) {
            AttemptMoodleResponse attempt = parseJson(taskResponse, AttemptMoodleResponse.class);
            TaskType taskType = null;
            StudentAnswerType studentAnswerType = null;
            TeacherAnswerType teacherAnswerType = null;
            if (attempt.getTasktype().equals("android_submission")) {
                taskType = new AndroidSubmission();
            } else {
                commserviceModelLogger.log(Level.WARNING, "There is no such task type: " + attempt.getTasktype());
            }
            if (attempt.getStudent_answertype().equals("github_link")) {
                studentAnswerType = new GithubLink();
            } else {
                commserviceModelLogger.log(Level.WARNING, "There is no such student answer type: " + attempt.getStudent_answertype());
            }
            if (attempt.getTeacher_answertype().equals("gitea_link")) {
                teacherAnswerType = new GiteaLink();
            } else {
                commserviceModelLogger.log(Level.WARNING, "There is no such teacher task type: " + attempt.getTeacher_answertype());
            }
            AttemptModel attemptModel = AttemptModel.builder()
                    .attemptStepId(attempt.getAttempstepid())
                    .taskType(taskType)
                    .studentAnswertype(studentAnswerType)
                    .studentAnswer(attempt.getStudent_answer())
                    .teacherAnswertype(teacherAnswerType)
                    .teacherAnswer(attempt.getTeacher_answer())
                    .parameters(attempt.getParameters())
                    .build();
            attemptModels.add(attemptModel);
        }
    }
}
