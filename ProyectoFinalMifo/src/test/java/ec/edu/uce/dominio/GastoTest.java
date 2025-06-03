package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import ec.edu.uce.util.ExcepcionMifo;
class GastoTest {
        /**
         * Prueba el constructor por defecto de la clase {@link Gasto}.
         * Se espera que el gasto se inicialice con la categoría "Alimentación".
         */
        @Test
        void testConstructorPorDefecto() {
            Gasto gasto = new Gasto();

            System.out.println("Probando constructor por defecto...");
            System.out.println("Categoría esperada: Alimentación | Actual: " + gasto.getCategoria().getNombreCategoria());

            assertNotNull(gasto);
            assertEquals("Alimentación", gasto.getCategoria().getNombreCategoria());
        }

        /**
         * Prueba el constructor completo de {@link Gasto}.
         * Se verifica que los atributos sean correctamente asignados.
         */
        @Test
        void testConstructorCompleto() {
            Categoria categoria = new Categoria("Transporte");
            Date fecha = new Date();
            Gasto gasto = new Gasto("Pago de gasolina", 50.0, fecha, categoria);

            System.out.println("Probando constructor completo...");
            System.out.println("Descripción esperada: Pago de gasolina | Actual: " + gasto.getDescripcion());
            System.out.println("Monto esperado: 50.0 | Actual: " + gasto.getMonto());
            System.out.println("Categoría esperada: Transporte | Actual: " + gasto.getCategoria().getNombreCategoria());

            assertEquals("Pago de gasolina", gasto.getDescripcion());
            assertEquals(50.0, gasto.getMonto());
            assertEquals(fecha, gasto.getFecha());
            assertEquals(categoria, gasto.getCategoria());
        }

        /**
         * Prueba el método {@link Gasto#setCategoria(Categoria)}.
         * Se verifica que la categoría del gasto pueda modificarse correctamente.
         */
        @Test
        void testSetCategoria() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            Gasto gasto = new Gasto();
            Categoria nuevaCategoria = new Categoria("Entretenimiento");
            gasto.setCategoria(nuevaCategoria);

            System.out.println("Probando setCategoria...");
            System.out.println("Nueva categoría esperada: Entretenimiento | Actual: " + gasto.getCategoria().getNombreCategoria());

            assertEquals("Entretenimiento", gasto.getCategoria().getNombreCategoria());
        }
    /**
         * Prueba el método {@link Gasto#registrar()}.
         * Se verifica que un gasto con monto válido pueda registrarse sin errores.
         */
        @Test
        void testRegistrarGastoValido() {
            Gasto gasto = new Gasto("Cena con amigos", 30.0, new Date(), new Categoria("Ocio"));

            System.out.println("Probando registrar gasto válido...");
            assertTrue(gasto.registrar());
        }

        /**
         * Prueba el método {@link Gasto#registrar()} con monto inválido.
         * Se verifica que un gasto con monto <= 0 no pueda registrarse.
         */
        @Test
        void testRegistrarGastoInvalido() {
            Gasto gasto = new Gasto("Compra de ropa", -10.0, new Date(), new Categoria("Moda"));

            System.out.println("Probando registrar gasto inválido...");
            assertFalse(gasto.registrar());
        }

        /**
         * Prueba el método {@link Gasto#realizar()} con monto inválido.
         * Se verifica que se arroje una excepción cuando el monto es menor o igual a cero.
         */
        @Test
        void testRealizarGastoMontoInvalido() {
            Gasto gasto = new Gasto("Suscripción mensual", 0.0, new Date(), new Categoria("Servicios"));

            System.out.println("Probando realizar gasto con monto inválido...");
            assertThrows(ExcepcionMifo.MovimientoInvalidoExcepcion.class, gasto::realizar);
        }

        /**
         * Prueba el método {@link Gasto#realizar()} con monto válido.
         * Se verifica que el gasto se pueda realizar sin errores.
         */
        @Test
        void testRealizarGastoMontoValido() {
            Gasto gasto = new Gasto("Compra de libros", 40.0, new Date(), new Categoria("Educación"));

            System.out.println("Probando realizar gasto con monto válido...");
            assertDoesNotThrow(() -> gasto.realizar());
        }
        /**
         * Prueba el método Gasto
         * Se verifica que la representación en cadena tenga el formato correcto.
         */
        @Test
        void testToString() {
            Gasto gasto = new Gasto("Vacaciones", 500.0, new Date(), new Categoria("Turismo"));
            String resultado = gasto.toString();
            System.out.println("Probando toString...");
            System.out.println("Salida obtenida:\n" + resultado);
            String resultadoLower = resultado.toLowerCase();
            assertTrue(resultadoLower.contains("categoria= turismo".toLowerCase().trim()));
        }
}