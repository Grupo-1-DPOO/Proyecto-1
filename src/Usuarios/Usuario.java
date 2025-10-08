package Usuarios;

public class Usuario {
	
	String logIn;
	
	String contraseña;
	
	String tipo;
	
	
	public Usuario(String log, String cont, String tip){
		
		this.logIn=log;
		
		this.contraseña=cont;
		
		this.tipo=tip;
		
	}
	
	public String getLog() {
		
		return this.logIn;
	}

}
