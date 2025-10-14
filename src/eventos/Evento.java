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
	
	
	public Evento(Organizador org, int cap, String nombre, String tipo, String fecha, String horaIni, String horaFin, double precioBase, Venue venue){
		
		this.organizador=org;
		
		this.nombre=nombre;
		
		this.tipo=tipo;
		
		this.fecha=fecha;
		
		this.horaIni=horaIni;
		
		this.horaFin=horaFin;
		
		this.precioBase=precioBase;
		
		this.localidades= new ArrayList<Localidad>();
		
		this.tiqPros=new ArrayList<String>();
		
		this.tiqRes=new ArrayList<String>();
		
		this.venue=venue;
		
		this.estatus=true;	
		
		this.localidadBasica=null;
		
		this.capMax=cap;
		
		//añade a la lista de pendientes el evento
			
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
	
	public ArrayList<String> getTiqPros() { 
		return tiqPros; }
	
	public ArrayList<String> getTiqRes() { 
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

	            System.out.println("Localidad añadida exitosamente.\n");

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


	
	
	
	//cantidad de tiquetes procesados y restantes 
	public int getTiquetesProcesados() {
	    return tiqPros.size();
	}

	public int getTiquetesRestantes() {
	    return getCapacidadTotalEvento() - tiqPros.size();
	}
	
}






























