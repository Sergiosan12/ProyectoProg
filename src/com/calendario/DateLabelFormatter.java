package com.calendario;

import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends DateComponentFormatter {

    private SimpleDateFormat dateFormatter;

    public DateLabelFormatter() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value == null) {
            return "";
        }
        if (value instanceof Calendar) {
            return dateFormatter.format(((Calendar) value).getTime());
        }
        throw new ParseException("Invalid object type", 0);
    }
}
