package com.kb.javacodingassessmentdb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignalProcessingDTO {
    @NotNull
    @Min(value = 1, message = "The signal id should be greater or equal 1")
    private Integer signalId;
}
