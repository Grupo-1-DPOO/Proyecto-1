package tiquetes;

import Usuarios.Cliente;
import eventos.Evento;
import eventos.Localidad;

public class TiqueteDeluxe extends Tiquete {
    
    private String beneficios;

    public TiqueteDeluxe(double costo, Localidad localidad, Evento evento, Cliente cliente, String beneficios) {
        super("Deluxe", 1, costo + (costo * (evento.tasa / 100)), localidad, evento, cliente);
        this.beneficios = beneficios;
    }

    public TiqueteDeluxe() {
        super(null, 0, 0.0, null, null, null);
    }

    public String getBeneficios() {
        return this.beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    @Override
    public String imprimir() {
        return this.getIdentificador() + "," +
               this.getTipo() + "," +
               this.getIndividuos() + "," +
               this.getCosto() + "," +
               (this.getLocalidad() != null ? this.getLocalidad().getNombre() : "N/A") + "," +
               (this.getEvento() != null ? this.getEvento().getNombre() : "N/A") + "," +
               this.getFecha() + "," +
               this.getHora() + "," +
               (this.getCliente() != null ? this.getCliente().getLog() : "N/A") + "," +
               (this.getBeneficios() != null ? this.getBeneficios() : "N/A");
    }

    @Override
    public String toString() {
        return imprimir();
    }
}
