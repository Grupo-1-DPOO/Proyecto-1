package reventa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import tiquetes.Tiquete;
import tiquetes.TiqueteDeluxe;
import usuarios.Administrador;
import usuarios.Cliente;

public class Marketplace {

	public final Map<String, Oferta> ofertas;
	
	public final List<Registro> log;
	
	public Marketplace(){
	
		ofertas = new HashMap<>();
		log = new ArrayList<>();}

  
//Ofertas Disponibles 

    public List<Oferta> listarOfertasActivas() {
        List<Oferta> r = new ArrayList<>();
        for (Oferta o : ofertas.values()) if (o.isActiva()) r.add(o);
        return r;
    }

    public Optional<Oferta> buscarPorId(String id) {
        return Optional.ofNullable(ofertas.get(id));
    }

 
    public List<Registro> verLog(Administrador admin) {
        return Collections.unmodifiableList(log);
    }

//Publicar 
    public Oferta publicarOferta(String id, Cliente vendedor, Tiquete tiq, double precio) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("ID vacío.");
        if (vendedor == null) throw new IllegalArgumentException("Vendedor nulo.");
        if (tiq == null) throw new IllegalArgumentException("Tiquete nulo.");
        if (precio <= 0) throw new IllegalArgumentException("Precio inválido.");
        if (tiq instanceof TiqueteDeluxe) throw new IllegalArgumentException("No se revenden tiquetes Deluxe.");
        if (!vendedor.getTiqVi().contains(tiq)) throw new IllegalArgumentException("El vendedor no posee ese tiquete.");
        if (ofertas.containsKey(id)) throw new IllegalArgumentException("ID de oferta ya existe.");

        Oferta o = new Oferta(id, vendedor, tiq, precio);
        ofertas.put(id, o);
        log("Publicada oferta " + id + " por " + vendedor.getLog()
            + " (tiquete " + tiq.getIdentificador() + ", $" + precio + ")");
        return o;
    }

 //Borrar oferta V
    public void borrarOferta(String id, Cliente vendedor) {
        Oferta o = mustExist(id);
        if (o.getVendedor() != vendedor) throw new SecurityException("No eres el vendedor de esta oferta.");
        if (o.isActiva()) o.cerrar();
        log("Vendedor " + vendedor.getLog() + " retiró la oferta " + id);
    }

//Eliminar oferta A
    public void eliminarOfertaComoAdmin(String id, Administrador admin, String motivo) {
        Oferta o = mustExist(id);
        if (o.isActiva()) o.cerrar();
        log("Administrador " + admin.getLog() + " eliminó la oferta " + id + " (motivo: " + motivo + ")");
    }

 //ContraOfertar

    public void contraofertar(String id, Cliente comprador, double nuevoPrecio) {
        Oferta o = mustExistActiva(id);
        if (comprador == o.getVendedor()) throw new IllegalArgumentException("No puedes contraofertarte.");
        if (nuevoPrecio <= 0) throw new IllegalArgumentException("Precio inválido.");
        Contraoferta c = new Contraoferta(comprador, nuevoPrecio);
        o.agregarContraoferta(c);
        log("Contraoferta de " + comprador.getLog() + " por $" + nuevoPrecio + " en oferta " + id);
    }

//Aceptar C.O
    public void aceptarContraoferta(String id, Cliente vendedor, int indice) {
        Oferta o = mustExistActiva(id);
        if (o.getVendedor() != vendedor) throw new SecurityException("No eres el vendedor.");
        List<Contraoferta> lista = o.getContraofertas();
        if (indice < 0 || indice >= lista.size()) throw new IndexOutOfBoundsException("Índice de contraoferta inválido.");

        Contraoferta c = lista.get(indice);
        if (c.getEstado() != Contraoferta.Estado.Pendiente)
            throw new IllegalStateException("La contraoferta no está pendiente.");

        Cliente comprador = c.getComprador();
        double precio = c.getNuevoPrecio();

       
        if (comprador.getSaldo() < precio) throw new IllegalStateException("Saldo insuficiente del comprador.");
        comprador.debitarSaldo(precio);
        vendedor.acreditarSaldo(precio);

    
        transferirTiquete(o, comprador);

       
        c.setEstado(Contraoferta.Estado.Aceptada);
        o.cerrar();

        log("La contraoferta de " + comprador.getLog() + " ha sido ACEPTADA por el vendedor " + vendedor.getLog() + " por un precio de $" + precio + " en oferta de la boleta " + id);
    }

//Rechazar C.O
    public void rechazarContraoferta(String id, Cliente vendedor, int indice) {
        Oferta o = mustExistActiva(id);
        if (o.getVendedor() != vendedor) throw new SecurityException("No eres el vendedor.");
        List<Contraoferta> lista = o.getContraofertas();
        if (indice < 0 || indice >= lista.size()) throw new IndexOutOfBoundsException("Índice de contraoferta inválido.");

        Contraoferta c = lista.get(indice);
        if (c.getEstado() != Contraoferta.Estado.Pendiente)
            throw new IllegalStateException("La contraoferta no está pendiente.");

        Cliente comprador = c.getComprador();
        c.setEstado(Contraoferta.Estado.Rechazada);
        log("La contrapferta de " + comprador.getLog() + " ha sido RECHAZADA por el vendedor " + vendedor.getLog() + " en oferta de la boleta " + id);
    }

  //Comprar al precio publicado 

    public void comprarOferta(String id, Cliente comprador) {
        Oferta o = mustExistActiva(id);
        if (comprador == o.getVendedor()) throw new IllegalArgumentException("No puedes comprarte a ti mismo.");
        double precio = o.getPrecio();
        if (comprador.getSaldo() < precio) throw new IllegalStateException("Saldo insuficiente.");

        comprador.debitarSaldo(precio);
        o.getVendedor().acreditarSaldo(precio);

        transferirTiquete(o, comprador);

        o.cerrar();
        log(comprador.getLog() + " Compró la oferta de la boleta " + id + " por un precio de $" + precio);
    }


    private Oferta mustExist(String id) {
        Oferta o = ofertas.get(id);
        if (o == null) throw new NoSuchElementException("No existe la oferta " + id);
        return o;
    }

    private Oferta mustExistActiva(String id) {
        Oferta o = mustExist(id);
        if (!o.isActiva()) throw new IllegalStateException("La oferta no está activa.");
        return o;
    }

    private void log(String descripcion) {
        log.add(new Registro(descripcion));
    }

    private void transferirTiquete(Oferta o, Cliente comprador) {
        Cliente vendedor = o.getVendedor();
        Tiquete t = o.getTiquete();

    
        vendedor.getTiqVi().remove(t);
        comprador.getTiqVi().add(t);


        t.setCliente(comprador);
    }
}