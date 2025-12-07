package eventos;

import java.util.ArrayList;

import tiquetes.Tiquete;
import usuarios.Organizador;

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
	transient private ArrayList<Tiquete> tiqPros;
	transient private ArrayList<Tiquete> tiqRes;
	private Venue venue;
	private Boolean estatus; //Si es true es un evento activo o próximo, si es False es un evento pasado
	public Localidad localidadBasica;
	public double tasa;
	public String beneficios;
	
	public Evento() {
        this.localidades = new ArrayList<>();
        this.tiqPros = new ArrayList<>();
        this.tiqRes = new ArrayList<>();}
	
	public Evento(Organizador org, int cap, String nombre, String tipo, String fecha, String horaIni, String horaFin, double precioBase, Venue venue, String beneficios){
		this.organizador=org;
		this.nombre=nombre;
		this.tipo=tipo;
		this.fecha=fecha;
		this.horaIni=horaIni;
		this.horaFin=horaFin;
		this.precioBase=precioBase;
		this.localidades= new ArrayList<Localidad>();
		this.tiqPros=new ArrayList<Tiquete>();
		this.tiqRes=new ArrayList<Tiquete>();
		this.venue=venue;
		this.estatus=true;	
		this.localidadBasica=null;
		this.capMax=cap;
		this.tasa=0;
		this.beneficios=beneficios;
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


	public int getCapacidadUsadaEnLocalidades() {
	    int suma = 0;
	    for (Localidad l : localidades) suma += l.getCapacidad();
	    return suma;
	}
	
	public int getCapacidadTotalEvento() {
	    int tope = Math.min(capMax, venue.getCapMax());
	    return tope - getCapacidadUsadaEnLocalidades();
	}
	
	public void agregarLocalidades() {
	    System.out.println("Estas son las localidades disponibles en el venue:");
	    int i = 0;
	    for (Localidad o : this.venue.getLocalidades()) {
	        System.out.println(".............................................");
	        System.out.println(i + ".");
	        System.out.println("Nombre: " + o.getNombre());
	        System.out.println("Capacidad máxima: " + o.getCapacidad());
	        System.out.println("Porcentaje de aumento de precio: " + o.getPorcentaje());
	        System.out.println(".............................................");
	        i++;
	    }

	    int op = -1;
	    while (op != 0) {
	        System.out.println("1. Añadir");
	        System.out.println("0. Finalizar");
	        String decision = System.console().readLine();
	        op = Integer.parseInt(decision);

	        switch (op) {
	        case 1:
	            System.out.println("Selecciona una localidad (con el número dado en la lista)");
	            
	            System.out.println("Recuerda que la primera que añadas debe ser la localidad base (porcentaje de aumento = 0)");
	            
	            String ind = System.console().readLine();
	            int indicador = Integer.parseInt(ind);
	            
	            Localidad loc = this.venue.getLocalidades().get(indicador);

	            boolean yaExiste = false;
	            for (Localidad l : localidades) {
	            	
	                if (l.getNombre().equalsIgnoreCase(loc.getNombre())) {
	                	
	                    yaExiste = true;
	                    break;
	                }
	            }
	            if (yaExiste) {
	                System.out.println("Esta localidad ya fue añadida.");
	                break;
	            }

	            System.out.println("Indica la capacidad máxima de boletos disponibles para esta localidad:");
	            
	            String cappa = System.console().readLine();
	            
	            int capacidad = Integer.parseInt(cappa);

	            if (capacidad <= 0) {
	                throw new IllegalArgumentException("La capacidad de la localidad debe ser mayor a 0");
	            }

	            int sumaActual = 0;
	            for (Localidad l : localidades) {
	                sumaActual += l.getCapacidad();
	            }

	            if (sumaActual + capacidad > capMax || sumaActual + capacidad > venue.getCapMax()) {
	                throw new IllegalArgumentException("La suma de capacidades en las localidades excede la capacidad del evento o del venue");
	            }

	            if (localidades.isEmpty() && loc.getPorcentaje() != 0) {
	                System.out.println("La primera localidad añadida debe ser la base (porcentaje de aumento = 0).");
	                break;
	            }

	            Localidad nueva = new Localidad(loc.getNombre(), loc.getPorcentaje(), loc.getVenue(), capacidad);
	            this.localidades.add(nueva);
	            System.out.println("Localidad añadida exitosamente.");
	            break;

	        case 0:
	            System.out.println("Finalizando registro de localidades...");
	            System.out.println("Localidades actuales:");
	            for (Localidad o : this.getLocalidades()) {
	                System.out.println(".............................................");
	                System.out.println("Nombre: " + o.getNombre());
	                System.out.println("Capacidad máxima: " + o.getCapacidad());
	                System.out.println("Porcentaje de aumento: " + o.getPorcentaje());
	                System.out.println(".............................................");
	            }
	            this.localidadBasica=this.getLocalidades().get(0);
	            op = 0;
	            break;
	        }
	    }
	}

 
	public int getTiquetesProcesados() { return tiqPros.size(); }
	public int getTiquetesRestantes() { return getCapacidadTotalEvento() - tiqPros.size(); }
	


	@Override
	public String toString() {
	    return "Evento{" +
	            "nombre='" + nombre + '\'' +
	            ", tipo='" + tipo + '\'' +
	            ", fecha='" + fecha + '\'' +
	            ", horaIni='" + horaIni + '\'' +
	            ", horaFin='" + horaFin + '\'' +
	            ", precioBase=" + precioBase +
	            ", venue=" + (venue != null ? venue.getNombre() : "null") +
	            ", organizador=" + (organizador != null ? organizador.getLog() : "null") +
	            '}';
	}
}
	

