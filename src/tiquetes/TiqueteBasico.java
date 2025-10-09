package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;


public class TiqueteBasico extends Tiquete implements Impresion{

	TiqueteBasico(String tipo, int individuos, double costo, String localidad, Evento evento, Cliente cliente) {
		super("Básico", 1, evento.getPrecioBase(), evento.localidadBasica, evento, cliente);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
	    return this.getIdentificador() + "," + this.getTipo() + "," + this.getIndividuos() + "," + this.getCosto() + "," + this.getLocalidad().getNombre() + "," + this.getEvento().getNombre() + "," + this.getFecha() + "," + this.getHora() + "," + this.getCliente().getLog();
	}
}
