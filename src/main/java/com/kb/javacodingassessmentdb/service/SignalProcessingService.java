package com.kb.javacodingassessmentdb.service;

import com.kb.javacodingassessmentdb.dto.SignalProcessingDTO;
import com.kb.javacodingassessmentdb.exception.InvalidSignalIdException;
import com.kb.javacodingassessmentdb.service.handler.SignalHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.kb.javacodingassessmentdb.util.SignalProcessingUtil.buildSignalServiceName;

@Service
@RequiredArgsConstructor
public class SignalProcessingService {
    private final Map<String, SignalHandler> signalHandlers;

    public void processSignal(@NonNull SignalProcessingDTO signalDTO) {
        if (signalDTO.getSignalId() == null || signalDTO.getSignalId() <= 0) {
            throw new InvalidSignalIdException("The signalId cannot be null and must be greater than 0.");
        }
        final String serviceName = buildSignalServiceName(signalDTO.getSignalId());

        final SignalHandler signalHandler = signalHandlers.getOrDefault(serviceName, signalHandlers.get(SignalType.SIGNAL_DEFAULT_NAME));
        signalHandler.handleSignal(signalDTO.getSignalId());
    }
}
