package eventos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VenueNumeradoTest {

	// Verifica que se cree un arreglo y su longitud coincida con la capacidad maxima
	@Test
	void disponiblesConCapacidadMaxima() {
		VenueNumerado vn = new VenueNumerado("Cra 10", 5, "Auditorio");
		assertNotNull(vn.getDisponibles());
		assertEquals(5, vn.getDisponibles().length);
	}
	// Verifica que se muestren los datos basicos en el toString 
	@Test
	void toStringMuestraDatosBasicos() {
		VenueNumerado vn = new VenueNumerado("Cra 10", 3, "Auditorio");
		String s = vn.toString();
		assertTrue(s.contains("Auditorio"));
		assertTrue(s.contains("Cra 10"));
		assertTrue(s.contains("capMax=3"));
	}

}
