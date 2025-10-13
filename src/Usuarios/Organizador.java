package Usuarios;

import java.util.ArrayList;

import eventos.Evento;

public class Organizador extends Cliente{
	private ArrayList<String> eventosProx;
	
	private ArrayList<String> eventosPas;
	
	public Organizador(String log, String cont) {
		super(log, cont);
		// TODO Auto-generated constructor stub
		this.tipo="Organizador";
		
		this.eventosProx= new ArrayList<String>();
		
		this.eventosPas= new ArrayList<String>();
		
	}
	
	public ArrayList<String> getEventosProx(){
		
		return this.eventosProx;
		
	}
	
	public ArrayList<String> getEventosPas(){
		
		return this.eventosPas;
		
	}
	
	
	
}
