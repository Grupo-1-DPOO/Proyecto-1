package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;
import eventos.Venue;
import eventos.VenueNumerado;

public class TiqueteNumerado extends Tiquete{
	
	int numero;

	public TiqueteNumerado(String tipo, int individuos, double costo, Localidad localidad, Evento evento, Cliente cliente) {
		super("numerado", individuos, costo, localidad, evento, cliente);
		// TODO Auto-generated constructor stub
		
		Venue ven=evento.getVenue();
		
		if (ven instanceof VenueNumerado) {
			
			VenueNumerado venNum=(VenueNumerado) ven;
			
			for(int i=0;i<venNum.disponibles.length;i++) {
				
				if (venNum.disponibles[i] == null) {
					
					this.numero=i;
					
					venNum.disponibles[i]=this;
					
					break;
					
				}
				
			}
			
		}
	}

}
