package ec.edu.uce.dominio;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import ec.edu.uce.util.ExcepcionMifo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Juan Pérez", "1234", "juan@correo.com", 0, 1);
    }

    @Test
    void testCrearPresupuestoExitoso() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Presupuesto p = new Presupuesto(1000.0, new Date());
        String resultado = usuario.crearPresupuesto(p);
        assertNotNull(resultado);
        assertEquals(1, usuario.getNumPresupuestos());
        System.out.println("Presupuesto creado: " + resultado);
    }

    @Test
    void testCrearPresupuestoDuplicado() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Date fecha = new Date();
        usuario.crearPresupuesto(new Presupuesto(1500.0, fecha));
        assertThrows(ExcepcionMifo.MovimientoInvalidoExcepcion.class, () ->
                usuario.crearPresupuesto(new Presupuesto(1500.0, fecha)));
        System.out.println("Intento de crear presupuesto duplicado detectado.");
    }

    @Test
    void testEliminarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        usuario.crearPresupuesto(new Presupuesto(1000.0, new Date()));
        usuario.crearPresupuesto(new Presupuesto(2000.0, new Date()));
        System.out.println("Número de presupuestos antes de eliminación: " + usuario.getNumPresupuestos());
        usuario.eliminarPresupuesto(0);
        System.out.println("Número de presupuestos después de eliminación: " + usuario.getNumPresupuestos());
    }

    @Test
    void testEditarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Date fecha = new Date();
        usuario.crearPresupuesto(new Presupuesto(1000.0, fecha));
        usuario.editarPresupuesto(0, 5000.0, fecha, 100.0, 200.0);

        Presupuesto editado = usuario.buscarPresupuesto(0);
        System.out.println("Presupuesto editado: " + editado);
        assertEquals(5000.0, editado.getPresupuesto());
        assertEquals(100.0, editado.getGastoTotal());
        assertEquals(200.0, editado.getIngresoTotal());
    }

    @Test
    void testConsultarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        usuario.crearPresupuesto(new Presupuesto(1200.0, new Date()));
        String resultado = usuario.consultarPresupuesto();
        System.out.println("Presupuesto consultado: " + resultado);
        assertTrue(resultado.contains("Presupuesto"));
    }

    @Test
    void testCambiarContrasenaCorrecta() throws ExcepcionMifo.ContrasenaInvalidaExcepcion {
        usuario.cambiarContrasena("1234", "abcd");
        System.out.println("Nueva contraseña: " + usuario.getContrasena());
        assertEquals("abcd", usuario.getContrasena());
    }

    @Test
    void testCambiarContrasenaIncorrecta() {
        assertThrows(ExcepcionMifo.ContrasenaInvalidaExcepcion.class, () ->
                usuario.cambiarContrasena("wrong", "nueva"));
        System.out.println("Cambio de contraseña fallido debido a contraseña incorrecta.");
    }

    @Test
    void testEqualsYHashCode() {
        Usuario otro = new Usuario("Ana", "pass", "ana@correo.com", 0, 1);
        System.out.println("Comparación de usuarios: " + usuario.equals(otro));
        assertEquals(usuario, otro);
        assertEquals(usuario.hashCode(), otro.hashCode());
    }

    @Test
    void testToString() {
        String result = usuario.toString();
        System.out.println("Representación del usuario: " + result);
        assertTrue(result.contains("Usuario{"));
        assertTrue(result.contains("nombre='Juan Pérez'"));
    }

}
