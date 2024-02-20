package com.apptester_commsservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommserviceOutgoingRequest {

    public String message;
}
