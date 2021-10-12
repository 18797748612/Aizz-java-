package com.aizhong.common;

import org.springframework.core.convert.converter.Converter;

import java.text.*;
import java.util.Date;

public class MyDateConverter implements Converter<String, Date> {
    private String pattern;

    @Override
    public Date convert(String source) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
