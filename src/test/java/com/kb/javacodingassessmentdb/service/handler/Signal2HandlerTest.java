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
class Signal2HandlerTest {

    @Mock
    private AlgoService algoService;

    @InjectMocks
    private Signal2Handler signal2Handler;

    @Test
    void shouldProcessSignal2() {
        // given
        int signalId = 2;

        // when
        signal2Handler.handleSignal(signalId);

        // then
        verify(algoService).reverse();
        verify(algoService).setAlgoParam(1,80);
        verify(algoService).submitToMarket();
        verify(algoService).doAlgo();
        verifyNoMoreInteractions(algoService);
    }
}
