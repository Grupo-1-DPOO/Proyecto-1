package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;
import eventos.Venue;
import eventos.VenueNumerado;

public class TiqueteNumerado extends Tiquete {
	
	private int numero;

	public TiqueteNumerado(double costo, Localidad localidad, Evento evento, Cliente cliente) {
		super("numerado", 1, costo, localidad, evento, cliente);
		
		Venue ven = evento.getVenue();
		
		if (ven instanceof VenueNumerado) {
			VenueNumerado venNum = (VenueNumerado) ven;

			for (int i = 0; i < venNum.disponibles.length; i++) {
				
				if (venNum.disponibles[i] == null) {
					this.numero = i;
					
					venNum.disponibles[i] = this;
					
					break;
				}
			}
		}
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	@Override
	public String imprimir() {
	    return this.getIdentificador() + "," +
	           this.getTipo() + "," +
	           this.getIndividuos() + "," +
	           this.getCosto() + "," +
	           this.getLocalidad().getNombre() + "," +
	           this.getEvento().getNombre() + "," +
	           this.getFecha() + "," +
	           this.getHora() + "," +
	           this.getCliente().getLog() + "," +
	           this.getNumero();
	}

	@Override
	public String toString() {
		return imprimir();
	}
}
