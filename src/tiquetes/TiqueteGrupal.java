package tiquetes;

import eventos.Evento;
import eventos.Localidad;
import usuarios.Cliente;

public class TiqueteGrupal extends Tiquete {
	
	public TiqueteGrupal() {
        super(null, 0, 0.0, null, null, null);
    }

	public TiqueteGrupal(int individuos, Localidad localidad, Evento evento, Cliente cliente) {
		super("Grupal", individuos, 
		      (evento.getPrecioBase() + (localidad.getPorcentaje() * evento.getPrecioBase())) * individuos,
		      localidad, evento, cliente);
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
               (this.getCliente() != null ? this.getCliente().getLog() : "N/A");
    }
	@Override
	public String toString() {
		return imprimir();
	}
}
