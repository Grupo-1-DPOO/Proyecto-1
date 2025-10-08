package modelo;

public class Localidad {
	private final String nombre; 
	private final int capacidad;
	private final double porcentajeAumentoCosto;
	private final Venue venue;
	
	public Localidad(String nombre, int capacidad, double porcentajeAumentoCosto, Venue venue) {
	
	this.nombre = nombre;
	this.capacidad = capacidad;
	this.porcentajeAumentoCosto = porcentajeAumentoCosto;
	this.venue = venue;
	
	
		
	}
	//GETTERS

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public double getPorcentajeAumentoCosto() {
		return porcentajeAumentoCosto;
	}

	public Venue getVenue() {
		return venue;
	}

	
}
