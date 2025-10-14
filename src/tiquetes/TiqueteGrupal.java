package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteGrupal extends Tiquete{

	public TiqueteGrupal(String tipo, int individuos, Localidad localidad, Evento evento, Cliente cliente) {
		
		super(tipo, individuos, (evento.getPrecioBase()+(localidad.getPorcentaje()*evento.getPrecioBase()))*individuos, localidad, evento, cliente);
		
	}

	@Override
	public String imprimir() {
	    return this.getIdentificador() + "," + this.getTipo() + "," + this.getIndividuos() + "," + this.getCosto() + "," + this.getLocalidad().getNombre() + "," + this.getEvento().getNombre() + "," + this.getFecha() + "," + this.getHora() + "," + this.getCliente().getLog();
	}
	
}
