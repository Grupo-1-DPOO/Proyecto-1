package eventos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VenueTest {
	
	// Revisa que se permita un evento por fecha y rechace duplicados
	@Test
	void eventoPorFechaYRechazarDuplicados() {
		Venue v = new Venue("Cra 1", 100, "Coliseo");
		Evento e = new Evento();
		v.programarEvento("2025-12-01", e);
		assertThrows(IllegalStateException.class, () -> v.programarEvento("2025-12-01", new Evento()));
		
	}
	
	// Revisa que no se permita crear localidades con capacidad menor o igual a cero
	@Test
	void noPermiLocalidadConCapacidadNoPositiva() {
		Venue v = new Venue("Cra 1", 100, "Coliseo");
		assertThrows(IllegalArgumentException.class, () -> v.agregarLocalidad("VIP", 0.2, 0));
		
	}
	
	// Revisa que no se permita que la suma de localidades supere la capacidad maxima del venue
	@Test
	void noPermitirLocalidadesSuperenCapacidadDelVenue() {
		Venue v = new Venue("Cra 1", 100, "Coliseo");
		v.agregarLocalidad("A", 0.0, 80);
		assertThrows(IllegalArgumentException.class, () -> v.agregarLocalidad("B", 0.0, 30));
	}

	// Verifica que se permita la suma exacta a la capacidad maxima del venue
    @Test
    void permitirSumaExactaAlaCapacidadMaxima() {
        Venue v = new Venue("Cra 1", 100, "Coliseo");
        v.agregarLocalidad("A", 0.0, 60);
        v.agregarLocalidad("B", 0.0, 40);
        assertEquals(2, v.getLocalidades().size());
    }

}


