package Usuarios;

public abstract class Usuario {
	
	protected String logIn;
	protected String contraseña;
	protected String tipo;
	
	public Usuario() {
	}

	
	public Usuario(String log, String cont, String tip){
		this.logIn = log;
		this.contraseña = cont;
		this.tipo = tip;
	}
	
	public String getLog() {
		return this.logIn;
	}
	
	public void setLog(String log) {
		this.logIn = log;
	}
	
	public String getContraseña() {
		return this.contraseña;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
	    // Formato: logIn,contraseña,tipo
	    return this.logIn + "," + this.contraseña + "," + this.tipo;
	}
}
