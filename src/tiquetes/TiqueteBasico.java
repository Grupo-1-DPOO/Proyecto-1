package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteBasico extends Tiquete {

	public TiqueteBasico(Localidad localidad, Evento evento, Cliente cliente) {
		super("Básico", 1, evento.getPrecioBase(), localidad, evento, cliente);
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
