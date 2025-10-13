package eventos;

import java.util.ArrayList;

import Usuarios.Organizador;
import tiquetes.Tiquete;


public class Evento {
	
	private Organizador organizador;
	
	private int capMax;
	
	private String tipo;
	
	private String nombre;
	
	private String fecha;
	
	private String horaIni;
	
	private String horaFin;
	
	private double precioBase;
	
	private ArrayList<Localidad> localidades;
	
	private ArrayList<Tiquete> tiqPros;
	
	private ArrayList<Tiquete> tiqRes;
	
	private Venue venue;
	
	private Boolean estatus; //Si es true es un evento activo o próximo, si es False es un evento pasado
	
	public Localidad localidadBasica;
	
	
	public Evento(Organizador org, String nombre, String tipo, String fecha, String horaIni, String horaFin, double precioBase, Venue venue, int cap, Localidad locBas){
		
		this.organizador=org;
		
		this.nombre=nombre;
		
		this.tipo=tipo;
		
		this.fecha=fecha;
		
		this.horaIni=horaIni;
		
		this.horaFin=horaFin;
		
		this.precioBase=precioBase;
		
		this.localidades=new ArrayList<Localidad>();
		
		this.tiqPros=new ArrayList<Tiquete>();
		
		this.tiqRes=new ArrayList<Tiquete>();
		
		this.venue=venue;
		
		this.estatus=true;	
		
		this.localidadBasica=locBas;
		
		this.capMax=cap;
		
		//añade a la lista de pendientes el evento
		consola.Aplicacion.pendientes.add(this);
			
	}
	
	//GETTERS
	
	public Organizador getOrganizador() { 
		return organizador; }
	
	public int getCapMax() { 
		return capMax; }
	
	public String getTipo() { 
		return tipo; }
	
	public String getNombre() { 
		return nombre; }
	
	public String getFecha() { 
		return fecha; }
	
	public String getHoraIni() { 
		return horaIni; }
	
	public String getHoraFin() { 
		return horaFin; }
	
	public double getPrecioBase() { 
		return precioBase; }
	
	public ArrayList<Localidad> getLocalidades() { 
		return localidades; }
	
	public ArrayList<Tiquete> getTiqPros() { 
		return tiqPros; }
	
	public ArrayList<Tiquete> getTiqRes() { 
		return tiqRes; }
	
	public Venue getVenue() { 
		return venue; }
	
	public Boolean getEstatus() { 
		return estatus; }
	
	//SETTERS

	public void setOrganizador(Organizador organizador) { 
		this.organizador = organizador; }
	
	public void setCapMax(int capMax) { 
		this.capMax = capMax; }
	
	public void setTipo(String tipo) { 
		this.tipo = tipo; }
	
	public void setNombre(String nombre) { 
		this.nombre = nombre; }
	
	public void setFecha(String fecha) { 
		this.fecha = fecha; }
	
	public void setHoraIni(String horaIni) { 
		this.horaIni = horaIni; }
	
	public void setHoraFin(String horaFin) { 
		this.horaFin = horaFin; }
	
	public void setPrecioBase(double precioBase) { 
		this.precioBase = precioBase; }
	
	public void setLocalidades(ArrayList<Localidad> localidades) { 
		this.localidades = localidades; }
	
	public void setTiqPros(ArrayList<Tiquete> tiqPros) { 
		this.tiqPros = tiqPros; }
	
	
	public void setTiqRes(ArrayList<Tiquete> tiqRes) { 
		this.tiqRes = tiqRes; }
	
	public void setVenue(Venue venue) { 
		this.venue = venue; }
	
	public void setEstatus(Boolean estatus) { 
		this.estatus = estatus; }	
	
	///////////////////////
	
	public int getCapacidadUsadaEnLocalidades() {
	    int suma = 0;
	    for (Localidad l : localidades) suma += l.getCapacidad();
	    return suma;
	}
	
	public int getCapacidadTotalEvento() {
	    int tope = Math.min(capMax, venue.getCapMax());
	    return tope - getCapacidadUsadaEnLocalidades();
	}
	
	public void agregarLocalidad(String nombre, double porcentajeAumento, int capacidad) {
	    if (capacidad <= 0) {
	        throw new IllegalArgumentException("La capacidad de la localidad debe ser mayor a 0");
	    }

	    int sumaActual = 0;
	    for (Localidad l : localidades) {
	        sumaActual += l.getCapacidad();
	    }

	    if (sumaActual + capacidad > capMax || sumaActual + capacidad > venue.getCapMax()) {
	        throw new IllegalArgumentException("La suma de capacidades en las localidades excede la capacidad del evento y del venue");
	    }

	    Localidad nueva = new Localidad(nombre, this, porcentajeAumento, venue, capacidad);
	    localidades.add(nueva);
	}
	
	
	
	//cantidad de tiquetes procesados y restantes 
	public int getTiquetesProcesados() {
	    return tiqPros.size();
	}

	public int getTiquetesRestantes() {
	    return getCapacidadTotalEvento() - tiqPros.size();
	}
	
}






























