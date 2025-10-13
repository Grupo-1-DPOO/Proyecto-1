package eventos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Venue {
	
	String direccion;
	int capMax;
	ArrayList<Localidad> localidades;
	
	public Venue(String dir, int cap){
		this.direccion = dir;
		this.capMax = cap;
		this.localidades = new ArrayList<Localidad>();
	}
	
	//GETTERS
	
	public String getDireccion() {
		return direccion;
	}
	
	public int getCapMax() {
		return capMax;
	}
	
	public ArrayList<Localidad> getLocalidades() {
		return localidades;
	}
	
	//SETTERS
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setCapMax(int capMax) {
		this.capMax = capMax;
	}
	
	public void setLocalidades(ArrayList<Localidad> localidades) {
		this.localidades = localidades;
	}
	
	//Agenda por fecha de evento en el venue 
	
	private Map<String, Evento> agenda = new HashMap<>();
	
	public boolean disponible(String fecha) {
	    return !agenda.containsKey(fecha);
	}
	
	void programarEvento(String fecha, Evento evento) {
	    if (!disponible(fecha)) {
	        throw new IllegalStateException("El venue ya tiene un evento el " + fecha);
	    }
	    agenda.put(fecha, evento);

	}

	
}

