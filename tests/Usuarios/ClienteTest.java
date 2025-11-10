package Usuarios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tiquetes.Tiquete;

class TiqueteIdStub extends Tiquete {
	private final String id;
	public TiqueteIdStub(String id) {
		super();
		this.id = id;
		this.setIdentificador(id);
	}
	@Override
	public String imprimir() {
		return id;
	}
}

public class ClienteTest {
	
	// Verifica que el constructor inicializa tipo de listas y saldo por defecto
	@Test
	void constructorInicializaCorrectamente() {
		Cliente c = new Cliente();
		assertEquals("Cliente", c.getTipo());
		assertEquals(0.0, c.getSaldo(), 1e-9);
		assertTrue(c.getTiqVi().isEmpty());
		assertTrue(c.getTiqNoVi().isEmpty());
		
	}
	
	// Verifica que imprimir formatea correctamente saldo y lista de ids
	@Test
	void imprimirFormateaCredencialesCorrectamente() {
		Cliente c = new Cliente("jose", "1234");
		c.getTiqVi().add(new TiqueteIdStub("A1"));
		c.getTiqVi().add(new TiqueteIdStub("B2"));
		c.getTiqNoVi().add(new TiqueteIdStub("Z1"));
		
		String esperado = "jose,1234,Cliente,0.0,A1;B2|Z1";
		assertEquals(esperado, c.imprimir());
		assertEquals(esperado, c.toString());
	}

}
