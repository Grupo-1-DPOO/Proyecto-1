package usuarios;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AdministradorTest {

	// Verifica que el constructor vacio defina como tipo administrador
    @Test
    void constructorVacioDefineTipoAdministrador() {
        Administrador a = new Administrador();
        assertEquals("Administrador", a.getTipo());
    }

    // verifica que cuando el constructor este con argumentos se fije el login, la contrasena y el tipo
    @Test
    void constructorConArgsFijaLoginPassYTipo() {
        Administrador a = new Administrador("root", "pw");
        assertEquals("root", a.getLog());
        assertEquals("pw", a.getContraseña());
        assertEquals("Administrador", a.getTipo());
    }

    // Verifica que el toString siga el formato Login,Pass,Tipo
    @Test
    void toStringSigueElFormato() {
        Administrador a = new Administrador("adm", "123");
        assertEquals("adm,123,Administrador", a.toString());
    }
}

