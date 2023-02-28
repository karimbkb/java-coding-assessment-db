package com.kb.javacodingassessmentdb.service;

public enum SignalType {
    SIGNAL1(SignalType.SIGNAL1_NAME),
    SIGNAL2(SignalType.SIGNAL2_NAME),
    SIGNAL3(SignalType.SIGNAL3_NAME),
    SIGNAL_DEFAULT(SignalType.SIGNAL_DEFAULT_NAME);

    public static final String SIGNAL1_NAME = "SIGNAL-1";
    public static final String SIGNAL2_NAME = "SIGNAL-2";
    public static final String SIGNAL3_NAME = "SIGNAL-3";
    public static final String SIGNAL_DEFAULT_NAME = "SIGNAL_DEFAULT";

    private String signalName;

    SignalType(String signalName) {
        this.signalName = signalName;
    }
}
