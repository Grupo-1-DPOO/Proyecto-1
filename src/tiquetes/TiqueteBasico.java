package tiquetes;

import eventos.Evento;
import eventos.Localidad;
import usuarios.Cliente;

public class TiqueteBasico extends Tiquete {

	public TiqueteBasico(Localidad localidad, Evento evento, Cliente cliente) {
		super("Básico", 1, evento.getPrecioBase(), localidad, evento, cliente);
	}
	
	public TiqueteBasico() {
	    super(null, 0, 0.0, null, null, null);
	}

	@Override
	public String imprimir() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(getIdentificador()).append(',')
	      .append(getTipo()).append(',')
	      .append(getIndividuos()).append(',')
	      .append(getCosto()).append(',')
	      .append(getLocalidad() != null ? getLocalidad().getNombre() : "N/A").append(',')
	      .append(getEvento() != null ? getEvento().getNombre() : "N/A").append(',')
	      .append(getFecha()).append(',')
	      .append(getHora()).append(',')
	      .append(getCliente() != null ? getCliente().getLog() : "N/A");
	    return sb.toString();
	}


	@Override
	public String toString() {
		return imprimir();
	}
}
