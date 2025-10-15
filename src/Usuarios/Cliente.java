package Usuarios;

import java.util.ArrayList;
import tiquetes.Tiquete;

public class Cliente extends Usuario {
	
	ArrayList<Tiquete> tiqVi;    // Tiquetes vigentes
	ArrayList<Tiquete> tiqNoVi;  // Tiquetes no vigentes
	double saldo;

	public Cliente(String log, String cont) {
		super(log, cont, "Cliente");
		this.tiqVi = new ArrayList<Tiquete>();
		this.tiqNoVi = new ArrayList<Tiquete>();
		this.saldo = 0;
	}
	
	// Getters
	public ArrayList<Tiquete> getTiqVi() {
		return tiqVi;
	}
	
	public ArrayList<Tiquete> getTiqNoVi() {
		return tiqNoVi;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	// Setters
	public void setTiqVi(ArrayList<Tiquete> tiqVi) {
		this.tiqVi = tiqVi;
	}
	
	public void setTiqNoVi(ArrayList<Tiquete> tiqNoVi) {
		this.tiqNoVi = tiqNoVi;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	@Override
	public String toString() {
	    return imprimir();
	}

	public String imprimir() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(this.getLog()).append(",");
	    sb.append(this.getContraseña()).append(",");
	    sb.append(this.getTipo()).append(",");
	    sb.append(this.getSaldo()).append(",");
	    
	    for (int i = 0; i < tiqVi.size(); i++) {
	        sb.append(tiqVi.get(i).getIdentificador());
	        if (i < tiqVi.size() - 1) sb.append(";");
	    }
	    
	    sb.append("|");
	    
	    for (int i = 0; i < tiqNoVi.size(); i++) {
	        sb.append(tiqNoVi.get(i).getIdentificador());
	        if (i < tiqNoVi.size() - 1) sb.append(";");
	    }
	    
	    return sb.toString();
	}
}
