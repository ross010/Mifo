package ec.edu.uce;
import ec.edu.uce.dominio.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Prueba unitaria para {@link SubMenuIngresarSistema}.
 * Se verifica la correcta inicialización del menú.
 */
class SubMenuIngresarSistemaTest {

    /**
     * Prueba la inicialización del menú con un usuario válido.
     * Se espera que la instancia de {@link SubMenuIngresarSistema} sea creada sin errores.
     */
    @Test
    void testInicializacionMenu() {
        Usuario usuario = new Usuario("testUser", "testPass", "test@example.com", 0, 1);
        SubMenuIngresarSistema subMenu = new SubMenuIngresarSistema(usuario);
        assertNotNull(subMenu);
    }
}