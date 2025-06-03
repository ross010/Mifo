package ec.edu.uce.dominio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Date;
import ec.edu.uce.util.ExcepcionMifo;
class MovimientoTest {
    @Test
    void testConstructorConDescripcionMontoCategoria() {
        Categoria categoria = new Categoria("Educación");

        Movimiento movimiento = new Movimiento("Pago de matrícula", 500.0, new Date(), categoria) {
        @Override
            public boolean registrar() {
                return false;
            }

            @Override
            public boolean validarDuplicado(Movimiento var1) {
                return false;
            }

            @Override
            public void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, ExcepcionMifo.MovimientoInvalidoExcepcion {
                // implementación vacía para test
            }
        };

        System.out.println("Probando constructor con descripción, monto y categoría...");
        System.out.println("Descripción esperada: Pago de matrícula | Actual: " + movimiento.getDescripcion());
        System.out.println("Monto esperado: 500.0 | Actual: " + movimiento.getMonto());
        System.out.println("Categoría esperada: Educación | Actual: " + movimiento.getCategoria().getNombreCategoria());

        assertEquals("Pago de matrícula", movimiento.getDescripcion());
        assertEquals(500.0, movimiento.getMonto());
        assertEquals("Educación", movimiento.getCategoria().getNombreCategoria());
    }

    @Test
    void testToString() {
        Categoria categoria = new Categoria("Educación");

        Movimiento movimiento = new Movimiento("Compra de libros", 200.0, new Date(), categoria) {
            @Override
            public boolean registrar() {
                return false;
            }

            @Override
            public boolean validarDuplicado(Movimiento var1) {
                return false;
            }

            @Override
            public void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, ExcepcionMifo.MovimientoInvalidoExcepcion {
                // implementación vacía para test
            }
        };

        String resultado = movimiento.toString();

        System.out.println("Probando toString...");
        System.out.println("Salida obtenida:\n" + resultado);

        assertTrue(resultado.contains("Descripcion= Compra de libros"));
        assertTrue(resultado.contains("Monto= 200.0"));
        assertTrue(resultado.contains("Categoria= Educación"));
    }
}
