package consola;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Usuarios.Administrador;
import Usuarios.Cliente;
import Usuarios.Organizador;
import eventos.Evento;
import eventos.Venue;
import tiquetes.Tiquete;

public class Aplicacion {
	
	ArrayList<Cliente> clientes; //Esto inlcuye organizadores
	
	ArrayList<Administrador> staff; //Lista exclusiva de administradores
	
	ArrayList<Evento> eventosProx; //Lista de todos los eventos acualmente activos (pasando o que van a pasar)
	
	ArrayList<Evento> eventosPas; //Lista de todos los eventos vencidos (pasados o cancelados)
	
	ArrayList<Venue> venues; //Lista de todos los lugares actualmente regisrados con la aplicación
	
	ArrayList<Tiquete> activos; //Lista de todos los tiquetes actualmente activos y válidos, una vez usados se eliminan de aquí, permitiendo reusar identificadores

	public static ArrayList<Evento> pendientes; //Lista de eventos pendiendtes por aprobar por los administradores
	
	public Aplicacion() {
		
		this.clientes = new ArrayList<Cliente>(); 
		
		this.staff = new ArrayList<Administrador>();
		
		this.eventosProx = new ArrayList<Evento>(); 
		
		this.eventosPas = new ArrayList<Evento>(); 
		
		this.venues = new ArrayList<Venue>(); 
		
		this.activos = new ArrayList<Tiquete>(); 
		
		
		crearArchivoSiNoExiste("clientes.txt");
        crearArchivoSiNoExiste("staff.txt");
        crearArchivoSiNoExiste("eventosProx.txt");
        crearArchivoSiNoExiste("eventosPas.txt");
        crearArchivoSiNoExiste("venues.txt");
        crearArchivoSiNoExiste("activos.txt");
	}
	
	
	private void crearArchivoSiNoExiste(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + nombreArchivo);
            } else {
                System.out.println("Archivo ya existe: " + nombreArchivo);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo " + nombreArchivo);
        }
    }
	
	private void guardarAdmin(String archivo, String log, String pas, String tipo) {
	    try (FileWriter writer = new FileWriter(archivo, true)) { // true = append
	        writer.write(log + "," + pas + "," + tipo + "," +"\n");
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}
	
	private void guardarCliente(String archivo, String log, String pas, String tipo) {
	    try (FileWriter writer = new FileWriter(archivo, true)) { // true = append
	        writer.write(log + "," + pas + "," + tipo + "," +"\n");
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}


	
	public void registro() {
		
		System.out.println("¿Eres staff?");
		
		System.out.println("");
		
		String in = System.console().readLine();
		
		int opcion = Integer.parseInt(in);
		
		while(opcion!=0) {
		
			System.out.println("1. Si");
			
			System.out.println("2. No");
			
			System.out.println("0. Volver");
			
			switch(opcion) {
			
			case 1:
				
				System.out.println("Ingresa la contraseña mestra");
				
				String mast = System.console().readLine();
				
				if (mast.equals("LlaveMaestra")) {
					
					System.out.println("Bienvenido al equipo de Boletmaster");
					
					System.out.println("..................................................");
					
					System.out.println("Crea un nombre de usuario: ");
					
					String log = System.console().readLine();
					
					System.out.println("Crea una contraseña: ");
					
					String pas = System.console().readLine();
					
					Administrador nuevo= new Administrador(log,pas);
					
					this.staff.add(nuevo);
					
					this.guardarAdmin("staff.txt", log, pas, nuevo.getTipo());
					
					System.out.println("Creado con éxito");
					
					break;
					
				}
				
				else {System.out.println("Llave no válida, intentar de nuevo."); break;}
				
				
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
					
					Organizador nuevo= new Organizador(log,pas);
					
					this.clientes.add(nuevo);
					
					System.out.println("Creado con éxito");
					
					break;
					
				case 2:
					
					System.out.println("Bienvenido a Boletmaster");
					
					System.out.println("..................................................");
					
					System.out.println("Crea un nombre de usuario: ");
					
					String log2 = System.console().readLine();
					
					System.out.println("Crea una contraseña: ");
					
					String pas2 = System.console().readLine();
					
					Cliente nuevo2= new Cliente(log2,pas2);
					
					this.clientes.add(nuevo2);
					
					System.out.println("Creado con éxito");
					
					break;
					
				case 0:
					break;
					
				}
			
			case 0:
				break;
			}
			
			System.out.println("\n¿Eres staff?");
			in = System.console().readLine();
			opcion = Integer.parseInt(in);
			
		}
		
		
	}
	
	
	public void menu() {
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		ArrayList<Integer> caca= new ArrayList<Integer>();
		
		caca.add(1);
		
		caca.add(2);
		
		caca.add(3);
		
		System.out.println(caca);
		
		
		Aplicacion app = new Aplicacion(); // ← aquí creas la instancia
		
		System.out.println("Bienvenido a Boletmaster");
		
		System.out.println("");
		
		System.out.println("Nuestro sistema le ayudará a gestionar todas sus necesidades en el sistema de venta de tiquetes");
		
		System.out.println("............................................");
		
		System.out.println("............................................");
		
		String in = System.console().readLine();
		
		int opcion = Integer.parseInt(in);
		
		
		while(opcion!=0) {
		
			System.out.println("Elija una de las siguientes opciones para continuar:");
			
			System.out.println("");
			
			System.out.println("1. Crear usuario");
			
			System.out.println("2. Iniciar sesión");
			
			System.out.println("0. Salir");
			
			
			System.out.println("¿Que quieres hacer?: ");
			
			switch(opcion) {
			
			case 1:
				
				app.registro();
				
			case 0:
				
				app.menu();
				
			case 3:
				
				System.out.println("¡Gracias por preferirnos, nos vemos luego!");
				break;
				
				
			default:
				System.out.println("Opción Inválida");
			
			
			}
			
			System.out.println("\n¿Que quieres hacer?: ");
	        in = System.console().readLine();
	        opcion = Integer.parseInt(in);
			
			
			
		}
			
		
		
		
		
		
		
		
	}


	

}
