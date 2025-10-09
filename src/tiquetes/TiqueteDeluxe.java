package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteDeluxe extends Tiquete{
	
	String beneficios;

	public TiqueteDeluxe(String tipo, int individuos, double costo, Localidad localidad, Evento evento, Cliente cliente, String beneficios) {
		super("Deluxe", 1, costo, localidad, evento, cliente);
		// TODO Auto-generated constructor stub
		this.beneficios=beneficios;
		
	}
	
	public String getBeneficios() {
		
		return this.beneficios;
		
	}
	
	public void setBeneficios(String x) {
		
		this.beneficios=x;
		
	}
}
