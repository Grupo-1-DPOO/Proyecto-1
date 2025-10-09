package Usuarios;

import java.util.ArrayList;

import eventos.Evento;

public class Organizador extends Cliente{
	private ArrayList<Evento> eventosProx;
	
	private ArrayList<Evento> eventosPas;
	
	public Organizador(String log, String cont) {
		super(log, cont);
		// TODO Auto-generated constructor stub
		this.tipo="Organizador";
		
		this.eventosProx= new ArrayList<Evento>();
		
		this.eventosPas= new ArrayList<Evento>();
		
	}
	
	public ArrayList<Evento> getEventosProx(){
		
		return this.eventosProx;
		
	}
	
	public ArrayList<Evento> getEventosPas(){
		
		return this.eventosPas;
		
	}
	
	
	
}
