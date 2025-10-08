package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteDeluxe extends Tiquete{

	TiqueteDeluxe(String tipo, int individuos, double costo, Localidad localidad, Evento evento, Cliente cliente) {
		super("Deluxe", 1, costo, localidad, evento, cliente);
		// TODO Auto-generated constructor stub
	}

}
