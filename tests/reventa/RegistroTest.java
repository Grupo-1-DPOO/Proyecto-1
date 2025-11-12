package reventa;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;

public class RegistroTest {

    // Verifica que el constructor inicialice correctamente fecha y descripcion
    @Test
    void constructorInicializaCorrectamente() {
        Registro r = new Registro("Venta realizada correctamente");

        assertEquals("Venta realizada correctamente", r.getDescripcion());
        assertNotNull(r.getFechaHora(), "La fecha y hora no deben ser nulas");
        assertTrue(Duration.between(r.getFechaHora(), LocalDateTime.now()).abs().getSeconds() < 2, "La fechaHora debe corresponder aproximadamente al momento actual");
    }

    //verifica que cada registro tenga una fecha única sin importar si se crean muy seguidos
    @Test
    void cadaRegistroTieneFechaUnica() throws InterruptedException {
        Registro r1 = new Registro("Primera acción");
        Thread.sleep(1000); //para que tenga retraso de 1 segundo
        Registro r2 = new Registro("Segunda acción");

        assertNotEquals(r1.getFechaHora(), r2.getFechaHora(), "Cada registro debe tener una marca temporal distinta");
    }

    //Verifica que toString retorna el formato correcto
    @Test
    void toStringFormateaCorrectamente() {
        Registro r = new Registro("Oferta publicada");
        String out = r.toString();
        String iso = r.getFechaHora().toString().substring(0, 10);
        assertTrue(out.contains(iso), "Debe incluir la fecha");
        assertTrue(out.indexOf(iso) < out.indexOf(r.getDescripcion()), "La fecha debe aparecer antes de la descripción");
    }

}
