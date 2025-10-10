package eventos;

import tiquetes.Tiquete;

public class VenueNumerado extends Venue{
	
	public Tiquete[] disponibles;

	public VenueNumerado(String dir,int cap) {
		super(dir,cap);
		// TODO Auto-generated constructor stub
		
		this.disponibles= new Tiquete[cap];
	}
	
	public Tiquete[] getDisponibles() {
		
		return this.disponibles;
		
	}
	

}
