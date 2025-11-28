package usuarios;

import consola.Aplicacion;
import eventos.Evento;
import tiquetes.Tiquete;

public class Administrador extends Usuario {
	
	public Administrador() {
        super();  // Llama al constructor vacío de Usuario
        this.tipo = "Administrador";
    }

	public Administrador(String log, String cont) {
		super(log, cont, "Administrador");
	}
	
	
	public void menuAdmin(Aplicacion app) {
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
	                System.out.println("Actualmente hay " + app.pendientes.size() + " eventos pendientes");

	                int opp = -1;
	                int actual = 0;
	                while (opp != 0 && actual < app.pendientes.size()) {

	                    Evento ev = app.pendientes.get(actual);

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

	                            app.eventosProx.add(ev);
	                            app.guardarEvento("eventosProx.json", ev);

	                            System.out.println("Comisión añadida. Evento aprobado.");
	                            actual++;
	                            break;

	                        case 2:
	                            app.cancelados.add(ev);
	                            app.guardarEvento("cancelados.json", ev);
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

	                if (actual >= app.pendientes.size()) {
	                    System.out.println("No hay más eventos pendientes por revisar.");
	                }
	                break;


	            case 2:
	                int opt = -1;
	                int act = 0;
	                while (opt != 0 && act < app.eventosProx.size()) {

	                    Evento ev = app.eventosProx.get(act);

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
	                            for (Cliente cli : app.clientes) {
	                                for (Tiquete tiq : cli.getTiqVi()) {
	                                    for (Tiquete id : ev.getTiqPros()) {
	                                        if (id.getIdentificador().equals(tiq.getIdentificador())) {
	                                            cli.setSaldo(cli.getSaldo() + (tiq.getCosto() - ev.tasa));
	                                        }
	                                    }
	                                }
	                            }
	                            app.cancelados.add(ev);
	                            app.guardarEvento("cancelados.json", ev);
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

	                            for (Organizador o : app.organizadores) {
	                                if (o.getLog().equals(cognomen)) {
	                                    nomen = o;
	                                    break;
	                                }
	                            }

	                            if (nomen == null) {
	                                System.out.println("No se encontró el organizador " + cognomen);
	                                break;
	                            }


	                            for (Evento ev : app.eventosPas) {
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
	                            
	                            for (Evento fe : app.eventosPas) {
	                                if (fe.getFecha().equals(jour)) {
	                                    res = fe;
	                                    break;
	                                }
	                            }
	                            
	                            if (res == null) {
	                                System.out.println("No se encontró la fecha " + jour);
	                                break;
	                            }
	                            
	                            
	                            for (Evento ev : app.eventosPas) {
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

	                            for (Evento x : app.eventosPas) {
	                                if (x.getNombre().equals(praenomen)) {
	                                    event = x;
	                                    break;
	                                }
	                            }

	                            if (event == null) {
	                                System.out.println("No se encontró el evento: " + praenomen);
	                                break;
	                            }


	                            for (Evento ev : app.eventosPas) {
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

	

	@Override
	public String toString() {
	    return this.getLog() + "," + this.getContraseña() + "," + this.getTipo();
	}
}
