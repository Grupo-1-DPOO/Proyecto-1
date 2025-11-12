package reventa;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import usuarios.Cliente;

//Stub de Cliente
class ClienteStub extends Cliente {
    public ClienteStub(String log, String cont) { super(log, cont); }
}

public class ContraofertaTest {

	// Verifica que el constructor inicalice de manera consistente el comprador, precio, fecha y estado de manera correcta
    @Test
    void constructorInicializaCorrectamente() {
        ClienteStub comprador = new ClienteStub("buyer", "pw");
        Contraoferta c = new Contraoferta(comprador, 150.0);

        assertSame(comprador, c.getComprador());                     
        assertEquals(150.0, c.getNuevoPrecio(), 1e-9);                      
        assertNotNull(c.getFecha());                                
        assertTrue(Duration.between(c.getFecha(), LocalDateTime.now()).abs().getSeconds() < 2, "La fecha debe ser 'ahora' (tolerancia de 2s).");
        assertEquals(Contraoferta.Estado.Pendiente, c.getEstado());   
    }

    // Verifica que pueda cambiar de estado a aceptada y rechazada
    @Test
    void cambiaCorrectamenteDeEstado() {
        Contraoferta c = new Contraoferta(new ClienteStub("b","p"), 99.0);

        c.setEstado(Contraoferta.Estado.Aceptada);
        assertEquals(Contraoferta.Estado.Aceptada, c.getEstado());   
        c.setEstado(Contraoferta.Estado.Rechazada);
        assertEquals(Contraoferta.Estado.Rechazada, c.getEstado());  
    }

    // Verifica la inmutabilidad de comprador y nuevoprecio, lo que evita el cambio de comprador por fuera del marketplace
    @Test
    void compradorYNuevoPrecioSonInmutables() {
        ClienteStub comprador = new ClienteStub("buyer","pw");
        Contraoferta c = new Contraoferta(comprador, 200.0);

        assertSame(comprador, c.getComprador());
        assertEquals(200.0, c.getNuevoPrecio(), 1e-9);
    }
}
