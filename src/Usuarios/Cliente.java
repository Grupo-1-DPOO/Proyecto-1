package Usuarios;

import java.util.ArrayList;


public class Cliente extends Usuario {
	
	ArrayList<String> tiqVi;    // Tiquetes vigentes
	ArrayList<String> tiqNoVi;  // Tiquetes no vigentes
	double saldo;

	public Cliente(String log, String cont) {
		super(log, cont, "Cliente");
		this.tiqVi = new ArrayList<String>();
		this.tiqNoVi = new ArrayList<String>();
		this.saldo = 0;
	}
	
	// Getters
	public ArrayList<String> getTiqVi() {
		return tiqVi;
	}
	
	public ArrayList<String> getTiqNoVi() {
		return tiqNoVi;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	// Setters
	public void setTiqVi(ArrayList<String> tiqVi) {
		this.tiqVi = tiqVi;
	}
	
	public void setTiqNoVi(ArrayList<String> tiqNoVi) {
		this.tiqNoVi = tiqNoVi;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}
