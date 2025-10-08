package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;

public class Tiquete {
	
	private String identificador;
	
	private String tipo;
	
	private int individuos;
	
	private double costo;
	
	private String localidad;
	
	private Evento evento;
	
	private String fecha;
	
	private String hora;
	
	private Cliente cliente;
	
	Tiquete(String tipo, int individuos, double costo, String localidad, Evento evento, String fecha, String hora, Cliente cliente){
		
		this.tipo=tipo;
		
		this.individuos=individuos;
		
		this.costo=costo;
		
		this.localidad=localidad;
		
		this.evento=evento;
		
		this.fecha=fecha;
		
		this.hora=hora;
		
		this.cliente=cliente;
		
		int num=((int)(Math.random() * 1001));
		
		this.identificador=this.cliente.getLog()+num;
		
	}
	

}
