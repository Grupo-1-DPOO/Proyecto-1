package eventos;

import java.util.ArrayList;

public class Venue {
	
	String direccion;
	int capMax;
	ArrayList<Localidad> localidades;
	
	public Venue(String dir, int cap){
		this.direccion = dir;
		this.capMax = cap;
		this.localidades = new ArrayList<Localidad>();
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public int getCapMax() {
		return capMax;
	}
	
	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setCapMax(int capMax) {
		this.capMax = capMax;
	}
	
	public void setLocalidades(ArrayList<Localidad> localidades) {
		this.localidades = localidades;
	}
}

