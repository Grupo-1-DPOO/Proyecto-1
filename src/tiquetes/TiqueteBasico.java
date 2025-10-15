package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;

public class TiqueteBasico extends Tiquete {

	TiqueteBasico(String tipo, int individuos, double costo, String localidad, Evento evento, Cliente cliente) {
		super("Básico", 1, evento.getPrecioBase(), evento.localidadBasica, evento, cliente);
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
	           this.getCliente().getLog();
	}

	@Override
	public String toString() {
		return imprimir();
	}
}
