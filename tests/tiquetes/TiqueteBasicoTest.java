package tiquetes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eventos.*;
import usuarios.Cliente;

class EventoStubBasico extends Evento {
    private final String nombre;
    private final String fecha;
    private final String hora;
    private final double precioBase;
    private final Venue venue;
    public double tasa = 0.0; 

    EventoStubBasico(String nombre, String fecha, String hora, double precioBase, Venue venue) {
        this.nombre = nombre; this.fecha = fecha; this.hora = hora;
        this.precioBase = precioBase; this.venue = venue;
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

class ClienteStubBasico extends Cliente {
    public ClienteStubBasico(String log, String cont) { 
    	super(log, cont); 
    	}
}

public class TiqueteBasicoTest {

	// Verifica que el calculo del costo sea el costo base mas el porcentaje de la localidad
    @Test
    void costoEsBaseMasPorcentajeDeLocalidad() {
        Venue v = new Venue("Dir", 100, "Nom");
        Localidad loc = new Localidad("VIP", 20.0, v, 50); 
        EventoStubBasico ev = new EventoStubBasico("Show", "2026-01-01", "20:00", 100.0, v);
        ClienteStubBasico cl = new ClienteStubBasico("jose", "123");
        TiqueteBasico t = new TiqueteBasico(loc, ev, cl);
        assertEquals(120.0, t.getCosto(), 1e-9);
    }

   // Comprueba que cuando hay campos nulos  se imprime N/A
    @Test
    void imprimirCorrectamenteCuandoFaltenReferencias() {
        Venue v = new Venue("Dir", 100, "Nom");
        Localidad loc = new Localidad("VIP", 20.0, v, 50);
        EventoStubBasico ev = new EventoStubBasico("Show", "2026-01-01", "20:00", 100.0, v);
        ClienteStubBasico cl = new ClienteStubBasico("jose", "123");

        TiqueteBasico t = new TiqueteBasico(loc, ev, cl);
        t.setEvento(null);
        t.setCliente(null);
        t.setLocalidad(null);

        String out = t.imprimir();
        assertTrue(out.contains("N/A"));
    }

}

