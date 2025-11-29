package consola;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import reventa.Marketplace;
import eventos.Evento;
import eventos.Venue;
import tiquetes.Tiquete;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.Organizador;
import permanencia.JsonManager;

public class Aplicacion {
	
	public ArrayList<Cliente> clientes;
	public ArrayList<Organizador> organizadores;
	public ArrayList<Administrador> staff; 
	public ArrayList<Evento> cancelados; 
	public ArrayList<Evento> eventosProx; 
	public ArrayList<Evento> eventosPas; 
	public ArrayList<Venue> venues; 
	public ArrayList<Tiquete> activos; 
	public ArrayList<Evento> pendientes;
	public Marketplace marketplace;
	
	public Aplicacion() {
		this.clientes = new ArrayList<>();
		this.staff = new ArrayList<>();
		this.eventosProx = new ArrayList<>();
		this.pendientes = new ArrayList<>();
		this.eventosPas = new ArrayList<>();
		this.venues = new ArrayList<>();
		this.activos = new ArrayList<>();
		this.organizadores = new ArrayList<>();
		this.cancelados = new ArrayList<>();
		this.marketplace= new Marketplace();


		crearArchivoSiNoExiste("clientes.json");
        crearArchivoSiNoExiste("organizadores.json");
        crearArchivoSiNoExiste("staff.json");
        crearArchivoSiNoExiste("eventosProx.json");
        crearArchivoSiNoExiste("eventosPas.json");
        crearArchivoSiNoExiste("venues.json");
        crearArchivoSiNoExiste("activos.json");
        crearArchivoSiNoExiste("pendientes.json");
        crearArchivoSiNoExiste("cancelados.json");
    }

