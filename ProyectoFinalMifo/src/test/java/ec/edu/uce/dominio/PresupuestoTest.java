package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import ec.edu.uce.util.ExcepcionMifo;
class PresupuestoTest {
        /**
         * Prueba el constructor completo de la clase {@link Presupuesto}.
         * Se espera que el presupuesto se inicialice correctamente con todos los valores proporcionados.
         */
        @Test
        void testConstructorCompleto() {
            Date fecha = new Date();
            Usuario usuario = new Usuario("Carlos", "password123", "carlos@example.com", 0, 1);
            Presupuesto presupuesto = new Presupuesto(5000.0, fecha, usuario);

            System.out.println("Probando constructor completo...");
            System.out.println("Presupuesto esperado: 5000.0 | Actual: " + presupuesto.getPresupuesto());
            System.out.println("Gasto total esperado: 0.0 | Actual: " + presupuesto.getGastoTotal());
            System.out.println("Ingreso total esperado: 0.0 | Actual: " + presupuesto.getIngresoTotal());
            System.out.println("Usuario esperado: Carlos | Actual: " + presupuesto.getUsuario().getNombre());

            assertEquals(5000.0, presupuesto.getPresupuesto());
            assertEquals(0.0, presupuesto.getGastoTotal());
            assertEquals(0.0, presupuesto.getIngresoTotal());
            assertEquals("Carlos", presupuesto.getUsuario().getNombre());
        }

        /**
         * Prueba el método {@link Presupuesto#agregarMovimiento(Movimiento)}.
         * Se verifica que los movimientos sean correctamente agregados y afecten el presupuesto.
         */
        @Test
        void testAgregarMovimiento() {
            Presupuesto presupuesto = new Presupuesto(1000.0, new Date());
            Movimiento ingreso = new Ingreso("Salario", 500.0, new Date(), new Categoria("Salario"));
            Movimiento gasto = new Gasto("Compra", 200.0, new Date(), new Categoria("Compras"));

            presupuesto.agregarMovimiento(ingreso);
            presupuesto.agregarMovimiento(gasto);

            System.out.println("Probando agregarMovimiento...");
            System.out.println("Presupuesto esperado: 1300.0 | Actual: " + presupuesto.getPresupuesto());
            System.out.println("Ingreso total esperado: 500.0 | Actual: " + presupuesto.getIngresoTotal());
            System.out.println("Gasto total esperado: 200.0 | Actual: " + presupuesto.getGastoTotal());
            System.out.println("Número de movimientos esperado: 2 | Actual: " + presupuesto.getNumMovimientos());

            assertEquals(1300.0, presupuesto.getPresupuesto());
            assertEquals(500.0, presupuesto.getIngresoTotal());
            assertEquals(200.0, presupuesto.getGastoTotal());
            assertEquals(2, presupuesto.getNumMovimientos());
        }

        /**
         * Prueba el método {@link Presupuesto#editarMovimiento(int, String, double, Date, boolean, Categoria)}.
         * Se verifica que los movimientos sean correctamente modificados.
         */
        @Test
        void testEditarMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            Presupuesto presupuesto = new Presupuesto(2000.0, new Date());
            Movimiento ingreso = new Ingreso("Salario", 1000.0, new Date(), new Categoria("Salario"));
            presupuesto.agregarMovimiento(ingreso);

            System.out.println("Antes de editar: Presupuesto=" + presupuesto.getPresupuesto());

            // Cambié la descripción "Bono" por una con 5 o más caracteres
            presupuesto.editarMovimiento(0, "BonoX", 1200.0, new Date(), true, new Categoria("Incentivo"));

            System.out.println("Después de editar: Presupuesto=" + presupuesto.getPresupuesto());

            assertEquals(1200.0, presupuesto.getIngresoTotal());
            assertEquals(3200.0, presupuesto.getPresupuesto(), "El presupuesto después de editar debería ser 3200.0");
        }

        /**
         * Prueba el método {@link Presupuesto#eliminarMovimiento(int)}.
         * Se verifica que los movimientos puedan ser eliminados correctamente.
         */
        @Test
        void testEliminarMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            Presupuesto presupuesto = new Presupuesto(2000.0, new Date());
            Movimiento gasto = new Gasto("Cena", 300.0, new Date(), new Categoria("Restaurantes"));
            presupuesto.agregarMovimiento(gasto);

            System.out.println("Antes de eliminar: Gasto total: " + presupuesto.getGastoTotal());
            System.out.println("Presupuesto antes de eliminar: " + presupuesto.getPresupuesto());

            presupuesto.eliminarMovimiento(0);

            System.out.println("Después de eliminar: Gasto total: " + presupuesto.getGastoTotal());
            System.out.println("Presupuesto después de eliminar: " + presupuesto.getPresupuesto());

            assertEquals(0.0, presupuesto.getGastoTotal(), "El gasto total debería ser cero después de eliminar");
            assertEquals(2000.0, presupuesto.getPresupuesto(), "El presupuesto debería reflejar la eliminación correctamente");
            assertEquals(0, presupuesto.getNumMovimientos(), "El número de movimientos debería ser cero después de eliminar");
        }
        /**
         * Prueba el método {@link Presupuesto#consultarMovimientos()}.
         * Se verifica que los movimientos puedan ser listados correctamente.
         */
        @Test
        void testConsultarMovimientos() {
            Presupuesto presupuesto = new Presupuesto(3000.0, new Date());
            presupuesto.agregarMovimiento(new Ingreso("Venta de productos", 500.0, new Date(), new Categoria("Ventas")));
            presupuesto.agregarMovimiento(new Gasto("Pago alquiler", 800.0, new Date(), new Categoria("Vivienda")));

            String resultado = presupuesto.consultarMovimientos();

            System.out.println("Probando consultarMovimientos...");
            System.out.println("Salida obtenida:\n" + resultado);

            assertTrue(resultado.contains("Venta de productos"));
            assertTrue(resultado.contains("Pago alquiler"));
        }

        /**
         * Prueba el método {@link Presupuesto#toString()}.
         * Se verifica que la representación en cadena tenga el formato correcto.
         */
        @Test
        void testToString() {
            Usuario usuario = new Usuario("Ana", "password456", "ana@example.com", 0, 1);
            Presupuesto presupuesto = new Presupuesto(5000.0, new Date(), usuario);
            String resultado = presupuesto.toString();

            System.out.println("Probando toString...");
            System.out.println("Salida obtenida:\n" + resultado);

            assertTrue(resultado.contains("Presupuesto{"), "La salida debería contener 'Presupuesto{'");
            assertTrue(resultado.contains("usuario=Ana"), "La salida debería contener 'usuario=Ana'");
        }
    }