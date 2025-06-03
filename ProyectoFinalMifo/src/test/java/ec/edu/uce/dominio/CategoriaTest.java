package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CategoriaTest {

    /**
     * Prueba el constructor por defecto de la clase Categoria
     * Se espera que la categoría tenga el nombre "Sin categoria" y un tipo de movimiento nulo.
     */
    @Test
    void testConstructorPorDefecto() {
        Categoria categoria = new Categoria();
        System.out.println("Probando constructor por defecto...");
        System.out.println("Nombre categoría esperado: Sin categoria | Actual: " + categoria.getNombreCategoria());
        System.out.println("Tipo movimiento esperado: null | Actual: " + categoria.getTipoMovimiento());

        assertEquals("Sin categoria", categoria.getNombreCategoria());
        assertNull(categoria.getTipoMovimiento());
    }

    /**
     * Prueba el constructor con un nombre específico.
     * Se espera que el nombre de la categoría sea el proporcionado y el tipo de movimiento sea nulo.
     */
    @Test
    void testConstructorConNombre() {
        Categoria categoria = new Categoria("Alimentación");
        System.out.println("Probando constructor con nombre...");
        System.out.println("Nombre categoría esperado: Alimentación | Actual: " + categoria.getNombreCategoria());

        assertEquals("Alimentación", categoria.getNombreCategoria());
        assertNull(categoria.getTipoMovimiento());
    }

    /**
     * Prueba el constructor que recibe un nombre y un tipo de movimiento.
     * Se verifica que ambos atributos sean correctamente asignados.
     */
    @Test
    void testConstructorCompleto() {
        TipoMovimiento movimiento = TipoMovimiento.valueOf("INGRESO");
        Categoria categoria = new Categoria("Salario", movimiento);
        System.out.println("Probando constructor completo...");
        System.out.println("Nombre categoría esperado: Salario | Actual: " + categoria.getNombreCategoria());
        System.out.println("Tipo movimiento esperado: INGRESO | Actual: " + categoria.getTipoMovimiento());

        assertEquals("Salario", categoria.getNombreCategoria());
        assertEquals(movimiento, categoria.getTipoMovimiento());
    }

    /**
     * Prueba los métodos setters de la clase {@link Categoria}.
     * Se verifica que los valores sean correctamente actualizados.
     */
    @Test
    void testSetters() {
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria("Transporte");
        TipoMovimiento movimiento = TipoMovimiento.valueOf("GASTO");
        categoria.setTipoMovimiento(movimiento);

        System.out.println("Probando setters...");
        System.out.println("Nuevo nombre categoría: " + categoria.getNombreCategoria());
        System.out.println("Nuevo tipo movimiento: " + categoria.getTipoMovimiento());

        assertEquals("Transporte", categoria.getNombreCategoria());
        assertEquals(movimiento, categoria.getTipoMovimiento());
    }

    /**
     * Prueba el método {@link Categoria#equals(Object)}.
     * Se verifica que dos categorías con el mismo nombre sean iguales.
     */
    @Test
    void testEquals() {
        Categoria categoria1 = new Categoria("Entretenimiento");
        Categoria categoria2 = new Categoria("Entretenimiento");
        Categoria categoria3 = new Categoria("Educación");

        System.out.println("Probando equals...");
        System.out.println("Comparando Entretenimiento con Entretenimiento (esperado: true) | Actual: " + categoria1.equals(categoria2));
        System.out.println("Comparando Entretenimiento con Educación (esperado: false) | Actual: " + categoria1.equals(categoria3));

        assertTrue(categoria1.equals(categoria2));
        assertFalse(categoria1.equals(categoria3));
    }

    /**
     * Prueba el método {@link Categoria#toString()}.
     * Se espera que la salida en cadena tenga el formato correcto.
     */
    @Test
    void testToString() {
        Categoria categoria = new Categoria("Viajes", TipoMovimiento.GASTO);
        String expected = "Categoria: Viajes, Tipo: GASTO";
        String actual = categoria.toString();

        System.out.println("Probando toString...");
        System.out.println("Salida esperada: " + expected);
        System.out.println("Salida actual: " + actual);

        assertEquals(expected, actual);
    }
}