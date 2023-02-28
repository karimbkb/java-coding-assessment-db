package com.kb.javacodingassessmentdb.service.handler;

import com.kb.javacodingassessmentdb.service.AlgoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class Signal1HandlerTest {

    @Mock
    private AlgoService algoService;

    @InjectMocks
    private Signal1Handler signal1Handler;

    @Test
    void shouldProcessSignal1() {
        // given
        int signalId = 1;

        // when
        signal1Handler.handleSignal(signalId);

        // then
        verify(algoService).setUp();
        verify(algoService).setAlgoParam(1,60);
        verify(algoService).performCalc();
        verify(algoService).submitToMarket();
        verify(algoService).doAlgo();
        verifyNoMoreInteractions(algoService);
    }
}
