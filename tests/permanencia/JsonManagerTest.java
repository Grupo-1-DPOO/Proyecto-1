package permanencia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

public class JsonManagerTest {

    @TempDir
    Path tempDir;

    Path jsonPath;

    @BeforeEach
    void setUp() {
        jsonPath = tempDir.resolve("persistencia_test.json");
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(jsonPath); 
    }

    static class Dummy {
        String nombre;
        int cantidad;

        Dummy() {}
        Dummy(String n, int c) { this.nombre = n; this.cantidad = c; }

        @Override public boolean equals(Object o) {
            if (!(o instanceof Dummy)) return false;
            Dummy d = (Dummy) o;
            return Objects.equals(nombre, d.nombre) && cantidad == d.cantidad;
        }
        @Override public int hashCode() { return Objects.hash(nombre, cantidad); }
    }

    //Verifica que guardar y luego cargar una lista de strings conserve los datos
    @Test
    void guardarYCargarListaDeStrings() {
        List<String> original = Arrays.asList("A", "B", "C");
        JsonManager.guardarLista(jsonPath.toString(), original); 
        List<String> cargada = JsonManager.cargarLista(jsonPath.toString(), String.class);

        assertEquals(original, cargada, "La lista cargada debe ser idéntica a la original");
        assertTrue(new File(jsonPath.toString()).exists(), "Debe crearse el archivo JSON");
    }

    // Verifica que se pueda serializar una lista de POJOs
    @Test
    void guardarYCargarListaDeObjetosPOJO() {
        List<Dummy> original = Arrays.asList(new Dummy("x", 1), new Dummy("y", 2));
        JsonManager.guardarLista(jsonPath.toString(), original);
        List<Dummy> cargada = JsonManager.cargarLista(jsonPath.toString(), Dummy.class);

        assertEquals(original, cargada, "Los objetos deben conservar sus campos tras persistencia");
    }

    // Verifica que al guardar dos veces en la misma ruta el segundo guardado sobrescribe el contenido del archivo
    @Test
    void guardarSobrescribeContenidoPrevio() {
        JsonManager.guardarLista(jsonPath.toString(), Arrays.asList("uno"));
        JsonManager.guardarLista(jsonPath.toString(), Arrays.asList("dos", "tres"));

        List<String> cargada = JsonManager.cargarLista(jsonPath.toString(), String.class);
        assertEquals(Arrays.asList("dos", "tres"), cargada, "El contenido previo debe ser reemplazado");
    }

    //verifica que cargar desde un archivo inexistente no lance excepción y devuelva una lista vacía
    @Test
    void cargarArchivoInexistenteDevuelveListaVacia() {
        Path noExiste = tempDir.resolve("no_such_file.json");

        List<String> cargada = JsonManager.cargarLista(noExiste.toString(), String.class);
        assertNotNull(cargada, "Nunca debe devolver null");
        assertTrue(cargada.isEmpty(), "Si el archivo no existe, debe retornar lista vacía");
    }

    // Verifica que el archivo realmente contenga algo 
    @Test
    void guardar_CreaArchivoConContenido() {
        JsonManager.guardarLista(jsonPath.toString(), Arrays.asList(1, 2, 3));
        File f = new File(jsonPath.toString());

        assertTrue(f.exists(), "El archivo debe existir tras guardar");
        assertTrue(f.length() > 0, "El archivo no debe estar vacío");
    }
}