    private void crearArchivoSiNoExiste(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                System.out.println("Archivo JSON creado: " + nombreArchivo);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo " + nombreArchivo);
        }
    }
    
    
    public ArrayList<Cliente> getClientes(){
    	
    	return this.clientes;
    	
    }
    
    public ArrayList<Administrador> getStaff(){
    	
    	return this.staff;
    	
    }
    
    public ArrayList<Organizador> getOrgs(){
    	
    	return this.organizadores;
    	
    }

    public void guardarTodo() {
        JsonManager.guardarLista("clientes.json", clientes);
        JsonManager.guardarLista("organizadores.json", organizadores);
        JsonManager.guardarLista("staff.json", staff);
        JsonManager.guardarLista("eventosProx.json", eventosProx);
        JsonManager.guardarLista("eventosPas.json", eventosPas);
        JsonManager.guardarLista("venues.json", venues);
        JsonManager.guardarLista("activos.json", activos);
        JsonManager.guardarLista("pendientes.json", pendientes);
        JsonManager.guardarLista("cancelados.json", cancelados);
    }

    public void cargarTodo() {
        clientes = cargarListaSegura("clientes.json", Cliente.class);
        organizadores = cargarListaSegura("organizadores.json", Organizador.class);
        staff = cargarListaSegura("staff.json", Administrador.class);
        eventosProx = cargarListaSegura("eventosProx.json", Evento.class);
        eventosPas = cargarListaSegura("eventosPas.json", Evento.class);
        venues = cargarListaSegura("venues.json", Venue.class);
        activos = cargarListaSegura("activos.json", Tiquete.class);
        pendientes = cargarListaSegura("pendientes.json", Evento.class);
        cancelados = cargarListaSegura("cancelados.json", Evento.class);
    }

    public <T> ArrayList<T> cargarListaSegura(String archivo, Class<T> clase) {
        try {
            List<T> lista = JsonManager.cargarLista(archivo, clase);
            if (lista != null) {
                return new ArrayList<>(lista);
            }
        } catch (Exception e) {
            System.out.println("Error cargando " + archivo + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }


    

    public void guardarAdmin(String archivo, Administrador admin) {
        List<Administrador> admins = JsonManager.cargarLista(archivo, Administrador.class);
        if (admins == null) {
            admins = new ArrayList<>();
        }
        admins.add(admin);
        JsonManager.guardarLista(archivo, admins);
    }

    public void guardarOrg(String archivo, Organizador org) {
        List<Organizador> organizadores = JsonManager.cargarLista(archivo, Organizador.class);
        if (organizadores == null) {
            organizadores = new ArrayList<>();
        }
        organizadores.add(org);
        JsonManager.guardarLista(archivo, organizadores);
    }

    public void guardarCliente(String archivo, Cliente cli) {
        List<Cliente> clientes = JsonManager.cargarLista(archivo, Cliente.class);
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        clientes.add(cli);
        JsonManager.guardarLista(archivo, clientes);
    }

    public void guardarEvento(String archivo, Evento ev) {
        List<Evento> eventos = JsonManager.cargarLista(archivo, Evento.class);
        if (eventos == null) {
            eventos = new ArrayList<>();
        }
        eventos.add(ev);
        JsonManager.guardarLista(archivo, eventos);
    }

    public void guardarVenue(String archivo, Venue venue) {
        List<Venue> venues = JsonManager.cargarLista(archivo, Venue.class);
        if (venues == null) {
            venues = new ArrayList<>();
        }
        venues.add(venue);
        JsonManager.guardarLista(archivo, venues);
    }


	


	
    public void registro() {
        
        System.out.println("¿Eres staff?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.println("0. Volver");
        System.out.println("");
        
        String in = System.console().readLine();
        int opcion = Integer.parseInt(in);
        
        while(opcion != 0) {
            switch(opcion) {
                case 1:
                    System.out.println("Ingresa la contraseña maestra");
                    String mast = System.console().readLine();
                    
                    if (mast.equals("LlaveMaestra")) {
                        System.out.println("Bienvenido al equipo de Boletmaster");
                        System.out.println("..................................................");
                        System.out.println("Crea un nombre de usuario: ");
                        String log = System.console().readLine();
                        System.out.println("Crea una contraseña: ");
                        String pas = System.console().readLine();
                        
                        Administrador nuevo = new Administrador(log,pas);
                        this.staff.add(nuevo);
                        this.guardarAdmin("staff.json", nuevo);
                        System.out.println("Creado con éxito");
                    } else {
                        System.out.println("Llave no válida, intentar de nuevo.");
                    }
                    break;
                    
                case 2:
                    System.out.println("¿Eres organizador?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    System.out.println("0. Volver");
                    
                    String inp = System.console().readLine();
                    int resp = Integer.parseInt(inp);
                    
                    switch(resp) {
                        case 1:
                            System.out.println("Bienvenido al equipo de Boletmaster");
                            System.out.println("..................................................");
                            System.out.println("Crea un nombre de usuario: ");
                            String log = System.console().readLine();
                            System.out.println("Crea una contraseña: ");
                            String pas = System.console().readLine();
                            
                            Organizador nuevo = new Organizador(log,pas);
                            this.guardarOrg("organizadores.json", nuevo);
                            this.organizadores.add(nuevo);
                            System.out.println("Creado con éxito");
                            break;
                            
                        case 2:
                            System.out.println("Bienvenido a Boletmaster");
                            System.out.println("..................................................");
                            System.out.println("Crea un nombre de usuario: ");
                            String log2 = System.console().readLine();
                            System.out.println("Crea una contraseña: ");
                            String pas2 = System.console().readLine();
                            
                            Cliente nuevo2 = new Cliente(log2,pas2);
                            this.clientes.add(nuevo2);
                            this.guardarCliente("clientes.json", nuevo2);
                            System.out.println("Creado con éxito");
                            break;
                            
                        case 0:
                            break;
                    }
                    break;
                    
                case 0:
                    break;
            }
            
            System.out.println("\n¿Quieres realizar otra operación?");
            System.out.println("1. Crear otro usuario");
            System.out.println("0. Volver al menú principal");
            
            String continuar = System.console().readLine();
            opcion = Integer.parseInt(continuar);
            
            if (opcion == 1) {
                System.out.println("¿Eres staff?");
                System.out.println("1. Si");
                System.out.println("2. No");
                System.out.println("0. Volver");
                System.out.println("");
                
                in = System.console().readLine();
                opcion = Integer.parseInt(in);
            }
        }
    }
	
	



	
	
	
		
	public void menu() {
		
		System.out.println("Bienvenido al menú principal");
	    System.out.println("Primero dinos quién eres");
	    System.out.println("............................................");
	    
	    
        
        int opcion = -1;
	    while (opcion != 0) {
	    	System.out.println("1. Cliente");
	        System.out.println("2. Administrador");
	        System.out.println("3. Organizador");
	        System.out.println("0. Volver");
	        
	        String in = System.console().readLine();
	        opcion = Integer.parseInt(in);
	    	
        switch(opcion) {
        
        case 1: 
        	
        	System.out.println("Usuario:");
        	
        	String logC = System.console().readLine();
        	
        	System.out.println("Contraseña:");
        	
        	String pasC = System.console().readLine();
        	
        	boolean encontradoC = false;

            for (Cliente c : clientes) {
                if (c.getLog().equals(logC)) {
                    encontradoC = true;
                    if (c.getContraseña().equals(pasC)) {
                        System.out.println("Inicio de sesión exitoso. Bienvenido, " + logC + "!");
                        c.menuCliente(this);
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                    break;
                }
            }

            if (!encontradoC)
                System.out.println("Usuario no encontrado.");
            break;
        case 2:
        	
        	System.out.println("Usuario:");
        	
        	String logAd = System.console().readLine();
        	
        	System.out.println("Contraseña:");
        	
        	String pasAd = System.console().readLine();
        	
        	boolean encontradoAd = false;

            for (Administrador ad : staff) {
                if (ad.getLog().equals(logAd)) {
                    encontradoAd = true;
                    if (ad.getContraseña().equals(pasAd)) {
                        System.out.println("Inicio de sesión exitoso. Bienvenido, organizador " + logAd + "!");
                        ad.menuAdmin(this);
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                    break;
                }
            }

            if (!encontradoAd)
                System.out.println("Usuario no encontrado.");
        	
        	
        	break;
        	
        case 3:
        	
        	System.out.println("Usuario:");
        	
        	String logO = System.console().readLine();
        	
        	System.out.println("Contraseña:");
        	
        	String pasO = System.console().readLine();
        	
        	boolean encontradoO = false;

            for (Organizador o : organizadores) {
                if (o.getLog().equals(logO)) {
                    encontradoO = true;
                    if (o.getContraseña().equals(pasO)) {
                        System.out.println("Inicio de sesión exitoso. Bienvenido, organizador " + logO + "!");
                        o.menuOrg(this);
                    } else {
                        System.out.println("Contraseña incorrecta.");
                    }
                    break;
                }
            }

            if (!encontradoO)
                System.out.println("Usuario no encontrado.");
            break;
        
   
        	
        	
        case 0:
        	break;
        
        
        }
        }
		
	}
	
	public void debugUbicacionArchivos() {
	    System.out.println("=== DEBUG UBICACIÓN ECLIPSE ===");
	    System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
	    System.out.println("Directorio del usuario: " + System.getProperty("user.home"));
	    
	    String[] archivos = {"clientes.json", "eventosProx.json"};
	    
	    for (String archivo : archivos) {
	        File file = new File(archivo);
	        System.out.println("Buscando: " + archivo);
	        System.out.println("Ruta absoluta: " + file.getAbsolutePath());
	        System.out.println("Existe: " + file.exists());
	        System.out.println("---");
	    }
	}
	
	public static void main(String[] args) {
	    Aplicacion app = new Aplicacion();
	    
	    app.cargarTodo();
	    
	    app.debugUbicacionArchivos();
	    
	    System.out.println("Bienvenido a Boletmaster");
	    System.out.println("Nuestro sistema le ayudará a gestionar todas sus necesidades en el sistema de venta de tiquetes");
	    System.out.println("............................................");

	    int opcion = -1;
	    while (opcion != 0) {
	        System.out.println("\nElija una de las siguientes opciones para continuar:");
	        System.out.println("1. Crear usuario");
	        System.out.println("2. Iniciar sesión");
	        System.out.println("0. Salir");
	        System.out.print("¿Qué quieres hacer?: ");

	        String in = System.console().readLine();
	        opcion = Integer.parseInt(in);

	        switch (opcion) {
	            case 1:
	                app.registro();
	                break;
	            case 2:
	                app.menu();
	                break;
	            case 0:
	                System.out.println("¡Gracias por preferirnos, nos vemos luego!");
	                break;
	            default:
	                System.out.println("Opción inválida");
	        }
	    }
	}}
