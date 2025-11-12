package reventa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuarios.Cliente;
import usuarios.Administrador;
import tiquetes.Tiquete;

//Stub de Tiquete 
class TiqueteStub4 extends Tiquete {
    public TiqueteStub4(String id, Cliente dueño) {
        super();                 
        setIdentificador(id);    
        setCliente(dueño);
    }
    @Override public String imprimir() { return getIdentificador(); }
}

public class MarketplaceTest {

    private Marketplace mp;
    private Cliente vendedor;
    private Cliente comprador;
    private Administrador admin;
    private TiqueteStub4 tiq;

    @BeforeEach
    void setup() {
        mp = new Marketplace();
        vendedor = new Cliente("vend","pw");
        comprador = new Cliente("comp","pw");
        admin = new Administrador("root","pw");

        tiq = new TiqueteStub4("TQ-1", vendedor);
        vendedor.getTiqVi().add(tiq); 
        comprador.setSaldo(500.0);
    }

// Verifica la creacion y el registro en el log al publicar la oferta
    @Test
    void publicarOfertaFuncionaCorrectamente() {
        Oferta o = mp.publicarOferta("OF-1", vendedor, tiq, 120.0);
        assertTrue(mp.ofertas.containsKey("OF-1"));                        
        assertTrue(o.isActiva());
        assertFalse(mp.log.isEmpty());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("Publicada oferta")); 
    }
    
    // Verifica que el vendedor realmente tenga el tiquete para poder publicarlo, falla si no tiene el tiquete
    @Test
    void publicarOfertaSinTiquete() {
        TiqueteStub4 otro = new TiqueteStub4("TQ-2", vendedor);
        assertThrows(IllegalArgumentException.class,
                () -> mp.publicarOferta("OF-X", vendedor, otro, 100.0));  
    }

    // verifica que se cree una contraoferta y quede registrada en el log
    @Test
    void contraofertarAgregaCorrectamente() {
        mp.publicarOferta("OF-2", vendedor, tiq, 150.0);
        mp.contraofertar("OF-2", comprador, 120.0);

        Oferta o = mp.buscarPorId("OF-2").orElseThrow();
        assertEquals(1, o.getContraofertas().size());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("Contraoferta"));
    }

    // verifica que no se pueda contraofertar a si mismo y que el precio sea > 0
    @Test
    void contraofertarConCompradorVendedorOPrecioNegativo() {
        mp.publicarOferta("OF-3", vendedor, tiq, 100.0);
        assertThrows(IllegalArgumentException.class,
                () -> mp.contraofertar("OF-3", vendedor, 90.0)); 
        assertThrows(IllegalArgumentException.class,
                () -> mp.contraofertar("OF-3", comprador, 0.0)); 
    }

    // Verifica que al aceptar una contraoferta se transfiera el saldo y tiquete, se cierre la oferta y en el log quede como aceptada
    @Test
    void aceptarContraofertaFuncionaCorrectamente() {
        mp.publicarOferta("OF-4", vendedor, tiq, 150.0);
        mp.contraofertar("OF-4", comprador, 120.0);

        double saldoVendIni = vendedor.getSaldo();
        double saldoCompIni = comprador.getSaldo();

        mp.aceptarContraoferta("OF-4", vendedor, 0);

        assertEquals(saldoVendIni + 120.0, vendedor.getSaldo(), 1e-9);
        assertEquals(saldoCompIni - 120.0, comprador.getSaldo(), 1e-9);

        assertFalse(vendedor.getTiqVi().contains(tiq));
        assertTrue(comprador.getTiqVi().contains(tiq));

        Oferta o = mp.buscarPorId("OF-4").orElseThrow();
        assertFalse(o.isActiva());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("ACEPTADA"));
    }

    // Verifica que haya saldo para realizar la operacion, si es insuficiente no se da la venta 
    @Test
    void aceptarContraofertaConSaldoInsuficiente() {
        mp.publicarOferta("OF-5", vendedor, tiq, 200.0);
        mp.contraofertar("OF-5", comprador, 600.0); 

        assertThrows(IllegalStateException.class,
                () -> mp.aceptarContraoferta("OF-5", vendedor, 0)); 
    }

    // Verifica que al rechazar una contraoferta su estado pase a rechazada y quede en el log
    @Test
    void rechazarContraofertaFuncionaCorrectamente() {
        mp.publicarOferta("OF-6", vendedor, tiq, 100.0);
        mp.contraofertar("OF-6", comprador, 90.0);
        mp.rechazarContraoferta("OF-6", vendedor, 0);

        Oferta o = mp.buscarPorId("OF-6").orElseThrow();
        assertEquals(Contraoferta.Estado.Rechazada, o.getContraofertas().get(0).getEstado());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("RECHAZADA"));
    }

// Verifica que al comprar una oferta se mueva el saldo y el tiquete , se de el cierre y se loguee
    @Test
    void comprarOfertaFuncionaCorrectamente() {
        mp.publicarOferta("OF-7", vendedor, tiq, 140.0);

        double vIni = vendedor.getSaldo();
        double cIni = comprador.getSaldo();

        mp.comprarOferta("OF-7", comprador);

        assertEquals(vIni + 140.0, vendedor.getSaldo(), 1e-9);
        assertEquals(cIni - 140.0, comprador.getSaldo(), 1e-9);
        assertFalse(vendedor.getTiqVi().contains(tiq));
        assertTrue(comprador.getTiqVi().contains(tiq));

        Oferta o = mp.buscarPorId("OF-7").orElseThrow();
        assertFalse(o.isActiva());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("Compró"));
    }

    // verifica que no se pueda comprar a si mismo y que haya saldo suficiente , validando propiedad y saldo
    @Test
    void comprarOfertaSeaValida() {
        mp.publicarOferta("OF-8", vendedor, tiq, 300.0);
        assertThrows(IllegalArgumentException.class, () -> mp.comprarOferta("OF-8", vendedor));
        
        comprador.setSaldo(10.0);
        assertThrows(IllegalStateException.class, () -> mp.comprarOferta("OF-8", comprador));
    }

    // Verifica que se cumpla el que solo el vendedor pueda retirar su oferta y que se realice el cierre y registro
    @Test
    void borrarOfertaFuncionaCorrectamente() {
        mp.publicarOferta("OF-9", vendedor, tiq, 100.0);
        assertThrows(SecurityException.class, () -> mp.borrarOferta("OF-9", comprador));

        mp.borrarOferta("OF-9", vendedor);
        assertFalse(mp.buscarPorId("OF-9").orElseThrow().isActiva());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("retiró la oferta"));
    }

    // verifica que cuando el administrador elimine una oferta esta se cierre y  se registre
    @Test
    void eliminarOfertaComoAdminFuncionaCorrectamente() {
        mp.publicarOferta("OF-10", vendedor, tiq, 120.0);
        mp.eliminarOfertaComoAdmin("OF-10", admin, "prueba");
        assertFalse(mp.buscarPorId("OF-10").orElseThrow().isActiva());
        assertTrue(mp.log.get(mp.log.size()-1).getDescripcion().contains("Administrador"));
    }
}
