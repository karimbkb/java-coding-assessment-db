package com.kb.javacodingassessmentdb.test.util;


import com.kb.javacodingassessmentdb.controller.SignalControllerTest;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

public class TestDataUtil {

    public static String getStringFromResources(String filename) throws IOException {
        return IOUtils.toString(
                Objects.requireNonNull(SignalControllerTest.class.getClassLoader().getResourceAsStream(filename)),
                Charset.defaultCharset());
    }
}
