package usuarios;

import java.util.ArrayList;

import consola.Aplicacion;
import eventos.Evento;
import eventos.Localidad;
import eventos.VenueNumerado;
import reventa.Contraoferta;
import reventa.Oferta;
import tiquetes.Tiquete;
import tiquetes.TiqueteBasico;
import tiquetes.TiqueteDeluxe;
import tiquetes.TiqueteGrupal;
import tiquetes.TiqueteNumerado;

public class Cliente extends Usuario {
	
	private ArrayList<Tiquete> tiqVi;    // Tiquetes vigentes
	private ArrayList<Tiquete> tiqNoVi;  // Tiquetes no vigentes
	private double saldo;
	
	public Cliente() {
		super();
		this.tipo = "Cliente";
		this.tiqVi = new ArrayList<>();
		this.tiqNoVi = new ArrayList<>();
		this.saldo = 0.0;
	}

	public Cliente(String log, String cont) {
		super(log, cont, "Cliente");
		this.tiqVi = new ArrayList<Tiquete>();
		this.tiqNoVi = new ArrayList<Tiquete>();
		this.saldo = 0;
	}
		
	// Getters
	public ArrayList<Tiquete> getTiqVi() {
		return tiqVi;
	}
	
	public ArrayList<Tiquete> getTiqNoVi() {
		return tiqNoVi;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	// Setters
	public void setTiqVi(ArrayList<Tiquete> tiqVi) {
		this.tiqVi = tiqVi;
	}
	
	public void setTiqNoVi(ArrayList<Tiquete> tiqNoVi) {
		this.tiqNoVi = tiqNoVi;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	@Override
	public String toString() {
	    return imprimir();
	}

	public String imprimir() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(this.getLog()).append(",");
	    sb.append(this.getContraseña()).append(",");
	    sb.append(this.getTipo()).append(",");
	    sb.append(this.getSaldo()).append(",");
	    
	    for (int i = 0; i < tiqVi.size(); i++) {
	        sb.append(tiqVi.get(i).getIdentificador());
	        if (i < tiqVi.size() - 1) sb.append(";");
	    }
	    
	    sb.append("|");
	    
	    for (int i = 0; i < tiqNoVi.size(); i++) {
	        sb.append(tiqNoVi.get(i).getIdentificador());
	        if (i < tiqNoVi.size() - 1) sb.append(";");
	    }
	    
	    return sb.toString();
	}
	public void acreditarSaldo(double monto) {
		if (monto < 0) throw new IllegalArgumentException("Monto negativo no permitido");
		this.saldo += monto;
	}
	
	public void debitarSaldo(double monto) {
		if (monto < 0) throw new IllegalArgumentException("Monto negativo no permitido");
		if (this.saldo < monto) throw new IllegalStateException("Saldo insuficiente");
		this.saldo -= monto;
	}
	
	public void menuCliente(Aplicacion app) {
	    System.out.println("Bienvenido al menú para clientes!");
	    
	    int opcion = -1;
	    while (opcion != 0) {

	        System.out.println("1. Buscar eventos");
	        System.out.println("2. Consultar Saldo");
	        System.out.println("3. Transferir tiquete");
	        System.out.println("4. Buscar tiquete");
	        System.out.println("5. Acceder al Marketplace");
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

	                                    if (this.getSaldo() < costoTotal) {
	                                        System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                        break;
	                                    }

	                                    for (int i = 0; i < peuple; i++) {
	                                        TiqueteNumerado tiqNum = new TiqueteNumerado(ev.getPrecioBase(), lieu, ev, this);
	                                        this.getTiqVi().add(tiqNum);
	                                        ev.getTiqRes().add(tiqNum);
	                                        this.setSaldo(this.getSaldo() - tiqNum.getCosto());
	                                    }
	                                    System.out.println("Tiquetes generados correctamente");
	                                    System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                                }
	                                
	                                else if (pago == 2) {
	                                    
	                                    for (int i = 0; i < peuple; i++) {
	                                        TiqueteNumerado tiqNum = new TiqueteNumerado(ev.getPrecioBase(), lieu, ev, this);
	                                        this.getTiqVi().add(tiqNum);
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
	                                        TiqueteGrupal tiq = new TiqueteGrupal(peuple, lieu, ev, this);

	                                        if (this.getSaldo() < tiq.getCosto()) {
	                                            System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                            break;
	                                        }

	                                        this.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                        this.setSaldo(this.getSaldo() - tiq.getCosto());
	                                    } else {
	                                        TiqueteBasico tiq = new TiqueteBasico(lieu, ev, this);

	                                        if (this.getSaldo() < tiq.getCosto()) {
	                                            System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                            break;
	                                        }

	                                        this.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                        this.setSaldo(this.getSaldo() - tiq.getCosto());
	                                    }
	                                    
	                                    System.out.println("Tiquetes generados correctamente");
	                                    System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                                }
	                                
	                                else if (pago == 2) {
	                                    
	                                    if (peuple > 1) {
	                                        TiqueteGrupal tiq = new TiqueteGrupal(peuple, lieu, ev, this);
	                                        this.getTiqVi().add(tiq);
	                                        ev.getTiqRes().add(tiq);
	                                    } else {
	                                        TiqueteBasico tiq = new TiqueteBasico(lieu, ev, this);
	                                        this.getTiqVi().add(tiq);
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
	                                TiqueteDeluxe tiqDel = new TiqueteDeluxe(ev.getPrecioBase(), place, ev, this, ev.beneficios);

	                                if (this.getSaldo() < tiqDel.getCosto()) {
	                                    System.out.println("Saldo insuficiente para realizar la compra. Operación cancelada.");
	                                    break;
	                                }

	                                this.getTiqVi().add(tiqDel);
	                                ev.getTiqRes().add(tiqDel);
	                                this.setSaldo(this.getSaldo() - tiqDel.getCosto());
	                                System.out.println("Tiquetes generados correctamente");
	                                System.out.println("Ahora puedes acceder desde el menú y usar tus tiquetes cuando los necesites");
	                            }
	                            
	                            else if (pago == 2) {
	                                TiqueteDeluxe tiqDel = new TiqueteDeluxe(ev.getPrecioBase(), place, ev, this, ev.beneficios);
	                                this.getTiqVi().add(tiqDel);
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
	            System.out.println("Tu saldo actual es: " + this.getSaldo());
	            break;
	            
	        case 3:
	            System.out.println("¿Que tiquete quieres transferir?");
	            
	            System.out.println("Actualmente hay " + this.getTiqVi().size()+ " tiquetes vigentes.");

                int opp = -1;
                int actual = 0;
                while (opp != 0 && actual < this.getTiqVi().size()) {
                	


                    Tiquete tiq = this.getTiqVi().get(actual);
                    
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
                    	
                    	
                    	for (Cliente c: app.clientes) {
                    		
                    		if (c.getLog().equals(log)) {
                    			
                    			c.getTiqVi().add(tiq);
                    			
                    			this.getTiqVi().remove(tiq);
                    			
                    		}
                    		
                    	}
                    	
                    	System.out.println("Transferencia exitosa");
                    	
                    	break;
                    
                    case 0:
                    	
                    	break;
                    
                    }
                    
                    }
	            	
	            	
	            }
	            
	            
                if (actual >= app.pendientes.size()) {
                    System.out.println("No hay más tiquetes por revisar.");
                }
                break;
                
	        case 4:
	        	
	        	System.out.println("Inserta el nombre del evento de tu tiquete:");
	        	
	        	String nom = System.console().readLine();
	        	
	        	Tiquete boleto= null;
	        	
	        	for (Tiquete bol: this.getTiqVi()) {
	        		
	        		if(bol.getEvento().getNombre().equals(nom)) {
	        			
	        			boleto=bol;
	        			
	        			break;
	        			
	        		}
	        	}
	        	
	        	System.out.println(".....................................................");
                System.out.println("Evento: " + boleto.getEvento().getNombre());
                System.out.println("Tipo: " + boleto.getTipo());
                System.out.println("Individuos: " + boleto.getIndividuos());
                System.out.println("Identificador: " + boleto.getIdentificador());
                System.out.println(".....................................................");

                System.out.println("1. Usar");
                System.out.println("0. Salir");
                
                int us = Integer.parseInt(System.console().readLine());
                
                if (us==1) {
                	
                	this.getTiqVi().remove(boleto);
                	this.getTiqNoVi().add(boleto);
                	boleto.getEvento().getTiqPros().add(boleto);
                	boleto.getEvento().getTiqRes().remove(boleto);
                	
                	System.out.println("Tiquete registrado como usado, ya pedes acceder");
                }
                
                else if (us==0) {System.out.println("Saliendo...");
                
                break;}
                
                else {System.out.println("Opción incorrecta");
                
                break;}
	        	
	        case 5:
	        	
	        	int dec = -1;
	            while (dec != 0) {
	            	
	            	System.out.println("1. Revisar ofertas y contraofertar");
	    	        System.out.println("2. Revisar tus ofertas");
	    	        System.out.println("3. Publicar una oferta");
	    	        System.out.println("0. Salir");
	    	        
	    	        String inn = System.console().readLine();
	    	        try {
	    	            opcion = Integer.parseInt(inn);
	    	        } catch (Exception e) {
	    	            System.out.println("Entrada inválida, intenta de nuevo.");
	    	            continue;
	    	        }
	    	        
	    	        switch(dec) {
	    	        
	    	        case 1:

	    	            if (app.marketplace.ofertas.size() == 0) {
	    	                System.out.println("Aún no hay ofertas disponibles");
	    	                break;} 
	    	            
	    	            else {
	    	                ArrayList<String> llaves = new ArrayList<>(app.marketplace.ofertas.keySet());
	    	                int ac = 0;
	    	                int deci = -1;

	    	                while (deci != 0 && llaves.size() > 0) {

	    	                    if (ac >= llaves.size()) ac = 0;

	    	                    String llave = llaves.get(ac);
	    	                    Oferta off = app.marketplace.ofertas.get(llave);

	    	                    System.out.println(".....................................................");
	    	                    System.out.println("Id: " + off.getIdOferta());
	    	                    System.out.println("Precio: " + off.getPrecio());
	    	                    System.out.println("Vendedor: " + off.getVendedor().getLog());
	    	                    System.out.println("Evento: " + off.getTiquete().getEvento());
	    	                    System.out.println("Fecha de publicación: " + off.getFechaPublicacion());
	    	                    System.out.println(".....................................................");

	    	                    System.out.println("1. Siguiente oferta");
	    	                    System.out.println("2. Contraofertar");
	    	                    System.out.println("0. Salir");

	    	                    String in = System.console().readLine();
	    	                    int op=-1;
	    	                    try {
	    	                        op = Integer.parseInt(in);
	    	                    } catch (Exception e) {
	    	                        System.out.println("Entrada inválida, intenta de nuevo.");
	    	                        continue;
	    	                    }

	    	                    switch (op) {

	    	                    case 1:
	    	                        ac++;
	    	                        break;

	    	                    case 2:
	    	                    	
	    	                    	for (Contraoferta cot: off.getContraofertas()) {
	    	                    		
	    	                    		System.out.println("Comprador: "+cot.getComprador().getLog());
	    	                    		
	    	                    		System.out.println("Precio ofrecido: "+cot.getNuevoPrecio());
	    	                    		
	    	                    		System.out.println("Fecha de emisón: " +cot.getFecha());
	    	                    		
	    	                    	}
	    	                    	
	    	                    	
	    	                    	System.out.println("Inserta tu precio: ");
	    	                    	
	    	                    	double precio = Integer.parseInt(System.console().readLine());
	    	                        
	    	                    	Contraoferta cont =new Contraoferta(this, precio);
	    	                    	
	    	                    	off.getContraofertas().add(cont);
	    	                    	
	    	                    	System.out.println("Contraoferta publicada, puedes revisar para ver si es aceptada");
	    	                    	
	    	                        break;

	    	                    case 0:
	    	                        deci = 0;
	    	                        System.out.println("Saliendo de la revisión de ofertas...");
	    	                        break;

	    	                    default:
	    	                        System.out.println("Opción inválida.");
	    	                        break;
	    	                    }
	    	                }
	    	            }
	    	            break;
	    	        
	    	        case 2:

	    	            if (app.marketplace.ofertas.size() == 0) {
	    	                System.out.println("Aún no hay ofertas disponibles");
	    	                break;} 
	    	            
	    	            else {
	    	                ArrayList<String> llaves = new ArrayList<>(app.marketplace.ofertas.keySet());
	    	                int ac = 0;
	    	                int deci = -1;

	    	                while (deci != 0 && llaves.size() > 0) {
	    	                	
	    	                	

	    	                    if (ac >= llaves.size()) ac = 0;

	    	                    String llave = llaves.get(ac);
	    	                    Oferta off = app.marketplace.ofertas.get(llave);
	    	                    
	    	                    if(off.getVendedor().equals(this)) {
	    	                    System.out.println(".....................................................");
	    	                    System.out.println("Id: " + off.getIdOferta());
	    	                    System.out.println("Precio: " + off.getPrecio());
	    	                    System.out.println("Vendedor: " + off.getVendedor().getLog());
	    	                    System.out.println("Evento: " + off.getTiquete().getEvento());
	    	                    System.out.println("Fecha de publicación: " + off.getFechaPublicacion());
	    	                    System.out.println(".....................................................");

	    	                    System.out.println("1. Siguiente");
	    	                    System.out.println("2. Ver contraofertas");
	    	                    System.out.println("0. Salir");

	    	                    String in = System.console().readLine();
	    	                    int op=-1;
	    	                    try {
	    	                        op = Integer.parseInt(in);
	    	                    } catch (Exception e) {
	    	                        System.out.println("Entrada inválida, intenta de nuevo.");
	    	                        continue;
	    	                    }

	    	                    switch (op) {

	    	                    case 1:
	    	                        ac++;
	    	                        break;

	    	                    case 2:
	    	                    	
	    	                    	for (Contraoferta cot: off.getContraofertas()) {
	    	                    		
	    	                    		System.out.println("..................................................");
	    	                    		
	    	                    		System.out.println("Comprador: "+cot.getComprador().getLog());
	    	                    		
	    	                    		System.out.println("Precio ofrecido: "+cot.getNuevoPrecio());
	    	                    		
	    	                    		System.out.println("Fecha de emisón: " +cot.getFecha());
	    	                    		
	    	                    		System.out.println("..................................................");
	    	                    		
	    	                    	}
	    	                    	
	    	                    	int deca = -1;

	    	    	                while (deca != 0) {
	    	    	                	
	    	    	                	System.out.println("1. ¿Quieres aceptar alguna?");
	    	    	                    System.out.println("0. Volver");
	    	                    	
	    	                    	switch(deca) {
	    	                    	
	    	                    	case 1:
	    	                    		
	    	                    		System.out.println("Inserta el log in del comprador ganador: ");
		    	                    	
		    	                    	String respuesta = (System.console().readLine());
		    	                    	
		    	                    	Cliente ganador=null;
		    	                    	
		    	                    	for (Contraoferta contr: off.getContraofertas()) {
		    	                    		
		    	                    		if (contr.getComprador().getLog().equals(respuesta)) {
		    	                    			
		    	                    			ganador=contr.getComprador();
		    	                  
		    	                    			
		    	                    		}
		    	                    		
		    	                    		else {
		    	                    			
		    	                    			continue;
		    	                    			
		    	                    		}
		    	                    	}
		    	                    	
		    	                    	
		    	                    	
		    	                    	this.getTiqVi().remove(off.getTiquete());
		    	                    	
		    	                    	ganador.getTiqVi().add(off.getTiquete());
		    	                    	
		    	                        
		    	                    	app.marketplace.ofertas.remove(off.getIdOferta());
		    	                    	
		    	                    	System.out.println("Tiquete transferido correctamente");
		    	                    
	    	                    		
	    	                    		break;
	    	                    		
	    	                    	case 0:
	    	                    		
	    	                    		System.out.println("Volviendo al menú anterior...");
	    	                    		break;
	    	                    	
	    	                    	}
	    	                    }

	    	                    case 0:
	    	                        deci = 0;
	    	                        System.out.println("Saliendo de la revisión de ofertas...");
	    	                        break;

	    	                    default:
	    	                        System.out.println("Opción inválida.");
	    	                        break;
	    	                    }
	    	                }
	    	                    
	    	                    else {continue;}
	    	            }
	    	                
	    	            }
	    	            break;
	    	        	
	    	        case 3:
	    	        	
	    	        	int index=0;
	    	        	
	    	        	for (Tiquete bole: this.getTiqVi()) {
                    		
	    	        		System.out.println(".....................................................");
	    	        		System.out.println("Opción: "+index);
	    	                System.out.println("Evento: " + bole.getEvento().getNombre());
	    	                System.out.println("Tipo: " + bole.getTipo());
	    	                System.out.println("Individuos: " + bole.getIndividuos());
	    	                System.out.println("Identificador: " + bole.getIdentificador());
	    	                System.out.println(".....................................................");
                    		
	    	                index++;
                    	}
	    	        	
	    	        	System.out.println("Escoje el tiquete que vayas a ofrecer (usa la opción numérica)");
	    	        	
	    	        	int decid = Integer.parseInt(System.console().readLine());
	    	        	
	    	        	Tiquete aOfrecer=this.getTiqVi().get(decid);
	    	        	
	    	        	System.out.println("Inserta el precio por el que lo quieres vender: ");
	    	        	
	    	        	double prix= Double.parseDouble((System.console().readLine()));
	    	        	
	    	        	app.marketplace.publicarOferta(this.getLog()+aOfrecer.getEvento().getNombre(), this, aOfrecer, prix);
	    	        	
	    	      
	    	        	
	    	        	
	    	        	break;
	    	        
	    	        case 0:
	    	        	System.out.println("Saliendo al menú anterior...");
	    	        	break;
	    	        	
	    	        default:
	    	        	System.out.println("Opción incorrecta, intentar de nuevo");
	                    break;
	    	        
	    	        
	    	        }
	    	        
	    	        
	            	
	            }
	        	 
	        	 
	        	
	        case 0:
	            System.out.println("Saliendo del menú...");
	            break;
	            
	        default:
	            System.out.println("Opción inválida, intenta nuevamente.");
	            break;
	        }
	    }
	}





	
	
}