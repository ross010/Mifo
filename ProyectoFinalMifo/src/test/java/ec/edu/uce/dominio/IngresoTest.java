package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Date;
class IngresoTest {
        /**
         * Prueba el constructor por defecto de la clase {@link Ingreso}.
         * Se espera que el ingreso se inicialice con la categoría "Otro".
         */
        @Test
        void testConstructorPorDefecto() {
            Ingreso ingreso = new Ingreso();

            System.out.println("Probando constructor por defecto...");
            System.out.println("Categoría esperada: Otro | Actual: " + ingreso.getCategoria().getNombreCategoria());

            assertNotNull(ingreso);
            assertEquals("Otro", ingreso.getCategoria().getNombreCategoria());
        }

        /**
         * Prueba el constructor completo de {@link Ingreso}.
         * Se verifica que los atributos sean correctamente asignados.
         */
        @Test
        void testConstructorCompleto() {
            Categoria categoria = new Categoria("Salario");
            Date fecha = new Date();
            Ingreso ingreso = new Ingreso("Pago mensual", 1500.0, fecha, categoria);

            System.out.println("Probando constructor completo...");
            System.out.println("Descripción esperada: Pago mensual | Actual: " + ingreso.getDescripcion());
            System.out.println("Monto esperado: 1500.0 | Actual: " + ingreso.getMonto());
            System.out.println("Categoría esperada: Salario | Actual: " + ingreso.getCategoria().getNombreCategoria());

            assertEquals("Pago mensual", ingreso.getDescripcion());
            assertEquals(1500.0, ingreso.getMonto());
            assertEquals(fecha, ingreso.getFecha());
            assertEquals(categoria, ingreso.getCategoria());
        }

        /**
         * Prueba el método {@link Ingreso#setCategoria(Categoria)}.
         * Se verifica que la categoría del ingreso pueda modificarse correctamente.
         */
        @Test
        void testSetCategoria() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            Ingreso ingreso = new Ingreso();
            Categoria nuevaCategoria = new Categoria("Freelance");
            ingreso.setCategoria(nuevaCategoria);

            System.out.println("Probando setCategoria...");
            System.out.println("Nueva categoría esperada: Freelance | Actual: " + ingreso.getCategoria().getNombreCategoria());

            assertEquals("Freelance", ingreso.getCategoria().getNombreCategoria());
        }
    /**
         * Prueba el método {@link Ingreso#registrar()}.
         * Se verifica que un ingreso con monto válido pueda registrarse sin errores.
         */
        @Test
        void testRegistrarIngresoValido() {
            Ingreso ingreso = new Ingreso("Venta de productos", 500.0, new Date(), new Categoria("Comercio"));

            System.out.println("Probando registrar ingreso válido...");
            assertTrue(ingreso.registrar());
        }

        /**
         * Prueba el método {@link Ingreso#registrar()} con monto inválido.
         * Se verifica que un ingreso con monto <= 0 no pueda registrarse.
         */
        @Test
        void testRegistrarIngresoInvalido() {
            Ingreso ingreso = new Ingreso("Reembolso", -100.0, new Date(), new Categoria("Reembolsos"));

            System.out.println("Probando registrar ingreso inválido...");
            assertFalse(ingreso.registrar());
        }

        /**
         * Prueba el método {@link Ingreso#realizar()} con monto inválido.
         * Se verifica que se arroje una excepción cuando el monto es menor o igual a cero.
         */
        @Test
        void testRealizarIngresoMontoInvalido() {
            Ingreso ingreso = new Ingreso("Donación", 0.0, new Date(), new Categoria("Donaciones"));

            System.out.println("Probando realizar ingreso con monto inválido...");
            assertThrows(ExcepcionMifo.MovimientoInvalidoExcepcion.class, ingreso::realizar);
        }

        /**
         * Prueba el método {@link Ingreso#realizar()} con monto válido.
         * Se verifica que el ingreso se pueda realizar sin errores.
         */
        @Test
        void testRealizarIngresoMontoValido() {
            Ingreso ingreso = new Ingreso("Pago por servicios", 1000.0, new Date(), new Categoria("Servicios"));

            System.out.println("Probando realizar ingreso con monto válido...");
            assertDoesNotThrow(() -> ingreso.realizar());
        }

        /**
         * Prueba el método Ingreso.
         * Se verifica que la representación en cadena tenga el formato correcto.
         */
        @Test
        void testToString() {
            Ingreso ingreso = new Ingreso("Premio", 2000.0, new Date(), new Categoria("Incentivos"));
            String resultado = ingreso.toString();
            System.out.println("Probando toString...");
            System.out.println("Salida obtenida:\n" + resultado);
            String resultadoLower = resultado.toLowerCase();
            assertTrue(resultadoLower.contains("categoria= incentivos".toLowerCase().trim()),
                    () -> "El toString no contiene la categoría esperada. Resultado: " + resultado);
        }
}