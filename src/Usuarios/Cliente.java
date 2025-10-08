package Usuarios;

import java.util.ArrayList;

import tiquetes.Tiquete;

public class Cliente extends Usuario{
	
	ArrayList<Tiquete> tiqVi;//Tiquetes vigentes
	
	ArrayList<Tiquete> tiqNoVi; //Tiquetes no vigentes
	
	double saldo;

	Cliente(String log, String cont) {
		super(log, cont, "Cliente");
		
		this.tiqVi= new ArrayList<Tiquete>();
		
		this.tiqNoVi= new ArrayList<Tiquete>();
		
		this.saldo=0;
		
	}

}
