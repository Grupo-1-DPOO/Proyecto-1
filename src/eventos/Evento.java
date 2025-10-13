package eventos;

import java.util.ArrayList;

import Usuarios.Organizador;





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
	
	private ArrayList<String> tiqPros;
	
	private ArrayList<String> tiqRes;
	
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
		
		this.localidades=venue.getLocalidades();
		
		this.tiqPros=new ArrayList<String>();
		
		this.tiqRes=new ArrayList<String>();
		
		this.venue=venue;
		
		this.estatus=true;	
		
		this.localidadBasica=locBas;
		
		this.capMax=cap;
		
	}
	
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
	
	public ArrayList<String> getTiqPros() { 
		return tiqPros; }
	
	public ArrayList<String> getTiqRes() { 
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
	
	public void setTiqPros(ArrayList<String> tiqPros) { 
		this.tiqPros = tiqPros; }
	
	
	public void setTiqRes(ArrayList<String> tiqRes) { 
		this.tiqRes = tiqRes; }
	
	public void setVenue(Venue venue) { 
		this.venue = venue; }
	
	public void setEstatus(Boolean estatus) { 
		this.estatus = estatus; }	
	
	public String imprimir() {
	    return this.getNombre() + "," +
	            this.getOrganizador().getLog() + "," +
	            this.getTipo() + "," +
	            this.getFecha() + "," +
	            this.getHoraIni() + "," +
	            this.getHoraFin() + "," +
	            this.getVenue().getNombre() + "," +
	            this.getCapMax() + "," +
	            this.getPrecioBase() + "," +
	            (this.getEstatus() ? "Activo" : "Finalizado") + "," +
	            (this.localidadBasica != null ? this.localidadBasica.getNombre() : "N/A") + "," +
	            this.getTiqRes()+ "," +
	            this.getTiqPros();
	 }
	
	
}

