package Reventa;

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
    private final List<Contraoferta> contraofertas;

    public Oferta(String idOferta, Cliente vendedor, Tiquete tiquete, double precio) {
        this.idOferta = idOferta;
        this.vendedor = vendedor;
        this.tiquete = tiquete;
        this.precio = precio;
        this.activa = true;
        this.fechaPublicacion = LocalDateTime.now();
        this.contraofertas = new ArrayList<>();
    }

    //GETTERS
    public String getIdOferta() {
        return idOferta;
    }

    public Cliente getVendedor() {
        return vendedor;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isActiva() {
        return activa;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public List<Contraoferta> getContraofertas() {
        return contraofertas;
    }

    //SETTERS
    public void setPrecio(double nuevoPrecio) {
        if (nuevoPrecio <= 0) throw new IllegalArgumentException("El precio debe ser positivo.");
        this.precio = nuevoPrecio;
    }


   
    public void agregarContraoferta(Contraoferta c) {
        if (!activa) throw new IllegalStateException("La oferta está cerrada.");
        contraofertas.add(c);
    }

    public void cerrar() {
        this.activa = false;
    }
}
