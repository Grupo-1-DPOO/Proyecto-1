package eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Venue {
	
	String direccion;
	String nombre;
	int capMax;
	ArrayList<Localidad> localidades;
	private Map<String, Evento> agenda = new HashMap<>();
	
	public Venue(String dir, int cap,String nom){
		this.nombre=nom;
		this.direccion = dir;
		this.capMax = cap;
		this.localidades = new ArrayList<Localidad>();
	}
	
	//GETTERS
	public String getDireccion() { 
		return direccion; }
	
	public String getNombre() { 
		return nombre; }
	
	public int getCapMax() { 
		return capMax; }
	
	public ArrayList<Localidad> getLocalidades() { 
		return localidades; }
	
	

	public void setDireccion(String direccion) { 
		this.direccion = direccion; }
	
	public void setNombre(String nom) { 
		this.nombre = nom; }
	
	public void setCapMax(int capMax) { 
		this.capMax = capMax; }
	
	public void setLocalidades(ArrayList<Localidad> localidades) { 
		this.localidades = localidades; }
	

	//Agenda por fecha de evento en el venue 
	public boolean disponible(String fecha) {
	    return !agenda.containsKey(fecha);
	}
	
	public void programarEvento(String fecha, Evento evento) {
	    if (!disponible(fecha)) {
	        throw new IllegalStateException("El venue ya tiene un evento el " + fecha);
	    }
	    agenda.put(fecha, evento);
	}
	
	public void agregarLocalidad(String nombre, double porcentajeAumento, int capacidad) {
	    if (capacidad <= 0) {
	        throw new IllegalArgumentException("La capacidad de la localidad debe ser mayor a 0");
	    }

	    int sumaActual = 0;
	    for (Localidad l : localidades) {
	        sumaActual += l.getCapacidad();
	    }

	    if (sumaActual + capacidad > this.getCapMax()) {
	        throw new IllegalArgumentException("La suma de capacidades en las localidades excede la capacidad del venue");
	    }

	    Localidad nueva = new Localidad(nombre, porcentajeAumento, this, capacidad);
	    this.localidades.add(nueva);
	}

	public String imprimir() {
		String locs = "";
		for (int i = 0; i < this.localidades.size(); i++) {
			locs += this.localidades.get(i).getNombre();
			if (i < this.localidades.size() - 1) {
				locs += ";";
			}
		}
		
		return this.getDireccion() + ","
				+ this.getNombre() + ","
				+ this.getCapMax() + ","
				+ locs + ","
				+ "\n";
	}
	
	@Override
	public String toString() {
		return imprimir();
	}
}
