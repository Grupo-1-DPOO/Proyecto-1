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
}

