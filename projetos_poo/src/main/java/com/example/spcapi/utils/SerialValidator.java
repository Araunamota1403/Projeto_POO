package com.example.spcapi.utils;

public class SerialValidator {
    private static final String SERIAL_REGEX = "^\\d{6}(spc|SPC)\\d{6}$";

    public static boolean isValid(String serial) {
        return serial != null && serial.matches(SERIAL_REGEX);
    }
}