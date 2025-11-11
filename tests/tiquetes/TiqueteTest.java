package tiquetes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eventos.Localidad;
import eventos.Venue;
 
// Stubs para instanciar tiquete
class EventoStub extends eventos.Evento {
	private final String fecha;
	private final String hora;
	
	public EventoStub(String fecha, String hora) {
		this.fecha = fecha;
		this.hora = hora;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getHoraIni() {
		return hora;
	}
}

class ClienteStub extends usuarios.Cliente {
	public ClienteStub(String log, String cont) {
		super(log, cont);
	}
}	
class TiqueteStub extends Tiquete {
	public TiqueteStub(String tipo, int ind, double costo, Localidad loc, eventos.Evento ev, usuarios.Cliente cl) {
		super(tipo, ind, costo, loc, ev, cl);
	}
	@Override public String imprimir() {
		return "OK";
	}	
}


public class TiqueteTest {
	
	// Verifica que se calcule el costo con el porcentaje de la localidad
	@Test
	void calculoCostoConPorcentajeDeLocalidad() {
		Venue v = new Venue("Dir", 100, "Nom");
		Localidad loc = new Localidad("VIP", 20.0, v, 50);
		EventoStub ev = new EventoStub("2025-12-01", "20:00");
		ClienteStub cl = new ClienteStub("jose", "123");
		
		Tiquete t = new TiqueteStub("Basico", 1, 100.0, loc, ev, cl);
		assertEquals(120.0, t.getCosto(), 1e-9);
	}
	
	//Verifica que se copie la fecha y hora desde el evento pasado
	@Test
	void deberiaCopiarFechaYHoraDelEvento() {
        Venue v = new Venue("Dir", 100, "Nom");
        Localidad loc = new Localidad("General", 0.0, v, 100);
        EventoStub ev = new EventoStub("2026-01-01", "19:30");
        ClienteStub cl = new ClienteStub("ana", "xyz");

        Tiquete t = new TiqueteStub("Basico", 1, 50.0, loc, ev, cl);
        assertEquals("2026-01-01", t.getFecha());
        assertEquals("19:30", t.getHora());

	}
	
	//Verifica que el identificador inicie con login y termine en un numero
	@Test 
	void identificadorIniciaYFinalizaCorrectamente() {
		Venue v = new Venue("Dir", 100, "Nom");
		Localidad loc = new Localidad("Platea", 0.0, v, 100);
		EventoStub ev = new EventoStub("2025-05-05", "18:00");
		ClienteStub cl = new ClienteStub("loginUser", "pwd");
		
		Tiquete t =  new TiqueteStub("Basico", 1, 10.0, loc, ev, cl);
		String id = t.getIdentificador();
		assertTrue(id.startsWith("loginUser"));
		assertTrue(id.substring("loginUser".length()).matches("\\d{1,4}"), "Sufijo numerico [0..1000]");
		
	}
	// Verifica la delegacion correcta de toString en imprimir
	@Test
	void toStringDelegadoAImprimir() {
		Venue v = new Venue("Dir", 100, "Nom");
		Localidad loc = new Localidad("A", 0.0, v, 10);
		ClienteStub cl = new ClienteStub("u", "p");
		EventoStub ev = new EventoStub("2025-05-05", "18:00");
		Tiquete t = new TiqueteStub("Basico", 1, 10.0, loc, ev, cl);
		assertEquals("OK", t.toString());
	}
}


