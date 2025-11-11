package reventa;

import java.util.*;
import tiquetes.Tiquete;
import tiquetes.TiqueteDeluxe;
import usuarios.Administrador;
import usuarios.Cliente;

public class Marketplace {
	private final Map<String, Oferta> ofertas = new HashMap<>();
    private final List<registro> log = new ArrayList<>();
	
//Ofertas Disponibles 
    public List<Oferta> listarOfertasActivas() {
        List<Oferta> r = new ArrayList<>();
        for (Oferta o : ofertas.values()) if (o.isActiva()) r.add(o);
        return r;
    }
    public Optional<Oferta> buscarPorId(String id) { return Optional.ofNullable(ofertas.get(id)); }
    public List<registro> verLog(Administrador admin) { return Collections.unmodifiableList(log); }

//Publicar 
    public Oferta publicarOferta(String id, Cliente vendedor, Tiquete tiq, double precio) {
        if (tiq == null) throw new IllegalArgumentException("Tiquete nulo.");
        if (tiq instanceof TiqueteDeluxe) throw new IllegalArgumentException("No se pueden revender tiquetes Deluxe.");
        if (precio <= 0) throw new IllegalArgumentException("Precio no valido.");
        if (ofertas.containsKey(id)) throw new IllegalArgumentException("ID de oferta ya existe.");

        Oferta o = new Oferta(id, vendedor, tiq, precio);
        ofertas.put(id, o);
        log("Oferta de boleta " + id + " por " + vendedor.getLog() + " del tiquete " + tiq.getIdentificador() + " por  un precio de: $" + precio);
        return o;
    }

//Borrar oferta V
    public void borrarOferta(String id, Cliente vendedor) {
        Oferta o = mustExist(id);
        if (o.getVendedor() != vendedor) throw new SecurityException("No eres el vendedor, no puedes borrar la oferta.");
        o.cerrar();
        log("Vendedor " + vendedor.getLog() + " borró la oferta de la boleta " + id);
    }
    private Oferta mustExist(String id) {
    	// TODO Auto-generated method stub
    	return null;
    }

//Eliminar oferta A
    public void eliminarOfertaComoAdmin(String id, Administrador admin, String motivo) {
    	Oferta o = mustExist(id);
    	o.cerrar();
    	log("El administrador " + admin.getLog() + " elimino la oferta de la boleta " + id + " (motivo: " + motivo + ")");
    }

//ContraOfertar
    public void contraofertar(String id, Cliente comprador, double nuevoPrecio) {
    	Oferta o = mustExist(id);
    	if (comprador == o.getVendedor()) throw new IllegalArgumentException("No puedes hacer una ContraOferta.");
    	if (nuevoPrecio <= 0) throw new IllegalArgumentException("Precio inválido.");
    	contraOferta c = new contraOferta(comprador, nuevoPrecio);
    	o.agregarContraoferta(c);
    	log("Contraoferta de " + comprador.getLog() + " por un precio de $" + nuevoPrecio + " a la oferta de la boleta: " + id);
    }

//Aceptar C.O
    public void aceptarContraoferta(String id, Cliente vendedor, int indice) {
        Oferta o = mustExist(id);
        if (o.getVendedor() != vendedor) throw new SecurityException("No eres el vendedor.");
        contraOferta c = o.getContraofertas().get(indice);
        if (c.getEstado() != contraOferta.Estado.Pendiente) throw new IllegalStateException("La contraoferta no está pendiente.");

        Cliente comprador = c.getComprador();
        double precio = c.getNuevoPrecio();
        if (comprador.getSaldo() < precio) throw new IllegalStateException("El comprador no tiene saldo suficiente");

        comprador.debitarSaldo(precio);
        vendedor.acreditarSaldo(precio);

        c.setEstado(contraOferta.Estado.Aceptada);
        o.cerrar();

    	log("La contraoferta de " + comprador.getLog() + " ha sido ACEPTADA por el vendedor " + vendedor.getLog() + " por un precio de $" + precio + " en oferta de la boleta " + id);
    }
    
//Rechazar C.O
    public void rechazarContraoferta(String id, Cliente vendedor, int indice) {
        Oferta o = mustExist(id);
        Cliente comprador = c.getComprador();
        if (o.getVendedor() != vendedor) throw new SecurityException("No eres el vendedor.");
        contraOferta c = o.getContraofertas().get(indice);

        if (c.getEstado() != contraOferta.Estado.Pendiente) throw new IllegalStateException("La contraoferta no está pendiente.");
        c.setEstado(contraOferta.Estado.Rechazada);
        log("La contrapferta de " + comprador.getLog() + " ha sido RECHAZADA por el vendedor " + vendedor.getLog() + " en oferta de la boleta " + id);
    } 
//Comprar al precio publicado 
    public void comprarOferta(String id, Cliente comprador) {
        Oferta o = mustExist(id);
        if (comprador == o.getVendedor()) throw new IllegalArgumentException("No puedes comprarte a ti mismo.");
        double precio = o.getPrecio();
        if (comprador.getSaldo() < precio) throw new IllegalStateException("Saldo insuficiente.");

        comprador.debitarSaldo(precio);
        o.getVendedor().acreditarSaldo(precio);
        o.cerrar();

        log(comprador.getLog() + " Compró la oferta de la boleta " + id + " por un precio de $" + precio);
    }

}
