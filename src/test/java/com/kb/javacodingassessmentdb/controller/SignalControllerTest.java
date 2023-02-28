package com.kb.javacodingassessmentdb.controller;

import com.kb.javacodingassessmentdb.service.AlgoService;
import com.kb.javacodingassessmentdb.service.SignalProcessingService;
import com.kb.javacodingassessmentdb.service.SignalType;
import com.kb.javacodingassessmentdb.service.handler.DefaultSignalHandler;
import com.kb.javacodingassessmentdb.service.handler.Signal1Handler;
import com.kb.javacodingassessmentdb.service.handler.SignalHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static com.kb.javacodingassessmentdb.test.util.TestDataUtil.getStringFromResources;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignalController.class)
@Import({SignalProcessingService.class})
public class SignalControllerTest {

    private final String SIGNAL_URL_POST = "/api/v1/signal/processSignal";

    @MockBean
    Map<String, SignalHandler> signalBaseProcessors;

    @Autowired
    private MockMvc mockMvc;

    private Signal1Handler signal1Processor;
    private DefaultSignalHandler defaultSignalHandler;

    @BeforeEach
    void beforeEach() {
        AlgoService algoService = new AlgoService();
        signal1Processor = new Signal1Handler(algoService);
        defaultSignalHandler = new DefaultSignalHandler(algoService);
    }

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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.processed", is(true)))
                .andExpect(jsonPath("$.executedHandler", is("Signal1Handler")))
                .andExpect(jsonPath("$.requestedSignalId", is(1)));
    }

    @Test
    void shouldProcessSignalWithDefaultHandler() throws Exception {
        // given
        when(signalBaseProcessors.getOrDefault("SIGNAL-99", signalBaseProcessors.get(SignalType.SIGNAL_DEFAULT_NAME)))
                .thenReturn(defaultSignalHandler);

        // when + then
        mockMvc
                .perform(post(SIGNAL_URL_POST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getStringFromResources("requests/processing-signal-with-non-existing-id.json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.processed", is(true)))
                .andExpect(jsonPath("$.executedHandler", is("DefaultSignalHandler")))
                .andExpect(jsonPath("$.requestedSignalId", is(99)));
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
