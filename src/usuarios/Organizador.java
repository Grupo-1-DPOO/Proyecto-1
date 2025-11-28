package usuarios;

import java.util.ArrayList;

import consola.Aplicacion;
import eventos.Evento;
import eventos.Venue;
import tiquetes.Tiquete;

public class Organizador extends Cliente {
	
	private ArrayList<String> eventosProx;
	private ArrayList<String> eventosPas;
	
	public Organizador() {
	    super();
	    this.tipo = "Organizador";
	    this.eventosProx = new ArrayList<>();
	    this.eventosPas = new ArrayList<>();
	}

	
	public Organizador(String log, String cont) {
		super(log, cont);
		this.tipo = "Organizador";
		this.eventosProx = new ArrayList<String>();
		this.eventosPas = new ArrayList<String>();
	}
	
	public ArrayList<String> getEventosProx() {
		return this.eventosProx;
	}
	
	public ArrayList<String> getEventosPas() {
		return this.eventosPas;
	}
	
	@Override
	public String imprimir() {
	    StringBuilder sb = new StringBuilder();

	    sb.append(this.getLog()).append(",");
	    sb.append(this.getContraseña()).append(",");
	    sb.append(this.getTipo()).append(",");
	    sb.append(this.getSaldo()).append(",");
	    

	    for (int i = 0; i < this.getTiqVi().size(); i++) {
	        sb.append(this.getTiqVi().get(i).getIdentificador());
	        if (i < this.getTiqVi().size() - 1) sb.append(";");
	    }
	    
	    sb.append("|");
	    
	    // Tiquetes no vigentes
	    for (int i = 0; i < this.getTiqNoVi().size(); i++) {
	        sb.append(this.getTiqNoVi().get(i).getIdentificador());
	        if (i < this.getTiqNoVi().size() - 1) sb.append(";");
	    }

	    sb.append("|");


	    for (int i = 0; i < this.eventosProx.size(); i++) {
	        sb.append(this.eventosProx.get(i));
	        if (i < this.eventosProx.size() - 1) sb.append(";");
	    }

	    sb.append("|");


	    for (int i = 0; i < this.eventosPas.size(); i++) {
	        sb.append(this.eventosPas.get(i));
	        if (i < this.eventosPas.size() - 1) sb.append(";");
	    }

	    return sb.toString();
	}
	
public void menuOrg(Aplicacion app) {
		
		System.out.println("Bienvenido al menú para organizadores");
		
		
		
		 int opcion = -1;
		 while (opcion != 0) {
			
			System.out.println("1. Crear un evento");
			
			System.out.println("2. Crear un venue");
			
			System.out.println("3. Acceder a tu menú de cliente");
			
			System.out.println("4. Revisar eventos pasados");
			
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
				
				for (Venue x: app.venues) {
					
					if(x.getNombre().equals(nomen)) {
						
						ven=x;
						
						break;		
					}
	
				}
				
				if (ven==null) {
					System.out.println("No se encontró un venue con ese nombre. Regresando al menú anterior...");
					break;
				}
				
				Evento saturnalia = new Evento(this,capp,nomm,tip,fecha,ini,fin,prix,ven,del);
				
				saturnalia.agregarLocalidades();
				
				app.pendientes.add(saturnalia);
				
				app.guardarEvento("pendientes.json", saturnalia);
				
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
				
				app.venues.add(nuovo);
				app.guardarVenue("venues.json", nuovo);
				System.out.println("Venue creado exitosamente y registrado en el sistema.");
                break;
				
			case 3:
				
				this.menuCliente(app);
				
				break;
				
			case 4:
				
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
                            addit += tiquete.getCosto() / ev.tasa;
                        }
                    }
                }

                System.out.println("Ganancias totales: " + addit);
                break;
				
			case 0:
				
				break;
			
			}
			
		 
		 }
        
        
        
        
        
		
	}
	


	@Override
	public String toString() {
	    return imprimir();
	}
}
