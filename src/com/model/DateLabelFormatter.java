package com.model;

import net.sourceforge.jdatepicker.impl.DateComponentFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * La clase {@code DateLabelFormatter} se encarga de formatear las fechas en el calendario.
 * Hereda de {@link DateComponentFormatter} y proporciona métodos para convertir cadenas de texto
 * en objetos de fecha y viceversa, utilizando el formato de fecha "yyyy-MM-dd".
 *
 * <p>Esta clase es útil para la integración con componentes de selección de fecha que requieren
 * la conversión entre representaciones de fecha en texto y objetos de fecha de Java.</p>
 *
 */
public class DateLabelFormatter extends DateComponentFormatter {

    private final SimpleDateFormat dateFormatter;

    /**
     * Crea una nueva instancia de {@code DateLabelFormatter} con el formato de fecha "yyyy-MM-dd".
     */
    public DateLabelFormatter() {
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    }

    /**
     * Convierte una cadena de texto en una fecha.
     *
     * @param text la cadena de texto que representa una fecha en el formato "yyyy-MM-dd".
     * @return un objeto {@code Date} que representa la fecha.
     * @throws ParseException si la cadena de texto no está en el formato correcto.
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return dateFormatter.parse(text);
    }

    /**
     * Convierte una fecha en una cadena de texto.
     *
     * @param value el objeto que representa una fecha. Debe ser una instancia de {@code Calendar}.
     * @return una cadena de texto que representa la fecha en el formato "yyyy-MM-dd".
     * @throws ParseException si el objeto proporcionado no es una instancia de {@code Calendar}.
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
