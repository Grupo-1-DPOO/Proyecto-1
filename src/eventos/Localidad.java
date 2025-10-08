package eventos;

public class Localidad {
	
	private Evento evento;
	
	private double porAum;
	
	private Venue venue;
	
	
	public Evento getEvento() {
		
		return this.evento;
	}
	
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

}
