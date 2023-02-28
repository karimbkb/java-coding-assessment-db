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
class DefaultSignalHandlerTest {

    @Mock
    private AlgoService algoService;

    @InjectMocks
    private DefaultSignalHandler defaultSignalHandler;

    @Test
    void shouldProcessDefaultSignal() {
        // given
        int signalId = 999;

        // when
        defaultSignalHandler.handleSignal(signalId);

        // then
        verify(algoService).cancelTrades();
        verify(algoService).doAlgo();
        verifyNoMoreInteractions(algoService);
    }
}
