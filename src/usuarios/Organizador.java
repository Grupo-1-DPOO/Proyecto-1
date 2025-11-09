package usuarios;

import java.util.ArrayList;

public class Organizador extends Cliente {
	
	private ArrayList<String> eventosProx;
	private ArrayList<String> eventosPas;
	
	public Organizador() {
	    super();
	    this.tipo = "Organizador";
	    this.eventosProx = new ArrayList<>();
	    this.eventosPas = new ArrayList<>();
	}

	
	public Organizador(String log, String cont) {
		super(log, cont);
		this.tipo = "Organizador";
		this.eventosProx = new ArrayList<String>();
		this.eventosPas = new ArrayList<String>();
	}
	
	public ArrayList<String> getEventosProx() {
		return this.eventosProx;
	}
	
	public ArrayList<String> getEventosPas() {
		return this.eventosPas;
	}
	
	@Override
	public String imprimir() {
	    StringBuilder sb = new StringBuilder();

	    sb.append(this.getLog()).append(",");
	    sb.append(this.getContraseña()).append(",");
	    sb.append(this.getTipo()).append(",");
	    sb.append(this.getSaldo()).append(",");
	    

	    for (int i = 0; i < this.getTiqVi().size(); i++) {
	        sb.append(this.getTiqVi().get(i).getIdentificador());
	        if (i < this.getTiqVi().size() - 1) sb.append(";");
	    }
	    
	    sb.append("|");
	    
	    // Tiquetes no vigentes
	    for (int i = 0; i < this.getTiqNoVi().size(); i++) {
	        sb.append(this.getTiqNoVi().get(i).getIdentificador());
	        if (i < this.getTiqNoVi().size() - 1) sb.append(";");
	    }

	    sb.append("|");


	    for (int i = 0; i < this.eventosProx.size(); i++) {
	        sb.append(this.eventosProx.get(i));
	        if (i < this.eventosProx.size() - 1) sb.append(";");
	    }

	    sb.append("|");


	    for (int i = 0; i < this.eventosPas.size(); i++) {
	        sb.append(this.eventosPas.get(i));
	        if (i < this.eventosPas.size() - 1) sb.append(";");
	    }

	    return sb.toString();
	}

	@Override
	public String toString() {
	    return imprimir();
	}
}
