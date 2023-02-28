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
class Signal3HandlerTest {

    @Mock
    private AlgoService algoService;

    @InjectMocks
    private Signal3Handler signal3Handler;

    @Test
    void shouldHandleSignal3() {
        //given
        int signalId = 3;

        // when
        signal3Handler.handleSignal(signalId);

        // then
        verify(algoService).setAlgoParam(1, 90);
        verify(algoService).setAlgoParam(2,15);
        verify(algoService).performCalc();
        verify(algoService).submitToMarket();
        verify(algoService).doAlgo();
        verifyNoMoreInteractions(algoService);
    }
}