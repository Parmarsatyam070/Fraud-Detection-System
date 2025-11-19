package com.frauddetection.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil implements DateTimeOperations {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String now() {
        return LocalDateTime.now().format(formatter);
    }
}
