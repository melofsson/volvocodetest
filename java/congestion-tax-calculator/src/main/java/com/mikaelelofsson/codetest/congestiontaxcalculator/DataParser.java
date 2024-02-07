package com.mikaelelofsson.codetest.congestiontaxcalculator;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataParser {

    public static Date[] getDatesFromStrings(String[] dateStrings) {
        Date[] dates = new Date[dateStrings.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < dateStrings.length; i++) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateStrings[i], formatter);
            dates[i] = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return dates;
    }

}
