package com.calendario;

import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Clase que se encarga de formatear las fechas en el calendario.

 */
public class DateLabelFormatter extends DateComponentFormatter {

    private final SimpleDateFormat dateFormatter;

    public DateLabelFormatter() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * Convierte un texto en una fecha.
     * @param text
     * @return fecha
     * @throws ParseException
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return dateFormatter.parse(text);
    }

    /**
     * Convierte una fecha en un texto.
     * @param value
     * @return texto
     * @throws ParseException
     */
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
