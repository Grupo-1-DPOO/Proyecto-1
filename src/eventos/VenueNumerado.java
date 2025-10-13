package eventos;

import tiquetes.Tiquete;

public class VenueNumerado extends Venue{
	
	public Tiquete[] disponibles;

	public VenueNumerado(String dir,int cap, String nom) {
		super(dir,cap,nom);
		// TODO Auto-generated constructor stub
		
		this.disponibles= new Tiquete[cap];
	}
	
	public Tiquete[] getDisponibles() {
		
		return this.disponibles;
		
	}
	

}
