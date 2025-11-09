package usuarios;

public class Administrador extends Usuario {
	
	public Administrador() {
        super();  // Llama al constructor vacío de Usuario
        this.tipo = "Administrador";
    }

	public Administrador(String log, String cont) {
		super(log, cont, "Administrador");
	}

	@Override
	public String toString() {
	    return this.getLog() + "," + this.getContraseña() + "," + this.getTipo();
	}
}
