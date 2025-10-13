package eventos;

import java.util.ArrayList;

public class Venue {
	
	String direccion;
	String nombre;
	int capMax;
	ArrayList<Localidad> localidades;
	
	public Venue(String dir, int cap,String nom){
		this.nombre=nom;
		this.direccion = dir;
		this.capMax = cap;
		this.localidades = new ArrayList<Localidad>();
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getNombre() {
		return nombre;
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
	
	public void setNombre(String nom) {
		this.nombre = nom;
	}
	
	public void setCapMax(int capMax) {
		this.capMax = capMax;
	}
	
	public void setLocalidades(ArrayList<Localidad> localidades) {
		this.localidades = localidades;
	}
}

