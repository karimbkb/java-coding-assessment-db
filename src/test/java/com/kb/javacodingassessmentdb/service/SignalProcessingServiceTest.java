package com.kb.javacodingassessmentdb.service;

import com.kb.javacodingassessmentdb.dto.SignalProcessingDTO;
import com.kb.javacodingassessmentdb.exception.InvalidSignalIdException;
import com.kb.javacodingassessmentdb.service.handler.Signal1Handler;
import com.kb.javacodingassessmentdb.service.handler.Signal2Handler;
import com.kb.javacodingassessmentdb.service.handler.Signal3Handler;
import com.kb.javacodingassessmentdb.service.handler.SignalHandler;
import com.kb.javacodingassessmentdb.util.SignalProcessingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class SignalProcessingServiceTest {

    @Mock
    private Signal1Handler signal1Processor;
    @Mock
    private Signal2Handler signal2Processor;
    @Mock
    private Signal3Handler signal3Processor;

    SignalProcessingService signalProcessingService;
    Map<String, SignalHandler> signalBaseProcessors;

    @BeforeEach
    void init() {
        signalBaseProcessors =
                Map.of(
                        "SIGNAL-1", signal1Processor,
                        "SIGNAL-2", signal2Processor,
                        "SIGNAL-3", signal3Processor
                );

        signalProcessingService = new SignalProcessingService(signalBaseProcessors);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void shouldProcessSignal1(int signalId) {
        // given
        SignalProcessingDTO signalProcessingDTO = SignalProcessingDTO.builder()
                .signalId(signalId)
                .build();

        // when
        signalProcessingService.processSignal(signalProcessingDTO);

        // then
        SignalHandler signalHandler = signalBaseProcessors.get(SignalProcessingUtil.buildSignalServiceName(signalId));
        verify(signalHandler).handleSignal(signalProcessingDTO.getSignalId());
    }

    @Test
    void shouldThrowInvalidSignalIdExceptionBecauseSignalIdIsZero() {
        // given
        SignalProcessingDTO signalProcessingDTO = SignalProcessingDTO.builder()
                .signalId(0)
                .build();

        // when
        assertThatThrownBy(() -> signalProcessingService.processSignal(signalProcessingDTO))
                .isInstanceOf(InvalidSignalIdException.class);

        // then
        verifyNoInteractions(signal1Processor);
    }

    @Test
    void shouldThrowInvalidSignalIdExceptionBecauseSignalIdIsNull() {
        // given
        SignalProcessingDTO signalProcessingDTO = SignalProcessingDTO.builder()
                .signalId(null)
                .build();

        // when
        assertThatThrownBy(() -> signalProcessingService.processSignal(signalProcessingDTO))
                .isInstanceOf(InvalidSignalIdException.class);

        // then
        verifyNoInteractions(signal1Processor);
    }
}
