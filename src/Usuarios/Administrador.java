package Usuarios;

public class Administrador extends Usuario {

	public Administrador(String log, String cont) {
		super(log, cont, "Administrador");
	}

	@Override
	public String toString() {
	    return this.getLog() + "," + this.getContraseña() + "," + this.getTipo();
	}
}
