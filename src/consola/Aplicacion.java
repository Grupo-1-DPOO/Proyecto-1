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
	
	ArrayList<Cliente> clientes;
	
	ArrayList<Organizador> organizadores;
	
	ArrayList<Administrador> staff; //Lista exclusiva de administradores
	
	ArrayList<Evento> cancelados; //Lista de eventos cancelados por el administrador
	
	ArrayList<Evento> eventosProx; //Lista de todos los eventos acualmente activos (pasando o que van a pasar)
	
	ArrayList<Evento> eventosPas; //Lista de todos los eventos vencidos (pasados o cancelados)
	
	ArrayList<Venue> venues; //Lista de todos los lugares actualmente regisrados con la aplicación
	
	ArrayList<Tiquete> activos; //Lista de todos los tiquetes actualmente activos y válidos, una vez usados se eliminan de aquí, permitiendo reusar identificadores

	public ArrayList<Evento> pendientes; //Lista de eventos pendiendtes por aprobar por los administradores
	
	public Aplicacion() {
		
		this.clientes = new ArrayList<Cliente>(); 
		
		this.staff = new ArrayList<Administrador>();
		
		this.eventosProx = new ArrayList<Evento>(); 
		
		this.pendientes = new ArrayList<Evento>();
		
		this.eventosPas = new ArrayList<Evento>(); 
		
		this.venues = new ArrayList<Venue>(); 
		
		this.activos = new ArrayList<Tiquete>(); 
		
		this.organizadores = new ArrayList<Organizador>();
		
		this.cancelados = new ArrayList<Evento>();

		
		
		crearArchivoSiNoExiste("clientes.txt");
		crearArchivoSiNoExiste("organizadores.txt");
        crearArchivoSiNoExiste("staff.txt");
        crearArchivoSiNoExiste("eventosProx.txt");
        crearArchivoSiNoExiste("eventosPas.txt");
        crearArchivoSiNoExiste("venues.txt");
        crearArchivoSiNoExiste("activos.txt");
        crearArchivoSiNoExiste("pendientes.txt");
        crearArchivoSiNoExiste("cancelados.txt");
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
	
	private void guardarAdmin(String archivo, Administrador admin) {
	    try (FileWriter writer = new FileWriter(archivo, true)) {
	        writer.write(admin.toString());
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}
	
	private void guardarCliente(String archivo,Cliente cli) {
	    try (FileWriter writer = new FileWriter(archivo, true)) {
	        writer.write(cli.toString());
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}
	
	private void guardarOrg(String archivo, Organizador org) {
	    try (FileWriter writer = new FileWriter(archivo, true)) {
	        writer.write(org.toString());
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}
	
	private void guardarEvento(String archivo, Evento ev) {
	    try (FileWriter writer = new FileWriter(archivo, true)) {
	        writer.write(ev.toString());
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}
	
	private void guardarVenue(String archivo, Venue ev) {
	    try (FileWriter writer = new FileWriter(archivo, true)) {
	        writer.write(ev.toString());
	    } catch (IOException e) {
	        System.out.println("Error al guardar en " + archivo);
	    }
	}
	
	
	


	
	public void registro() {
		
		System.out.println("¿Eres staff?");
		
		System.out.println("1. Si");
		
		System.out.println("2. No");
		
		System.out.println("0. Volver");
		
		System.out.println("");
		
		String in = System.console().readLine();
		
		int opcion = Integer.parseInt(in);
		
		while(opcion!=0) {
			
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
					
					this.guardarAdmin("staff.txt", nuevo);
					
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
					
					this.guardarOrg("organizadores.txt", nuevo);
					
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
					
					Cliente nuevo2= new Cliente(log2,pas2);
					
					this.clientes.add(nuevo2);
					
					this.guardarCliente("clientes.txt",nuevo2);
					
					System.out.println("Creado con éxito");
					
					break;
					
				case 0:
					break;
					
				}
			
			case 0:
				break;
			}
			opcion=0;
			
		}
		
		
	}
	
	public void menuAdmin(Administrador admin) {
	    System.out.println("Bienvenido al menú para administradores");

	    int opcion = -1;
	    while (opcion != 0) {

	        System.out.println("1. Revisar pendientes");
	        System.out.println("2. Revisar eventos próximos");
	        System.out.println("3. Revisar eventos pasados");
	        System.out.println("0. Volver");

	        String input = System.console().readLine();
	        opcion = Integer.parseInt(input);

	        switch (opcion) {

	            // -------------------- CASE 1 --------------------
	            case 1:
	                System.out.println("Actualmente hay " + this.pendientes.size() + " eventos pendientes");

	                int opp = -1;
	                int actual = 0;
	                while (opp != 0 && actual < this.pendientes.size()) {

	                    Evento ev = this.pendientes.get(actual);

	                    System.out.println(".....................................................");
	                    System.out.println("Nombre: " + ev.getNombre());
	                    System.out.println("Cap. Total: " + ev.getCapacidadTotalEvento());
	                    System.out.println("Hora de inicio: " + ev.getHoraIni());
	                    System.out.println("Hora de cierre: " + ev.getHoraFin());
	                    System.out.println("Precio base: " + ev.getPrecioBase());
	                    System.out.println(".....................................................");

	                    System.out.println("1. Aprobar");
	                    System.out.println("2. Cancelar");
	                    System.out.println("0. Salir");

	                    String in = System.console().readLine();
	                    opp = Integer.parseInt(in);

	                    switch (opp) {

	                        case 1:
	                            System.out.println("Inserta un porcentaje de ganancia para la tiquetera:");
	                            String x = System.console().readLine();
	                            double tasa = Double.parseDouble(x);

	                            ev.tasa = tasa / 100;
	                            ev.setPrecioBase(ev.getPrecioBase() + (ev.getPrecioBase() * ev.tasa));

	                            this.eventosProx.add(ev);
	                            this.guardarEvento("eventosProx.txt", ev);

	                            System.out.println("Comisión añadida. Evento aprobado.");
	                            actual++;
	                            break;

	                        case 2:
	                            this.cancelados.add(ev);
	                            this.guardarEvento("cancelados.txt", ev);
	                            System.out.println("Cancelación exitosa.");
	                            actual++;
	                            break;

	                        case 0:
	                            System.out.println("Saliendo del menú de pendientes...");
	                            break;

	                        default:
	                            System.out.println("Opción no válida, intenta de nuevo.");
	                            break;
	                    }
	                }

	                if (actual >= this.pendientes.size()) {
	                    System.out.println("No hay más eventos pendientes por revisar.");
	                }
	                break;

	            // -------------------- CASE 2 --------------------
	            case 2:
	                int opt = -1;
	                int act = 0;
	                while (opt != 0 && act < this.eventosProx.size()) {

	                    Evento ev = this.eventosProx.get(act);

	                    System.out.println(".....................................................");
	                    System.out.println("Nombre: " + ev.getNombre());
	                    System.out.println("Cap. Total: " + ev.getCapacidadTotalEvento());
	                    System.out.println("Hora de inicio: " + ev.getHoraIni());
	                    System.out.println("Hora de cierre: " + ev.getHoraFin());
	                    System.out.println("Precio base: " + ev.getPrecioBase());
	                    System.out.println(".....................................................");

	                    System.out.println("1. Siguiente");
	                    System.out.println("2. Cancelar");
	                    System.out.println("0. Salir");

	                    String in = System.console().readLine();
	                    opt = Integer.parseInt(in);

	                    switch (opt) {

	                        case 1:
	                            act++;
	                            break;

	                        case 2:
	                            // Cancelar evento y reembolsar
	                            for (Cliente cli : clientes) {
	                                for (Tiquete tiq : cli.getTiqVi()) {
	                                    for (Tiquete id : ev.getTiqPros()) {
	                                        if (id.getIdentificador().equals(tiq.getIdentificador())) {
	                                            cli.setSaldo(cli.getSaldo() + (tiq.getCosto() - ev.tasa));
	                                        }
	                                    }
	                                }
	                            }
	                            this.cancelados.add(ev);
	                            this.guardarEvento("cancelados.txt", ev);
	                            System.out.println("Cancelación exitosa.");
	                            break;

	                        case 0:
	                            System.out.println("Saliendo del menú de eventos próximos...");
	                            break;

	                        default:
	                            System.out.println("Opción no válida.");
	                            break;
	                    }
	                }
	                break;

	            // -------------------- CASE 3 --------------------
	            case 3:
	                int dec = -1;
	                while (dec != 0) {

	                    System.out.println("Buscar por: ");
	                    System.out.println("1. Organizador");
	                    System.out.println("2. Fecha");
	                    System.out.println("3. Evento");
	                    System.out.println("0. Volver");

	                    String j = System.console().readLine();
	                    dec = Integer.parseInt(j);

	                    switch (dec) {

	                        case 1:
	                            double suma = 0;
	                            Organizador nomen = null;

	                            System.out.println("Ingresa el login del organizador:");
	                            String cognomen = System.console().readLine();

	                            for (Organizador o : organizadores) {
	                                if (o.getLog().equals(cognomen)) {
	                                    nomen = o;
	                                    break;
	                                }
	                            }

	                            if (nomen == null) {
	                                System.out.println("No se encontró el organizador " + cognomen);
	                                break;
	                            }


	                            for (Evento ev : this.eventosPas) {
	                                if (ev.getOrganizador().equals(nomen)) {

	                                    System.out.println(".............................................");
	                                    System.out.println("Organizador: " + ev.getOrganizador());
	                                    System.out.println("Evento: " + ev.getNombre());

	                                    for (Tiquete tiquete : ev.getTiqPros()) {
	                                        suma += tiquete.getCosto() * ev.tasa;
	                                    }
	                                }
	                            }

	                            System.out.println("Ganancias totales: " + suma);
	                            break;

	                        case 2:

	                            System.out.println("Ingresa la fecha (DD/MM/AAAA)");
	                            String jour = System.console().readLine();
	                            
	                            double sum = 0;
	                            Evento res = null;
	                            
	                            for (Evento fe : eventosPas) {
	                                if (fe.getFecha().equals(jour)) {
	                                    res = fe;
	                                    break;
	                                }
	                            }
	                            
	                            if (res == null) {
	                                System.out.println("No se encontró la fecha " + jour);
	                                break;
	                            }
	                            
	                            
	                            for (Evento ev : this.eventosPas) {
	                                if (ev.getFecha().equals(jour)) {

	                                    System.out.println(".............................................");
	                                    System.out.println("Organizador: " + ev.getOrganizador());
	                                    System.out.println("Evento: " + ev.getNombre());

	                                    for (Tiquete tiquete : ev.getTiqPros()) {
	                                        sum += tiquete.getCosto() * ev.tasa;
	                                    }
	                                }
	                            }
	                            System.out.println("Ganancias totales: " + sum);
	                            break;
	                            
	                        case 3:
	                        
	                        	double addit = 0;
	                            Evento event = null;

	                            System.out.println("Ingresa el nombre del evento:");
	                            String praenomen = System.console().readLine();

	                            for (Evento x : eventosPas) {
	                                if (x.getNombre().equals(praenomen)) {
	                                    event = x;
	                                    break;
	                                }
	                            }

	                            if (event == null) {
	                                System.out.println("No se encontró el organizador " + praenomen);
	                                break;
	                            }


	                            for (Evento ev : this.eventosPas) {
	                                if (ev.getNombre().equals(praenomen)) {

	                                    System.out.println(".............................................");
	                                    System.out.println("Organizador: " + ev.getOrganizador());
	                                    System.out.println("Evento: " + ev.getNombre());

	                                    for (Tiquete tiquete : ev.getTiqPros()) {
	                                        addit += tiquete.getCosto() * ev.tasa;
	                                    }
	                                }
	                            }

	                            System.out.println("Ganancias totales: " + addit);
	                            break;

	                        case 0:
	                            System.out.println("Volviendo al menú anterior...");
	                            break;

	                        default:
	                            System.out.println("Opción no válida.");
	                            break;
	                    }
	                }
	                break;

	            case 0:
	                System.out.println("Saliendo del menú de administrador...");
	                break;

	            default:
	                System.out.println("Opción no válida. Intente de nuevo.");
	                break;
	        }
	    }
	}

	
	public void menuOrg(Organizador org) {
		
		System.out.println("Bienvenido al menú para organizadores");
		
		
		
		 int opcion = -1;
		 while (opcion != 0) {
			
			System.out.println("1. Crear un evento");
			
			System.out.println("2. Crear un venue");
			
			System.out.println("3. Acceder a tu menú de cliente");
			
			System.out.println("0. Volver");
			
			String in = System.console().readLine();
			
			opcion = Integer.parseInt(in);
			
			
			switch (opcion) {
			
			case 1:
				
				System.out.println("Vamos a registrar un nuevo evento!");
				
				System.out.println("....................................................");
				
				System.out.println("¿Cómo se llama?: ");
				
				String nomm = System.console().readLine();
				
				System.out.println("Añade una capacidad máxima (per cápita): ");
				
				String i = System.console().readLine();
				
				int capp = Integer.parseInt(i);
				
				System.out.println("¿Que tipo de evento es? (Religioso, Músical, Artístico, Cine, Literatura, etc...)");
				
				String tip = System.console().readLine();
				
				System.out.println("Inserta una fecha: ");
				
				String fecha = System.console().readLine();
				
				System.out.println("Inserta una hora de inicio");
				
				String ini = System.console().readLine();
				
				System.out.println("Inserta una hora de cierre");
				
				String fin = System.console().readLine();
				
				System.out.println("Inserta el precio base (el más básico) de los tiquetes por unidad:");
				
				String price = System.console().readLine();
				
				Double prix = Double.parseDouble(price);
				
				System.out.println("Inserta el nombre del Venue de tu preferencia:");
				
				String nomen = System.console().readLine();
				
				Venue ven = null;
				
				for (Venue x: venues) {
					
					if(x.getNombre().equals(nomen)) {
						
						ven=x;
						
						break;		
					}
	
				}
				
				if (ven==null) {
					System.out.println("No se encontró un venue con ese nombre. Regresando al menú anterior...");
					break;
				}
				
				Evento saturnalia = new Evento(org,capp,nomm,tip,fecha,ini,fin,prix,ven);
				
				saturnalia.agregarLocalidades();
				
				this.pendientes.add(saturnalia);
				
				this.guardarEvento("pendientes.txt", saturnalia);
				
				System.out.println("Solicitud de evento creada correctamente!");
				
				break;
				
			case 2:
				
				System.out.println("Vamos a registrar un nuevo venue!");
				
				System.out.println("....................................................");
				
				System.out.println("Añade una dirección: ");
				
				String dir = System.console().readLine();
				
				System.out.println("Añade una capacidad máxima (per cápita): ");
				
				String capa = System.console().readLine();
				
				int cap = Integer.parseInt(capa);
				
				System.out.println("¿Cómo se llama?: ");
				
				String nom = System.console().readLine();
				
				Venue nuovo = new Venue(dir, cap, nom);
				
				
				System.out.println("Vamos a añadirle localidades:");
				
				int op = -1;
				while (op != 0) {
					
					System.out.println("1. Añadir");
					
					System.out.println("0. Finalizar");
					
					String decision = System.console().readLine();
					
					op = Integer.parseInt(decision);
					
					
					switch(op) {
					
					case 1:
						
						System.out.println("Si es la primera localidad que ingresas, asegúrate de que sea la más básica (La localidad base, y la más económica)");
						
						System.out.println("Ingresa el nombre: ");
						
						String nomb = System.console().readLine();
						
						System.out.println("Ingresa el porcentaje de aumento de precio (si es la localidad básica debería ser 0): ");
						
						String por = System.console().readLine();
						
						int porcentaje = (int) Double.parseDouble(por);
						
						
						System.out.println("Capacidad máxima de personas (per cápita): ");
						
						String perc = System.console().readLine();
						
						int perCapita = Integer.parseInt(perc);
						
						nuovo.agregarLocalidad(nomb, porcentaje, perCapita);
						
						break;
						
					case 0:
						
						break;
					}
					
				}
				
				this.venues.add(nuovo);
				this.guardarVenue("venues.txt", nuovo);
				System.out.println("Venue creado exitosamente y registrado en el sistema.");
                break;
				
			case 3:
				
				break;
				
			case 0:
				
				break;
			
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
                        menuAdmin(ad);
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
                        menuOrg(o);
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
	
	
	public static void main(String[] args) {
	    Aplicacion app = new Aplicacion();

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

