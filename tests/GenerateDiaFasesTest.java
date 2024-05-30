import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GenerateDiaFasesTest {
    private Menstruacion menstruacion;
    private GenerateDiaFases generateDiaFases;

    @Before
    public void setUp() {
        menstruacion = new Menstruacion();
        menstruacion.setMediaCiclo(28);
        menstruacion.setMediaSangrado(5);

        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.MAY, 1);
        menstruacion.setLastperiod(cal.getTime());

        generateDiaFases = new GenerateDiaFases(menstruacion);
    }

    @Test
    public void testCalculoMediaFaseFolicular() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        assertEquals(11, mediaFaseFolicular);
        assertEquals(11, menstruacion.getDuracionFaseFolicular());
    }

    @Test
    public void testCalculoFaseLutea() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = generateDiaFases.CalculoFaseLutea(mediaFaseFolicular);
        assertEquals(12, mediaFaseLutea);
        assertEquals(12, menstruacion.getDuracionFaseLutea());
    }

    @Test
    public void testCalculoFaseOvulacion() {
        int mediaFaseOvulacion = generateDiaFases.CalculoFaseOvulacion();
        assertEquals(3, mediaFaseOvulacion);
        assertEquals(3, menstruacion.getDuracionFaseOvular());
    }

    @Test
    public void testCalculoFaseMenstrual() {
        generateDiaFases.CalculoFaseMenstrual();
        assertEquals(5, menstruacion.getMediaSangrado());
    }

    @Test
    public void testCalculoInicioFaseFolicular() {
        generateDiaFases.CalculoMediaFaseFolicular();
        Date inicioFaseFolicular = generateDiaFases.CalculoInicioFaseFolicular();

        Calendar cal = Calendar.getInstance();
        cal.setTime(menstruacion.getLastperiod());
        cal.add(Calendar.DAY_OF_MONTH, menstruacion.getMediaSangrado());
        assertEquals(cal.getTime(), inicioFaseFolicular);
    }

    @Test
    public void testCalculoInicioFaseLutea() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        generateDiaFases.CalculoFaseOvulacion();  // Set the duration of the ovulation phase
        Date inicioFaseLutea = generateDiaFases.CalculoInicioFaseLutea(mediaFaseFolicular);

        Calendar cal = Calendar.getInstance();
        cal.setTime(menstruacion.getLastperiod());
        cal.add(Calendar.DAY_OF_MONTH, menstruacion.getMediaSangrado() + mediaFaseFolicular + 3);
        assertEquals(cal.getTime(), inicioFaseLutea);
    }

    @Test
    public void testCalculoInicioFaseOvulacion() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = generateDiaFases.CalculoFaseLutea(mediaFaseFolicular);
        Date inicioFaseOvulacion = generateDiaFases.CalculoInicioFaseOvulacion(mediaFaseFolicular, mediaFaseLutea);

        Calendar cal = Calendar.getInstance();
        cal.setTime(menstruacion.getLastperiod());
        cal.add(Calendar.DAY_OF_MONTH, mediaFaseFolicular + menstruacion.getMediaSangrado());
        assertEquals(cal.getTime(), inicioFaseOvulacion);
    }

    @Test
    public void testCalculoSiguienteFaseMenstrual() {
        Date siguienteFaseMenstrual = generateDiaFases.CalculoSiguienteFaseMenstrual(menstruacion.getLastperiod());

        Calendar cal = Calendar.getInstance();
        cal.setTime(menstruacion.getLastperiod());
        cal.add(Calendar.DAY_OF_MONTH, menstruacion.getMediaCiclo());
        assertEquals(cal.getTime(), siguienteFaseMenstrual);
    }

    @Test
    public void testCalcularTodasLasFases() {
        generateDiaFases.calcularTodasLasFases();

        assertNotNull(menstruacion.getNextPeriod());
        assertNotNull(menstruacion.getNextFaseFolicular());
        assertNotNull(menstruacion.getNextFaseOvular());
        assertNotNull(menstruacion.getNextFaseLutea());
    }
}
