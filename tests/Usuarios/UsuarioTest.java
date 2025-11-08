package Usuarios;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class UsuarioTest  {
	
	// Usuario es abstracta entonces se crea UsuarioPrueba para instanciar objetos de prueba
	static class UsuarioPrueba extends Usuario {
		public UsuarioPrueba(String log, String cont, String tip) {
			super(log, cont, tip);
		}
		
	}
	
	// Verificar que el constructor inicialice correctamente 
	@Test
	void constructor_inicializaCamposCorrectamente() {
		Usuario u = new UsuarioPrueba("jm123", "abc123", "Cliente");
		
		assertEquals("jm123", u.getLog());
		assertEquals("abc123", u.getContraseña());
		assertEquals("Cliente", u.getTipo());
	}
	
	// Verificar el toString comportamiento del toString con valores vacios o nulos. Si se incluyen literalmente
		@Test
		void toString_camposVaciosoNulos_seIncluyen() {
			Usuario u = new UsuarioPrueba("",null,"Cliente");
			
			assertEquals(",null,Cliente", u.toString());
		}

}
