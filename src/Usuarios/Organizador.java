package Usuarios;

import java.util.ArrayList;

import eventos.Evento;

public class Organizador extends Cliente{
	private ArrayList<Evento> eventosProx;
	
	private ArrayList<Evento> eventosPas;
	
	Organizador(String log, String cont) {
		super(log, cont);
		// TODO Auto-generated constructor stub
		this.tipo="Organizador";
		
		this.eventosProx= new ArrayList<Evento>();
		
		this.eventosPas= new ArrayList<Evento>();
		
	}
}
