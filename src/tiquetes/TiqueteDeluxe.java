package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteDeluxe extends Tiquete {
	
	String beneficios;

	public TiqueteDeluxe(String tipo, int individuos, double costo, Localidad localidad, Evento evento, Cliente cliente, String beneficios) {
		super("Deluxe", 1, costo, localidad, evento, cliente);
		this.beneficios = beneficios;
	}
	
	public String getBeneficios() {
		return this.beneficios;
	}
	
	public void setBeneficios(String x) {
		this.beneficios = x;
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
	           this.getBeneficios();
	}

	@Override
	public String toString() {
		return imprimir();
	}
}
