package tiquetes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Usuarios.Cliente;
import eventos.*;

class EventoStubDeluxe extends Evento {
    private final String nombre;
    private final String fecha; 
    private final String hora;
    private final double precioBase;
    private final Venue venue;
    public double tasa;
    EventoStubDeluxe(String nombre, String fecha, String hora, double precioBase, double tasa, Venue venue) {
        this.nombre = nombre;
        this.fecha = fecha; 
        this.hora = hora;
        this.precioBase = precioBase; 
        this.tasa = tasa;
        this.venue = venue;
    }
    @Override public String getNombre() { 
    	return nombre; 
    	}
    @Override public String getFecha() {
    	return fecha; 
    	}
    @Override public String getHoraIni() { 
    	return hora; 
    	}
    @Override public double getPrecioBase() {
    	return precioBase; 
    	}
    @Override public Venue getVenue() {
    	return venue; 
    	}
}

class ClienteStubDeluxe extends Cliente { 
	public ClienteStubDeluxe(String l,String c) {
	super(l,c);
	}
}

public class TiqueteDeluxeTest {

	// Verifca que el costo del tiquete deluxe sume el recargo de la tasa del evento 
    @Test
    void aplicaTasaDelEventoAlCostoInicial() {
        Venue v = new Venue("Dir", 100, "Nom");
        Localidad loc = new Localidad("VIP", 0.0, v, 10);
        EventoStubDeluxe ev = new EventoStubDeluxe("Gala", "2025-08-01", "21:00", 200.0, 10.0, v); 
        ClienteStubDeluxe cl = new ClienteStubDeluxe("lux", "pw");
        TiqueteDeluxe t = new TiqueteDeluxe(200.0, loc, ev, cl, "Backstage");
        assertEquals(220.0, t.getCosto(), 1e-9);
    }

    // Verifica que en imprimir agrega los beneficios y mantiene deluxe como tipo
    @Test
    void inclcusionDeBeneficiosEnImpresion() {
        Venue v = new Venue("D", 10, "N");
        Localidad loc = new Localidad("Gold", 0.0, v, 5);
        EventoStubDeluxe ev = new EventoStubDeluxe("Gala", "2025-08-01", "21:00", 100, 5, v);
        ClienteStubDeluxe cl = new ClienteStubDeluxe("l", "p");

        TiqueteDeluxe t = new TiqueteDeluxe(100.0, loc, ev, cl, "Merch+Lounge");
        String out = t.imprimir(); 
        assertTrue(out.endsWith("Merch+Lounge"));
        assertTrue(out.contains(",Deluxe,")); 
    }

    // Verifica que se imprime N/A cuando no hay beneficios definidos
    @Test
    void imprimeNAEnBeneficiosNulos() {
        Venue v = new Venue("Dir", 10, "Nom");
        Localidad loc = new Localidad("General", 0.0, v, 0);   
        EventoStubDeluxe ev = new EventoStubDeluxe("Gala", "2025-08-01", "21:00", 0.0, 0.0, v);
        ClienteStubDeluxe cl = new ClienteStubDeluxe("u", "p");
        TiqueteDeluxe t = new TiqueteDeluxe(0.0, loc, ev, cl, "Backstage"); 
        String out = t.imprimir().trim();
        assertTrue(out.contains("N/A"));
    }

}

