package eventos;

public class Localidad {
	
	private String nombre;
	
	private Evento evento;
	
	private double porAum;
	
	private Venue venue;
	
	public Localidad(String nombre, Evento evento, double por, Venue ven) {
		
		this.nombre=nombre;
		
		this.evento=evento;
		
		this.porAum=por;
		
		this.venue=ven;
		
		
	}
	
	
	public Evento getEvento() {
		
		return this.evento;
	}
	
	public String getNombre() {
		
		return this.nombre;}
	
	
	public double getPorcentaje() {
		
		return this.porAum;
	}
	
	public Venue getVenue() {
		
		return this.venue;
	}
	
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

}
