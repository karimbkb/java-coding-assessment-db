package com.kb.javacodingassessmentdb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalProcessingResponseDTO {
    private Integer requestedSignalId;
    private String executedHandler;
    private Boolean processed;
}
