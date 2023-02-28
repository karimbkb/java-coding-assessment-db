package com.kb.javacodingassessmentdb.service.handler;

import com.kb.javacodingassessmentdb.service.AlgoService;
import com.kb.javacodingassessmentdb.service.SignalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service(SignalType.SIGNAL1_NAME)
@RequiredArgsConstructor
public class Signal1Handler implements SignalHandler {
    private final AlgoService algoService;

    @Override
    public void handleSignal(int signalId) {
        log.debug("Start processing signal [{}]", signalId);
        algoService.setUp();
        algoService.setAlgoParam(1,60);
        algoService.performCalc();
        algoService.submitToMarket();
        algoService.doAlgo();
        log.debug("End processing signal [{}]", signalId);
    }
}
