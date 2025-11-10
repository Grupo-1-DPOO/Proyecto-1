package tiquetes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Usuarios.Cliente;
import eventos.*;

class EventoStubGrupal extends Evento {
    private final String nombre;
    private final String fecha;
    private final String hora;
    private final double precioBase;
    private final Venue venue;
    public double tasa = 0.0;
    EventoStubGrupal(String n,String f,String h,double pb, Venue v){
        this.nombre = n; 
        this.fecha = f; 
        this.hora = h;
        this.precioBase = pb;
        this.venue = v;
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
class ClienteStubGrupal extends Cliente { 
	public ClienteStubGrupal(String l,String c){
		super(l,c);
		}
	}

public class TiqueteGrupalTest {

	// verifica que el costo sea (base + porcentaje * base) * individuos
    @Test
    void calculaCorrectamenteCostoGrupal() {
        Venue v = new Venue("Dir", 100, "Nom");
        Localidad loc = new Localidad("General", 0.20, v, 100); 
        EventoStubGrupal ev = new EventoStubGrupal("Partido", "2025-07-07", "15:00", 50.0, v);
        ClienteStubGrupal cl = new ClienteStubGrupal("team", "pw");
        TiqueteGrupal t = new TiqueteGrupal(3, loc, ev, cl);
        double esperado = (50.0 + 0.20*50.0) * 3; 
        assertEquals(esperado, t.getCosto(), 1e-9);
    }

    // verifica que imprimir refleje la cantidad de personas y el costo total
    @Test
    void impresionIncluyeIndividuosYCostoTotal() {
        Venue v = new Venue("D", 10, "N");
        Localidad loc = new Localidad("Grupo", 0.0, v, 20);
        EventoStubGrupal ev = new EventoStubGrupal("Charla", "2025-11-11", "10:00", 40.0, v);
        ClienteStubGrupal cl = new ClienteStubGrupal("g", "p");

        TiqueteGrupal t = new TiqueteGrupal(5, loc, ev, cl);
        String out = t.imprimir();
        assertTrue(out.contains(",Grupal,"));
        assertTrue(out.contains(",5,")); 
        assertTrue(out.contains("," + t.getCosto() + ",")); 
    }
}

