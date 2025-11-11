package tiquetes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import eventos.*;
import usuarios.Cliente;

class EventoStubNumerado extends Evento {
    private final String nombre;
    private final String fecha;
    private final String hora;
    private final double precioBase;
    private final Venue venue;
    public double tasa = 0.0;
    
    EventoStubNumerado(String n,String f,String h,double pb, Venue v) {
        this.nombre = n; 
        this.fecha = f;
        this.hora=h;
        this.precioBase = pb;
        this.venue =  v;
    }
    @Override public String getNombre(){
    	return nombre;
    	}
    @Override public String getFecha(){
    	return fecha;
    	}
    @Override public String getHoraIni(){
    	return hora;
    	}
    @Override public double getPrecioBase(){
    	return precioBase;
    	}
    @Override public Venue getVenue(){
    	return venue;
    	}
}
class ClienteStubNum extends Cliente { 
	public ClienteStubNum(String l,String c){
		super(l,c);
		}
	}

public class TiqueteNumeradoTest {

	// Verifica que se toma el primer indice libre en disponibles y se guarda el tiquete ahi
    @Test
    void asignacionPrimerPuestoLibreYMarcacionEnVenue() {
        VenueNumerado vn = new VenueNumerado("Av 10", 2, "Auditorio"); 
        Localidad loc = new Localidad("FilaA", 0.0, vn, 2);
        EventoStubNumerado ev = new EventoStubNumerado("Obra", "2026-05-05", "19:00", 30.0, vn);
        ClienteStubNum cl = new ClienteStubNum("u", "p");

        TiqueteNumerado t1 = new TiqueteNumerado(30.0, loc, ev, cl);
        TiqueteNumerado t2 = new TiqueteNumerado(30.0, loc, ev, cl);

        assertEquals(0, t1.getNumero());
        assertEquals(1, t2.getNumero());
        assertSame(t1, vn.getDisponibles()[0]);
        assertSame(t2, vn.getDisponibles()[1]);
    }

    // Verifica que si no hay espacios, no asigna y deja el numero por defecto en -1
    @Test
    void noAsignacionSiNoHayCupos() {
        VenueNumerado vn = new VenueNumerado("Av 10", 1, "Sala");
        Localidad loc = new Localidad("A", 0.0, vn, 1);
        EventoStubNumerado ev = new EventoStubNumerado("Func", "2026-01-01", "20:00", 25.0, vn);
        ClienteStubNum cl = new ClienteStubNum("u", "p");

        TiqueteNumerado t1 = new TiqueteNumerado(25.0, loc, ev, cl);
        TiqueteNumerado t2 = new TiqueteNumerado(25.0, loc, ev, cl);

        assertEquals(0, t1.getNumero());
        assertEquals(-1, t2.getNumero());
    }

    // Verifica que imprimir inluye el numero de asiento como ultimo campo
    @Test
    void inclusionDeNumeroDeAsientoEnImpresion() {
        VenueNumerado vn = new VenueNumerado("Dir", 2, "Teatro");
        Localidad loc = new Localidad("VIP", 0.0, vn, 2);
        EventoStubNumerado ev = new EventoStubNumerado("Obra", "2025-10-10", "18:00", 40.0, vn);
        ClienteStubNum cl = new ClienteStubNum("ana", "123");

        TiqueteNumerado t = new TiqueteNumerado(40.0, loc, ev, cl);
        String out = t.imprimir(); 
        assertTrue(out.endsWith("," + t.getNumero()));
    }
}

