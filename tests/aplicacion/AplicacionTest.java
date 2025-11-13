package aplicacion;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import consola.Aplicacion;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

import usuarios.Cliente;
import usuarios.Administrador;
import usuarios.Organizador;

import eventos.Venue;

class AplicacionIntegrationTest {

    private Aplicacion aplicacion;
    
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() throws IOException {
        // Configurar el directorio temporal para los archivos JSON
        System.setProperty("user.dir", tempDir.toString());
        
        aplicacion = new Aplicacion();
        aplicacion.cargarTodo();
    }
    
    @AfterEach
    void tearDown() {
        // Limpiar archivos de prueba
        String[] archivos = {
            "clientes.json", "organizadores.json", "staff.json",
            "eventosProx.json", "eventosPas.json", "venues.json",
            "activos.json", "pendientes.json", "cancelados.json"
        };
        
        for (String archivo : archivos) {
            File file = new File(archivo);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    void testCreacionArchivosJSON() {
        String[] archivos = {
            "clientes.json", "organizadores.json", "staff.json",
            "eventosProx.json", "eventosPas.json", "venues.json",
            "activos.json", "pendientes.json", "cancelados.json"
        };
        
        for (String archivo : archivos) {
            File file = new File(archivo);
            assertTrue(file.exists(), "El archivo " + archivo + " debería existir");
        }
    }

    @Test
    void testRegistroYGuardadoCliente() {
        Cliente cliente = new Cliente("PepitoPerez", "123");
        aplicacion.guardarCliente("clientes.json", cliente);
        
        ArrayList<Cliente> clientesCargados = aplicacion.cargarListaSegura("clientes.json", Cliente.class);
        assertFalse(clientesCargados.isEmpty(), "Debería haber clientes guardados");
        assertEquals("testUser", clientesCargados.get(0).getLog());
    }

    @Test
    void testRegistroYGuardadoAdministrador() {
        Administrador admin = new Administrador("JuanitoJuanez", "456");
        aplicacion.guardarAdmin("staff.json", admin);
        
        ArrayList<Administrador> adminsCargados = aplicacion.cargarListaSegura("staff.json", Administrador.class);
        assertFalse(adminsCargados.isEmpty(), "Debería haber administradores guardados");
        assertEquals("adminUser", adminsCargados.get(0).getLog());
    }

    @Test
    void testRegistroYGuardadoOrganizador() {
        Organizador org = new Organizador("FulanitoFulanez", "789");
        aplicacion.guardarOrg("organizadores.json", org);
        
        ArrayList<Organizador> orgsCargados = aplicacion.cargarListaSegura("organizadores.json", Organizador.class);
        assertFalse(orgsCargados.isEmpty(), "Debería haber organizadores guardados");
        assertEquals("orgUser", orgsCargados.get(0).getLog());
    }

    @Test
    void testCargaPersistencia() {

        Cliente cliente = new Cliente("PepitoPerez", "123");
        aplicacion.guardarCliente("clientes.json", cliente);
        
        Administrador admin = new Administrador("admin1", "admin1");
        aplicacion.guardarAdmin("staff.json", admin);
     
        Aplicacion app = new Aplicacion();
        app.cargarTodo();

        assertFalse(app.getClientes().isEmpty());
        
        assertFalse(app.getOrgs().isEmpty());
        
        assertFalse(app.getStaff().isEmpty());
        
        
    }

    @Test
    void testCrearVenue() {

        Organizador organizador = new Organizador("Che Guevara", "comunismo");
        aplicacion.guardarOrg("organizadores.json", organizador);
        

        Venue venue = new Venue("Tangamandapio", 100, "Goce Pagano");
        venue.agregarLocalidad("General", 0, 100);
        aplicacion.guardarVenue("venues.json", venue);
        
        // Verificar que se guardaron los datos
        ArrayList<Venue> venues = aplicacion.cargarListaSegura("venues.json", Venue.class);
        assertFalse(venues.isEmpty(), "Debería haber venues guardados");
    }




}
