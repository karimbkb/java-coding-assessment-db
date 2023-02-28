package com.kb.javacodingassessmentdb.service.handler;

import com.kb.javacodingassessmentdb.service.AlgoService;
import com.kb.javacodingassessmentdb.service.SignalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(SignalType.SIGNAL_DEFAULT_NAME)
@RequiredArgsConstructor
public class DefaultSignalHandler implements SignalHandler {
    private final AlgoService algoService;

    @Override
    public void handleSignal(int signalId) {
        log.debug("Start processing default signal");
        algoService.cancelTrades();
        algoService.doAlgo();
        log.debug("End processing default signal");
    }
}
