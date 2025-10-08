package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;


public class TiqueteBasico extends Tiquete{

	TiqueteBasico(String tipo, int individuos, double costo, String localidad, Evento evento, Cliente cliente) {
		super("Básico", 1, evento.getPrecioBase(), evento.localidadBasica, evento, cliente);
		// TODO Auto-generated constructor stub
	}
	
	
}
