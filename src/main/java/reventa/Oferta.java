package reventa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tiquetes.Tiquete;
import usuarios.Cliente;

public class Oferta {
	private final String idOferta;
    private final Cliente vendedor;
    private final Tiquete tiquete;        
    private double precio;
    private boolean activa;
    private final LocalDateTime fechaPublicacion;
    private final List<contraOferta> contraOfertas;
    
    public Oferta(String idOferta, Cliente vendedor, Tiquete tiquete, double precio) {
        this.idOferta = idOferta;
        this.vendedor = vendedor;
        this.tiquete = tiquete;
        this.precio = precio;
        this.activa = true;
        this.fechaPublicacion = LocalDateTime.now();
        this.contraOfertas = new ArrayList<>();
    }
    public String getIdOferta() { return idOferta; }
    public Cliente getVendedor() { return vendedor; }
    public Tiquete getTiquete() { return tiquete; }
    public double getPrecio() { return precio; }
    public void setPrecio(double nuevo) { this.precio = nuevo; }
    public boolean isActiva() { return activa; }
    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public List<contraOferta> getContraofertas() { return contraOfertas; }

    public void cerrar() { this.activa = false; }

    public void agregarContraoferta(contraOferta c) {
        if (!activa) throw new IllegalStateException("La oferta está cerrada.");
        contraOfertas.add(c);
    }

}
