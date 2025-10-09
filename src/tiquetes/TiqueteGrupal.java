package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteGrupal extends Tiquete{

	public TiqueteGrupal(String tipo, int individuos, Localidad localidad, Evento evento, Cliente cliente) {
		
		super(tipo, individuos, (evento.getPrecioBase()+(localidad.getPorcentaje()*evento.getPrecioBase()))*individuos, localidad, evento, cliente);
		
	}
	
}
