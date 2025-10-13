package eventos;

public class Localidad {
	
	private String nombre;
	
	private double porAum;
	
	private Venue venue;
	
	private int capacidad;
	
	public Localidad(String nombre, Evento evento, double por, Venue ven, int capacidad) {
		
		this.nombre=nombre;
		
		this.porAum=por;
		
		this.venue=ven;
		
		this.setCapacidad(capacidad);
		
		
	}
	
	//GETTERS
	
	public String getNombre() {
		
		return this.nombre;}
	
	
	public double getPorcentaje() {
		
		return this.porAum;
	}
	
	public Venue getVenue() {
		
		return this.venue;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	//SETTERS
	
	public void setEvento(Evento x) {
		
		this.evento=x;
	}
	
	public void setPorcentaje(double x) {
		
		this.porAum=x;
	}
	
	public void setVenue(Venue x) {
		
		this.venue=x;
	}
	
	public void setNombre(String x) {
		
		this.nombre=x;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

}
