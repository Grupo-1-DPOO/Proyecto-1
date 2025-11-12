package reventa;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import usuarios.Cliente;
import tiquetes.Tiquete;

//Stubs 
class ClienteStub3 extends Cliente {
    public ClienteStub3(String log, String cont) { super(log, cont); }
}

class TiqueteStub extends Tiquete {
    public TiqueteStub(String id) { super(); this.setIdentificador(id); }
    @Override public String imprimir() { return getIdentificador(); }
}

public class OfertaTest {

    private ClienteStub3 vendedor;
    private TiqueteStub tiquete;
    private Oferta oferta;

    @BeforeEach
    void setup() {
        vendedor = new ClienteStub3("vendedor1", "1234");
        tiquete = new TiqueteStub("TIQ-001");
        oferta = new Oferta("OF-001", vendedor, tiquete, 100.0);
    }

    //Verifica que el constructor asigne correctamente todos los atributos
    @Test
    void constructorInicializaCorrectamente() {
        assertEquals("OF-001", oferta.getIdOferta());
        assertSame(vendedor, oferta.getVendedor());
        assertSame(tiquete, oferta.getTiquete());
        assertEquals(100.0, oferta.getPrecio(), 1e-9);
        assertTrue(oferta.isActiva(), "La oferta debe iniciar activa");
        assertNotNull(oferta.getFechaPublicacion(), "Debe registrarse la fecha de publicación");
        assertTrue(Duration.between(oferta.getFechaPublicacion(), LocalDateTime.now()).abs().getSeconds() < 2, "La fecha debe corresponder al momento actual");
        assertNotNull(oferta.getContraofertas(), "La lista de contraofertas debe inicializarse");
        assertTrue(oferta.getContraofertas().isEmpty());
    }

    //Verifica que agregarcontraoferta funcione correctamente si la oferta está activa
    @Test
    void agregarContraofertaAñadeCorrectamente() {
        Contraoferta c = new Contraoferta(new ClienteStub3("comprador1", "pw"), 120.0);
        oferta.agregarContraoferta(c);
        List<Contraoferta> contraofertas = oferta.getContraofertas();
        assertEquals(1, contraofertas.size());
        assertSame(c, contraofertas.get(0));
    }

    //verifica que agregarontraoferta lance excepción si la oferta está cerrada, garantiza que no se venda despues del cierre
    @Test
    void agregarContraofertaConOfertaCerrada() {
        oferta.cerrar();
        Contraoferta c = new Contraoferta(new ClienteStub3("comprador2", "pw"), 90.0);
        assertThrows(IllegalStateException.class, () -> oferta.agregarContraoferta(c));
    }

    //Verifica que cerrar cambie el estado a inactiva, si esta cerrada no sigue recibiendo contraofertas
    @Test
    void cerrarDesactivaOferta() {
        oferta.cerrar();
        assertFalse(oferta.isActiva(), "La oferta debe quedar inactiva al cerrarse");
    }
}
