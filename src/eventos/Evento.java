package eventos;

import java.util.ArrayList;

import Usuarios.Organizador;
import tiquetes.Tiquete;





public class Evento {
	
	private Organizador organizador;
	
	private int capMax;
	
	private String tipo;
	
	private String fecha;
	
	private String horaIni;
	
	private String horaFin;
	
	private double precioBase;
	
	private ArrayList<Localidad> localidades;
	
	private ArrayList<Tiquete> tiqPros;
	
	private ArrayList<Tiquete> tiqRes;
	
	private Venue venue;
	
	private Boolean estatus; //Si es true es un evento activo o próximo, si es False es un evento pasado
	
	Evento(Organizador org, String tipo, String fecha, String horaIni, String horaFin, double precioBase, Venue venue, int cap){
		
		this.organizador=org;
		
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
	}
	
	public Organizador getOrganizador() { 
		return organizador; }
	
	public int getCapMax() { 
		return capMax; }
	
	public String getTipo() { 
		return tipo; }
	
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

	public void setOrganizador(Organizador organizador) { 
		this.organizador = organizador; }
	
	public void setCapMax(int capMax) { 
		this.capMax = capMax; }
	
	public void setTipo(String tipo) { 
		this.tipo = tipo; }
	
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
	
	
}
