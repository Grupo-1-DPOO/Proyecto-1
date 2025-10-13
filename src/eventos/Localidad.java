package eventos;

public class Localidad {
	
	private String nombre;
	
	private double porAum;
	
	private Venue venue;
	
	public Localidad(String nombre, Evento evento, double por, Venue ven) {
		
		this.nombre=nombre;
		
		this.porAum=por;
		
		this.venue=ven;
		
		
	}
	
	
	public String getNombre() {
		
		return this.nombre;}
	
	
	public double getPorcentaje() {
		
		return this.porAum;
	}
	
	public Venue getVenue() {
		
		return this.venue;
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

}
