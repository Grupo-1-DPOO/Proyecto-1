package eventos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class LocalidadTest {

	// Verifica que el toString se realice correctamente, incluyendo venue
	@Test
	void formatearStringIncluyendoVenue() {
		Venue v = new Venue("Cra 1 #2-3", 1000, "Coliseo");
		Localidad l = new Localidad();
		l.setNombre("Platino");
		l.setPorcentaje(0.25);
		l.setCapacidad(200);
		l.setVenue(v);
		
		String s = l.toString();
		assertTrue(s.contains("Platino"), "Debe incluir el nombre de la localidad");
		assertTrue(s.contains("0.25"), "Debe incluir el porcentaje");
		assertTrue(s.contains("200"), "Debe incluir la capacidad de la localidad");
		assertTrue(s.contains("Coliseo"), "Debe incluir el nombre del venue");
		
	}
	
	// Verifica que se imprime N/A cuando no hay venue 
	@Test
	void imprimirSinVenue() {
		Localidad l = new Localidad();
		l.setNombre("General");
		l.setPorcentaje(0.0);
		l.setCapacidad(500);
		l.setVenue(null);
		
		String s = l.toString();
		assertTrue(s.contains("N/A"), "Si no hay venue debe imprimir N/A");
	}

}
