package com.kb.javacodingassessmentdb.util;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SignalProcessingUtil {
    private final static String PREFIX = "SIGNAL-";

    public static String buildSignalServiceName(@NonNull Integer signalId) {
        return PREFIX + signalId;
    }
}
