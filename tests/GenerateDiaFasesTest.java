import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateDiaFasesTest {
    private static final int EXPECTED_AVERAGE_FOLLICULAR_PHASE = 11;
    private Menstruacion menstruacion;
    private GenerateDiaFases generateDiaFases;

    @BeforeEach
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
    public void testShouldCalculateAverageFollicularPhase() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        assertEquals(EXPECTED_AVERAGE_FOLLICULAR_PHASE, mediaFaseFolicular);
        assertEquals(EXPECTED_AVERAGE_FOLLICULAR_PHASE, menstruacion.getDuracionFaseFolicular());
    }
    @Test
    public void testCalculoMediaFaseFolicular() {
        int mediaFaseFolicular = GenerateDiaFases.CalculoMediaFaseFolicular();
        assertEquals(11, mediaFaseFolicular);
    }

    @Test
    public void testCalculoFaseLutea() {
        int mediaFaseFolicular = GenerateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = GenerateDiaFases.CalculoFaseLutea(mediaFaseFolicular);
        assertEquals(12, mediaFaseLutea);
    }

    @Test
    public void testCalculoFaseOvulacion() {
        int mediaFaseOvulacion = GenerateDiaFases.CalculoFaseOvulacion();
        assertEquals(3, mediaFaseOvulacion);
    }

    @Test
    public void testCalculoFaseMenstrual() {
        GenerateDiaFases.CalculoFaseMenstrual();
        assertEquals(5, menstruacion.getMediaSangrado());
    }


    @Test
    public void testCalculoSiguienteFaseMenstrual() {
        Date siguienteFaseMenstrual = GenerateDiaFases.CalculoSiguienteFaseMenstrual(menstruacion.getLastperiod());
        assertNotNull(siguienteFaseMenstrual);
    }

    @Test
    public void testCalcularTodasLasFases() {
        GenerateDiaFases.calcularTodasLasFases();
        assertNotNull(menstruacion.getNextPeriod());
        assertNotNull(menstruacion.getNextFaseFolicular());
        assertNotNull(menstruacion.getNextFaseOvular());
        assertNotNull(menstruacion.getNextFaseLutea());
    }
}