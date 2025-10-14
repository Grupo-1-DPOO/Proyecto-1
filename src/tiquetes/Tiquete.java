package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public abstract class Tiquete {
	
	private String identificador;
	private String tipo;
	private int individuos;
	private double costo;
	private Localidad localidad;
	private Evento evento;
	private String fecha;
	private String hora;
	private Cliente cliente;
	
	public Tiquete(String tipo, int individuos, double costo, Localidad localidad, Evento evento, Cliente cliente){
		this.tipo = tipo;
		this.individuos = individuos;
		this.costo = costo;
		this.localidad = localidad;
		this.evento = evento;
		this.fecha = evento.getFecha();
		this.hora = evento.getHoraIni();
		this.cliente = cliente;
		int num = ((int)(Math.random() * 1001));
		this.identificador = this.cliente.getLog() + num;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getIndividuos() {
		return individuos;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public Localidad getLocalidad() {
		return localidad;
	}
	
	public Evento getEvento() {
		return evento;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getHora() {
		return hora;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setIndividuos(int individuos) {
		this.individuos = individuos;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public abstract String imprimir();
}

