package tiquetes;

import eventos.Evento;
import eventos.Localidad;
import eventos.Venue;
import eventos.VenueNumerado;
import usuarios.Cliente;

public class TiqueteNumerado extends Tiquete {
	
	private int numero = -1;

	// Constructor principal
	public TiqueteNumerado(double costo, Localidad localidad, Evento evento, Cliente cliente) {
		super("Numerado", 1, costo, localidad, evento, cliente);
		
		Venue ven = evento.getVenue();
		
		if (ven instanceof VenueNumerado venNum) {
			for (int i = 0; i < venNum.disponibles.length; i++) {
				if (venNum.disponibles[i] == null) {
					this.numero = i;
					venNum.disponibles[i] = this;
					break;
				}
			}
		}
	}

	
	public TiqueteNumerado() {
	    super(null, 0, 0.0, null, null, null);
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String imprimir() {
	    return this.getIdentificador() + "," +
	           this.getTipo() + "," +
	           this.getIndividuos() + "," +
	           this.getCosto() + "," +
	           (this.getLocalidad() != null ? this.getLocalidad().getNombre() : "N/A") + "," +
	           (this.getEvento() != null ? this.getEvento().getNombre() : "N/A") + "," +
	           this.getFecha() + "," +
	           this.getHora() + "," +
	           (this.getCliente() != null ? this.getCliente().getLog() : "N/A") + "," +
	           this.getNumero();
	}

	@Override
	public String toString() {
		return imprimir();
	}
}
