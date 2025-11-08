package consola;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Usuarios.Administrador;
import Usuarios.Cliente;
import Usuarios.Organizador;
import eventos.Evento;
import eventos.Localidad;
import eventos.Venue;
import eventos.VenueNumerado;
import tiquetes.Tiquete;
import tiquetes.TiqueteBasico;
import tiquetes.TiqueteDeluxe;
import tiquetes.TiqueteGrupal;
import tiquetes.TiqueteNumerado;
import permanencia.JsonManager; // 👈 importa tu clase JsonManager

public class Aplicacion {
	
	private ArrayList<Cliente> clientes;
	private ArrayList<Organizador> organizadores;
	private ArrayList<Administrador> staff; 
	private ArrayList<Evento> cancelados; 
	private ArrayList<Evento> eventosProx; 
	private ArrayList<Evento> eventosPas; 
	private ArrayList<Venue> venues; 
	private ArrayList<Tiquete> activos; 
	private ArrayList<Evento> pendientes; 
	
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

		// Crear los archivos JSON si no existen
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
        clientes = new ArrayList<>(JsonManager.cargarLista("clientes.json", Cliente.class));
        organizadores = new ArrayList<>(JsonManager.cargarLista("organizadores.json", Organizador.class));
        staff = new ArrayList<>(JsonManager.cargarLista("staff.json", Administrador.class));
        eventosProx = new ArrayList<>(JsonManager.cargarLista("eventosProx.json", Evento.class));
        eventosPas = new ArrayList<>(JsonManager.cargarLista("eventosPas.json", Evento.class));
        venues = new ArrayList<>(JsonManager.cargarLista("venues.json", Venue.class));
        activos = new ArrayList<>(JsonManager.cargarLista("activos.json", Tiquete.class));
        pendientes = new ArrayList<>(JsonManager.cargarLista("pendientes.json", Evento.class));
        cancelados = new ArrayList<>(JsonManager.cargarLista("cancelados.json", Evento.class));
    }


    
 // Guarda un administrador en staff.json
    public void guardarAdmin(String archivo, Administrador admin) {
        List<Administrador> admins = JsonManager.cargarLista(archivo, Administrador.class);
        admins.add(admin);
        JsonManager.guardarLista(archivo, admins);
    }

    // Guarda un organizador en organizadores.json
    public void guardarOrg(String archivo, Organizador org) {
        List<Organizador> organizadores = JsonManager.cargarLista(archivo, Organizador.class);
        organizadores.add(org);
        JsonManager.guardarLista(archivo, organizadores);
    }

    // Guarda un cliente en clientes.json
    public void guardarCliente(String archivo, Cliente cli) {
        List<Cliente> clientes = JsonManager.cargarLista(archivo, Cliente.class);
        clientes.add(cli);
        JsonManager.guardarLista(archivo, clientes);
    }

    // Guarda un evento (pendiente, aprobado, cancelado, etc.)
    public void guardarEvento(String archivo, Evento ev) {
        List<Evento> eventos = JsonManager.cargarLista(archivo, Evento.class);
        eventos.add(ev);
        JsonManager.guardarLista(archivo, eventos);
    }

    // Guarda un venue
    public void guardarVenue(String archivo, Venue venue) {
        List<Venue> venues = JsonManager.cargarLista(archivo, Venue.class);
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
	
	
	public void menuCliente(Cliente cli) {
	    System.out.println("Bienvenido al menú para clientes!");
	    
	    int opcion = -1;
	    while (opcion != 0) {

	        System.out.println("1. Buscar eventos");
	        System.out.println("2. Consultar Saldo");
	        System.out.println("3. Transferir tiquete");
	        System.out.println("4. Buscar tiquete");
	        System.out.println("0. Volver");

	        String input = System.console().readLine();
	        try {
	            opcion = Integer.parseInt(input);
	        } catch (Exception e) {
	            System.out.println("Entrada inválida, intenta de nuevo.");
	            continue;
	        }

	        switch (opcion) {
	        
	        case 1:
	            
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
	                System.out.println("2. Comprar tiquetes");
	                System.out.println("0. Salir");

	                String in = System.console().readLine();
	                try {
	                    opt = Integer.parseInt(in);
	                } catch (Exception e) {
	                    System.out.println("Entrada inválida, intenta de nuevo.");
	                    continue;
	                }

	                switch (opt) {
	                
	                case 1:
	                    act++;
	                    break;

	                case 2:
	               
	                    int decc = -1;
	                    
	                    while (decc != 0) {
	                        
	                        System.out.println("1. Comprar tiquete básico (Nuestra opción más económica)");
	                        System.out.println("2. Comprar tiquete deluxe, incluye: " + ev.beneficios);
	                        System.out.println("0. Volver");
	                        
	                        String dec = System.console().readLine();
	                        
	                        try {
	                            decc = Integer.parseInt(dec);
	                        } catch (Exception e) {
	                            System.out.println("Entrada inválida, intenta de nuevo.");
	                            continue;
	                        }
	                        
	                        switch (decc) {
	                        
	                        case 1:
	                            
	                            System.out.println("Selecciona la localidad de tu preferencia:");
	                            
	                            int x = 0;
	                            for (Localidad loc : ev.getLocalidades()) {
	                                System.out.println(".....................................................");
	                                System.out.println("Opcion: " + x);
	                                System.out.println("Nombre: " + loc.getNombre());
	                                System.out.println("Cap. Total: " + loc.getCapacidad());
	                                System.out.println("Porcentaje de aumento de precio: " + loc.getPorcentaje());
	                                System.out.println(".....................................................");
	                                x++;
	                            }
	                            
	                            String localidadSeleccionada = System.console().readLine();
	                            int sel = Integer.parseInt(localidadSeleccionada);
	                            
	                            Localidad lieu = ev.getLocalidades().get(sel);
	                            
	                            if (ev.getVenue() instanceof VenueNumerado) {
	                                
	                                System.out.println("El venue de este evento es numerado, por lo que se generará un toquete de este tipo");
	                                System.out.println("Para los venues numerados no se pueden comprar para varias personas en uno solo, se generarán tantos tiquetes como indiques, y se asignarán de manera adyacente");
	                                System.out.println("¿Cuantas personas?");
	  
	                                int peuple = Integer.parseInt(System.console().readLine());
	                                
	                                System.out.println("Tu tiquete tendría un costo de: " + (ev.getPrecioBase()+(ev.getPrecioBase()*(lieu.getPorcentaje()/100))) +"por persona.");
	                                
	                                System.out.println("1. Pago por saldo");
	                                System.out.println("2. Pago por terceros");
	                                System.out.println("0. Volver");
	                                
	                                int pago = Integer.parseInt(System.console().readLine());
	                                
	                                if (pago == 1) {

	                                    // Verificación de saldo
	                                    double costoUnit = ev.getPrecioBase() + (ev.getPrecioBase() * (lieu.getPorcentaje()/100));
	                                    double costoTotal = costoUnit * peuple;

	                                    if (cli.getSaldo() < costoTotal) {
	                                        System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                        break;
	                                    }

	                                    for (int i = 0; i < peuple; i++) {
	                                        TiqueteNumerado tiqNum = new TiqueteNumerado(ev.getPrecioBase(), lieu, ev, cli);
	                                        cli.getTiqVi().add(tiqNum);
	                                        ev.getTiqRes().add(tiqNum);
	                                        cli.setSaldo(cli.getSaldo() - tiqNum.getCosto());
	                                    }
	                                    System.out.println("Tiquetes generados correctamente");
	                                    System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                                }
	                                
	                                else if (pago == 2) {
	                                    
	                                    for (int i = 0; i < peuple; i++) {
	                                        TiqueteNumerado tiqNum = new TiqueteNumerado(ev.getPrecioBase(), lieu, ev, cli);
	                                        cli.getTiqVi().add(tiqNum);
	                                        ev.getTiqRes().add(tiqNum);
	                                    }
	                                    System.out.println("Tiquetes generados correctamente");
	                                    System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                                    
	                                }
	                                
	                                else if (pago == 0) {
	                                    System.out.println("Volviendo al menú anterior...");
	                                }
	                                
	                                else {
	                                    System.out.println("Respuesta incorrecta, vuelve a intentarlo");
	                                }
	                                
	                            }
	                            
	                            else {
	                                System.out.println("¿Cuantas personas?");
	                                  
	                                int peuple = Integer.parseInt(System.console().readLine());
	                                
	                                System.out.println("Tu tiquete tendría un costo de: " + (ev.getPrecioBase()+(ev.getPrecioBase()*(lieu.getPorcentaje()/100))) +"por persona.");
	                                
	                                System.out.println("1. Pago por saldo");
	                                System.out.println("2. Pago por terceros");
	                                System.out.println("0. Volver");
	                                
	                                int pago = Integer.parseInt(System.console().readLine());
	                                
	                                if (pago == 1) {
	                                    
	                                    if (peuple > 1) {
	                                        TiqueteGrupal tiq = new TiqueteGrupal(peuple, lieu, ev, cli);

	                                        if (cli.getSaldo() < tiq.getCosto()) {
	                                            System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                            break;
	                                        }

	                                        cli.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                        cli.setSaldo(cli.getSaldo() - tiq.getCosto());
	                                    } else {
	                                        TiqueteBasico tiq = new TiqueteBasico(lieu, ev, cli);

	                                        if (cli.getSaldo() < tiq.getCosto()) {
	                                            System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                            break;
	                                        }

	                                        cli.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                        cli.setSaldo(cli.getSaldo() - tiq.getCosto());
	                                    }
	                                    
	                                    System.out.println("Tiquetes generados correctamente");
	                                    System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                                }
	                                
	                                else if (pago == 2) {
	                                    
	                                    if (peuple > 1) {
	                                        TiqueteGrupal tiq = new TiqueteGrupal(peuple, lieu, ev, cli);
	                                        cli.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                    } else {
	                                        TiqueteBasico tiq = new TiqueteBasico(lieu, ev, cli);
	                                        cli.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                    }
	                                    
	                                    System.out.println("Tiquetes generados correctamente");
	                                    System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                                    
	                                }
	                                
	                                else if (pago == 0) {
	                                    System.out.println("Volviendo al menú anterior...");
	                                }
	                                
	                                else {
	                                    System.out.println("Respuesta incorrecta, vuelve a intentarlo");
	                                }
	                            }
	                            break;
	                            
	                        case 2:
	                            
	                            System.out.println("Los tiquetes deluxe tienen un precio extra, e incluyen unos beneficios específicos.");
	                            System.out.println("Estos tiquetes sólamente pueden tener una persona, y son intransferibles");
	                            System.out.println("Selecciona la localidad de tu preferencia:");
	                            
	                            int y = 0;
	                            for (Localidad loc : ev.getLocalidades()) {
	                                System.out.println(".....................................................");
	                                System.out.println("Opcion: " + y);
	                                System.out.println("Nombre: " + loc.getNombre());
	                                System.out.println("Cap. Total: " + loc.getCapacidad());
	                                System.out.println("Porcentaje de aumento de precio: " + loc.getPorcentaje());
	                                System.out.println(".....................................................");
	                                y++;
	                            }
	                            
	                            String selec = System.console().readLine();
	                            int selselect = Integer.parseInt(selec);
	                            Localidad place = ev.getLocalidades().get(selselect);
	                            
	                            if (ev.getVenue() instanceof VenueNumerado) {
	                                System.out.println("Lo sentimos, los venues numerados no cuentan con la posibilidad de comprar tiquetes deluxe");
	                                System.out.println("Volviendo al menú anterior...");
	                                break;
	                            }
	                            
	                            System.out.println("Tu tiquete tendría un costo de: " + (ev.getPrecioBase()+(ev.getPrecioBase()*(place.getPorcentaje()/100))) +"por persona.");
	                            System.out.println("1. Pago por saldo");
	                            System.out.println("2. Pago por terceros");
	                            System.out.println("0. Volver");
	                            
	                            int pago = Integer.parseInt(System.console().readLine());
	                            
	                            if (pago == 1) {
	                                TiqueteDeluxe tiqDel = new TiqueteDeluxe(ev.getPrecioBase(), place, ev, cli, ev.beneficios);

	                                if (cli.getSaldo() < tiqDel.getCosto()) {
	                                    System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                    break;
	                                }

	                                cli.getTiqVi().add(tiqDel);
	                                ev.getTiqRes().add(tiqDel);
	                                cli.setSaldo(cli.getSaldo() - tiqDel.getCosto());
	                                System.out.println("Tiquetes generados correctamente");
	                                System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                            }
	                            
	                            else if (pago == 2) {
	                                TiqueteDeluxe tiqDel = new TiqueteDeluxe(ev.getPrecioBase(), place, ev, cli, ev.beneficios);
	                                cli.getTiqVi().add(tiqDel);
	                                ev.getTiqRes().add(tiqDel);
	                                System.out.println("Tiquetes generados correctamente");
	                                System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                            }
	                            
	                            else if (pago == 0) {
	                                System.out.println("Volviendo al menú anterior...");
	                            }
	                            
	                            else {
	                                System.out.println("Respuesta incorrecta, vuelve a intentarlo");
	                            }
	                            break;
	                            
	                        case 0:
	                            System.out.println("Volviendo al menú anterior...");
	                            break;
	                            
	                        default:
	                            System.out.println("Opción incorrecta, intentar de nuevo");
	                            break;
	                        }   
	                    }
	                    break;
	                case 0:
	                    System.out.println("Volviendo al menú principal...");
	                    break;

	                default:
	                    System.out.println("Opción incorrecta, intentar de nuevo");
	                    break;
	                }
	            }
	            break;
	            
	        case 2:
	            System.out.println("Tu saldo actual es: " + cli.getSaldo());
	            break;
	            
	        case 3:
	            System.out.println("¿Que tiquete quieres transferir?");
	            
	            System.out.println("Actualmente hay " + cli.getTiqVi().size()+ " tiquetes vigentes.");

                int opp = -1;
                int actual = 0;
                while (opp != 0 && actual < cli.getTiqVi().size()) {
                	


                    Tiquete tiq = cli.getTiqVi().get(actual);
                    
                    if (tiq.getTipo().equals("Deluxe")){actual++; break;}
                    
                    
                    else {
                    System.out.println(".....................................................");
                    System.out.println("Evento: " + tiq.getEvento().getNombre());
                    System.out.println("Tipo: " + tiq.getTipo());
                    System.out.println("Individuos: " + tiq.getIndividuos());
                    System.out.println("Identificador: " + tiq.getIdentificador());
                    System.out.println(".....................................................");

                    System.out.println("1. Siguiente");
                    System.out.println("2. Transferir");
                    System.out.println("0. Salir");

                    String in = System.console().readLine();
                    opp = Integer.parseInt(in);
                    
                    
                    switch (opp) {
                    
                    case 1:
                    	
                    	actual++;
                    	break;
                    
                    case 2:
                    	
                    	System.out.println("Inserta el Log-In de la persona que va a recibirlo: ");
                    	
                    	String log = System.console().readLine();
                    	
                    	
                    	for (Cliente c: this.clientes) {
                    		
                    		if (c.getLog().equals(log)) {
                    			
                    			c.getTiqVi().add(tiq);
                    			
                    			cli.getTiqVi().remove(tiq);
                    			
                    		}
                    		
                    	}
                    	
                    	System.out.println("Transferencia exitosa");
                    
                    case 0:
                    	
                    	break;
                    
                    }
                    
                    }
	            	
	            	
	            }
	            
	            
                if (actual >= this.pendientes.size()) {
                    System.out.println("No hay más tiquetes por revisar.");
                }
                break;
	            
	        case 0:
	            System.out.println("Saliendo del menú...");
	            break;
	            
	        default:
	            System.out.println("Opción inválida, intenta nuevamente.");
	            break;
	        }
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
				
				String del ="";
				
				System.out.println("Inserta los beneficios del paquete deluxe (Si tu venue es numerado solo continúa)");
				
				del = System.console().readLine();
				
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
				
				Evento saturnalia = new Evento(org,capp,nomm,tip,fecha,ini,fin,prix,ven,del);
				
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
				
				menuCliente(org);
				
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
	    
	    app.cargarTodo();
	    
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
