package eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import tiquetes.Tiquete;
import usuarios.Organizador;

// Stub para contar procesados
class TiqueteStub extends Tiquete {
    public TiqueteStub(String id) { 
    	super();
    	this.setIdentificador(id); 
    	}
    @Override public String imprimir() { 
    	return getIdentificador(); 
    	}
}

public class EventoTest {

    private Evento nuevoEventoConBase(Venue v, int capMax) {
        Organizador org = new Organizador("org", "pw");
        return new Evento(org, capMax, "Concierto", "MUSICAL", "2025-12-01", "20:00", "22:00", 100.0, v, "benef");
    }

    // Verifica que la distribucion interna se contabilice correctamente, mediante el recorrido de localidades y la suma de sus capacidades
    @Test
    void getCapacidadUsadaEnLocalidadesSumaCapacidades() {
        Venue v = new Venue("Dir", 100, "Sala");
        Evento e = nuevoEventoConBase(v, 90);

        ArrayList<Localidad> locs = new ArrayList<>();
        locs.add(new Localidad("General", 0.0, v, 30));
        locs.add(new Localidad("VIP", 0.2, v, 20));
        e.setLocalidades(locs);

        assertEquals(50, e.getCapacidadUsadaEnLocalidades());
    }

    // Verifica que se cumpla la restriccion de que la cantidad del evento no supere la capacidad del venue
    @Test
    void totalEventoNoSuperaCapacidadVenue() {
        Venue v = new Venue("Dir", 80, "Teatro");
        Evento e = nuevoEventoConBase(v, 90);
        ArrayList<Localidad> locs = new ArrayList<>();
        locs.add(new Localidad("A", 0.0, v, 30));
        e.setLocalidades(locs);

        assertEquals(50, e.getCapacidadTotalEvento());
    }

    // Verifica que los tiquetes procesados y restantes se calculan con base en la capacidad total
    @Test
    void tiquetesProcesadosYRestantesSeCalculanCorrectamente() {
        Venue v = new Venue("Dir", 10, "Coliseo");
        Evento e = nuevoEventoConBase(v, 5); 
        e.getTiqPros().add(new TiqueteStub("X1"));
        e.getTiqPros().add(new TiqueteStub("X2"));

        assertEquals(2, e.getTiquetesProcesados());
        assertEquals(3, e.getTiquetesRestantes()); 
    }

    // Verifica que el toString incluya nombre, tipo, fechas, precio, venue y organizador
    @Test
    void toStringIncluyeDatosClave() {
        Venue v = new Venue("Calle 1", 100, "Auditorio");
        Evento e = nuevoEventoConBase(v, 80);
        String s = e.toString();

        assertTrue(s.contains("nombre='Concierto'"));
        assertTrue(s.contains("tipo='MUSICAL'"));
        assertTrue(s.contains("fecha='2025-12-01'"));
        assertTrue(s.contains("horaIni='20:00'"));
        assertTrue(s.contains("horaFin='22:00'"));
        assertTrue(s.contains("precioBase=100.0"));
        assertTrue(s.contains("venue=Auditorio"));
        assertTrue(s.contains("organizador=org"));
    }
}

