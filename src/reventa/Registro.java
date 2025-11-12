package reventa;

import java.time.LocalDateTime;


public class Registro {
    private final LocalDateTime fechaHora;
	private final String descripcion;
	
	public Registro(String descripcion) {
        this.fechaHora = LocalDateTime.now();
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return fechaHora + " | " + descripcion;
    

}
}