package eventos;

import java.util.ArrayList;

public class Venue {
	
	String direccion;
	
	int capMax;
	
	ArrayList<Localidad> localidades;
	
	
	public Venue(String dir, int cap){
		
		this.direccion=dir;
		
		this.capMax=cap;
		
		this.localidades= new ArrayList<Localidad>();
		
	}

}
