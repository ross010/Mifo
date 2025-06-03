package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
class ObjetivoFinancieroTest {
        @Test
        public void testConstructorPorDefecto() {
            ObjetivoFinanciero objetivo = new ObjetivoFinanciero("Ahorro para viajes", 1000, new Date());
            System.out.println("\nProbando constructor por defecto...");
             System.out.println("Descripción: " + objetivo.getDescripcion());
            System.out.println("Monto: " + objetivo.getMonto());
            System.out.println("Fecha: " + objetivo.getFecha());
            System.out.println("Categoría: " + objetivo.getCategoria());

            assertNotNull(objetivo);
            assertEquals("Sin descripcion", objetivo.getDescripcion());
            assertEquals(0, objetivo.getMonto());
            assertNotNull(objetivo.getFecha());
            assertNotNull(objetivo.getCategoria());
        }

        @Test
        public void testConstructorConParametros() {
            Categoria categoria = new Categoria(); // Asegúrate de que sea válido
            ObjetivoFinanciero objetivo = new ObjetivoFinanciero("Estudio", 1500, new Date(), categoria);

            System.out.println("\nProbando constructor con parámetros...");
            System.out.println("Descripción: " + objetivo.getDescripcion());
            System.out.println("Monto: " + objetivo.getMonto());
            System.out.println("Fecha: " + objetivo.getFecha());
            System.out.println("Categoría: " + objetivo.getCategoria());

            assertNotNull(objetivo);
            assertEquals("Estudio", objetivo.getDescripcion());
            assertEquals(1500, objetivo.getMonto());
            assertNotNull(objetivo.getFecha());
            assertEquals(categoria, objetivo.getCategoria());
        }

        @Test
        public void testEquals() {
            Categoria categoria1 = new Categoria();
            Categoria categoria2 = new Categoria();
            ObjetivoFinanciero objetivo1 = new ObjetivoFinanciero("Estudio", 1500, new Date(), categoria1);
            ObjetivoFinanciero objetivo2 = new ObjetivoFinanciero("Estudio", 1500, new Date(), categoria1);
            ObjetivoFinanciero objetivo3 = new ObjetivoFinanciero("Salud", 1000, new Date(), categoria2);

            System.out.println("\nComparando objetivos financieros...");
            System.out.println("¿Objetivo1 es igual a Objetivo2?: " + objetivo1.equals(objetivo2));
            System.out.println("¿Objetivo1 es igual a Objetivo3?: " + objetivo1.equals(objetivo3));
            System.out.println("¿Objetivo1 comparado con null?: " + objetivo1.equals(null));
            System.out.println("¿Objetivo1 comparado con un string?: " + objetivo1.equals("string"));

            assertTrue(objetivo1.equals(objetivo2));
            assertFalse(objetivo1.equals(objetivo3));
            assertFalse(objetivo1.equals(null));
            assertFalse(objetivo1.equals("string"));
        }

        @Test
        public void testToString() {
            Categoria categoria = new Categoria();
            ObjetivoFinanciero objetivo = new ObjetivoFinanciero("Estudio", 1500, new Date(), categoria);

            System.out.println("\nProbando método toString...");
            System.out.println("Salida esperada:");
            System.out.println("Objetivo Financiero:\n" +
                    "Descripción: Estudio\n" +
                    "Monto: 1500.0\n" +
                    "Fecha: " + objetivo.getFecha() + "\n" +
                    "Categoría: " + categoria + "\n");

            System.out.println("Salida obtenida:");
            System.out.println(objetivo.toString());

            String expectedToString = "Objetivo Financiero:\n" +
                    "Descripción: Estudio\n" +
                    "Monto: 1500.0\n" +
                    "Fecha: " + objetivo.getFecha() + "\n" +
                    "Categoría: " + categoria + "\n";

            assertEquals(expectedToString, objetivo.toString());
        }
    }
