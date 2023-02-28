package com.kb.javacodingassessmentdb.controller;

import com.kb.javacodingassessmentdb.service.AlgoService;
import com.kb.javacodingassessmentdb.service.SignalProcessingService;
import com.kb.javacodingassessmentdb.service.SignalType;
import com.kb.javacodingassessmentdb.service.handler.Signal1Handler;
import com.kb.javacodingassessmentdb.service.handler.SignalHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static com.kb.javacodingassessmentdb.test.util.TestDataUtil.getStringFromResources;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SignalController.class)
@Import({SignalProcessingService.class})
public class SignalControllerTest {

    private final String SIGNAL_URL_POST = "/api/v1/signal/processSignal";

    AlgoService algoService = new AlgoService();
    Signal1Handler signal1Processor = new Signal1Handler(algoService);

    @MockBean
    Map<String, SignalHandler> signalBaseProcessors;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldProcessSignal() throws Exception {
        // given
        when(signalBaseProcessors.getOrDefault("SIGNAL-1", signalBaseProcessors.get(SignalType.SIGNAL_DEFAULT_NAME)))
                .thenReturn(signal1Processor);

        // when + then
        mockMvc
                .perform(post(SIGNAL_URL_POST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getStringFromResources("requests/processing-signal.json")))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotProcessSignalBecauseSignalIdIsZero() throws Exception {
        // when + then
        mockMvc
                .perform(post(SIGNAL_URL_POST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getStringFromResources("requests/processing-signal-with-signal-id-zero.json")))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void shouldNotProcessSignalBecauseSignalIdIsNull() throws Exception {
        // when + then
        mockMvc
                .perform(post(SIGNAL_URL_POST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getStringFromResources("requests/processing-signal-with-signal-id-null.json")))
                .andExpect(status().is5xxServerError());
    }
}