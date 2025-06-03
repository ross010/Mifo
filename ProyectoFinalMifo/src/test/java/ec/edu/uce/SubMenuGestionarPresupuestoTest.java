package ec.edu.uce;
import static org.junit.jupiter.api.Assertions.*;
import ec.edu.uce.dominio.Usuario;
import org.junit.jupiter.api.Test;
/**
 * Prueba unitaria para {@link SubMenuGestionarPresupuesto}.
 * Se valida la inicialización del submenú sin verificar usuario.
 */
class SubMenuGestionarPresupuestoTest {

    /**
     * Prueba la creación del submenú con un usuario válido.
     * Se espera que la instancia de {@link SubMenuGestionarPresupuesto} se cree correctamente.
     */
    @Test
    void testInicializacionSubMenu() {
        Usuario usuario = new Usuario("Carlos", "1234567p", "example@.com", 0, 0);
        SubMenuGestionarPresupuesto subMenu = new SubMenuGestionarPresupuesto(usuario);

        assertNotNull(subMenu);
    }

    /**
     * Prueba la ejecución del menú sin errores.
     * Se verifica que el método `menuGestionarPresupuesto()` pueda ser llamado sin excepciones.
     */
    @Test
    void testEjecutarMenuSinErrores() {
        Usuario usuario = new Usuario("Carlos", "password", "carlos@example.com", 0, 0);
        SubMenuGestionarPresupuesto subMenu = new SubMenuGestionarPresupuesto(usuario);

        assertDoesNotThrow(() -> subMenu.menuGestionarPresupuesto());
    }
}