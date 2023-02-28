package com.kb.javacodingassessmentdb.controller;

import com.kb.javacodingassessmentdb.dto.SignalProcessingDTO;
import com.kb.javacodingassessmentdb.dto.SignalProcessingResponseDTO;
import com.kb.javacodingassessmentdb.service.SignalProcessingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/signal")
public class SignalController {
    private final SignalProcessingService signalService;

    @PostMapping("/processSignal")
    @ResponseStatus(OK)
    public ResponseEntity<SignalProcessingResponseDTO> processSignal(@Valid @RequestBody SignalProcessingDTO signalDTO) {
        log.info("Processing signal with signalDTO [{}]", signalDTO);
        signalService.processSignal(signalDTO);
        SignalProcessingResponseDTO responseDTO = SignalProcessingResponseDTO.builder()
                .signalId(signalDTO.getSignalId())
                .processed(true)
                .build();
        return ResponseEntity.ok(responseDTO);
    }
}
