package usuarios;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tiquetes.Tiquete;

// Stub para IDs de tiquetes
class TiqueteIdStub2 extends Tiquete {
    public TiqueteIdStub2(String id) { 
    	super();
    	this.setIdentificador(id); 
    	}
    @Override public String imprimir() { 
    	return getIdentificador(); 
    	}
}

public class OrganizadorTest {

	// Verifica que el constructor vacio inicialice el tipo, las listas y el saldo
    @Test
    void constructorVacioInicializaCorrectamente() {
        Organizador o = new Organizador();
        assertEquals("Organizador", o.getTipo());
        assertTrue(o.getTiqVi().isEmpty());
        assertTrue(o.getTiqNoVi().isEmpty());
        assertEquals(0.0, o.getSaldo(), 1e-9);
        assertTrue(o.getEventosProx().isEmpty());
        assertTrue(o.getEventosPas().isEmpty());
    }

    // Verifica que el contructor con argumentos inicializa  el tipo y las listas 
    @Test
    void constructorConArgsInicializaCorrectamente() {
        Organizador o = new Organizador("org", "pw");
        assertEquals("Organizador", o.getTipo());
        assertNotNull(o.getEventosProx());
        assertNotNull(o.getEventosPas());
    }

    // Verifica que imprimir incluye correctamente el formato log,pass,tipo,saldo,IDsVigentes|IDsNoVigentes|eventosProx|eventosPas
    @Test
    void imprimirIncluyeTiquetesVigentesNoVigentesYEventos() {
        Organizador o = new Organizador("org", "pw");
   
        o.getTiqVi().add(new TiqueteIdStub2("A1"));
        o.getTiqVi().add(new TiqueteIdStub2("B2"));
        o.getTiqNoVi().add(new TiqueteIdStub2("Z9"));
       
        o.getEventosProx().add("EVT-10");
        o.getEventosPas().add("EVT-01");
        String out = o.imprimir();

        assertTrue(out.startsWith("org,pw,Organizador,0.0,"));
        assertTrue(out.contains("A1;B2|Z9|EVT-10|EVT-01"));
        assertEquals(out, o.toString());
    }
}

