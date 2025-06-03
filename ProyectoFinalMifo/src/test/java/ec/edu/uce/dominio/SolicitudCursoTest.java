package ec.edu.uce.dominio;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SolicitudCursoTest {
        private EducacionFinanciera curso;
        private SolicitudCurso solicitud;
        /**
         * Configuración inicial antes de cada prueba.
         * Se crea un curso de ejemplo para reutilizar en las pruebas.
         */
        @BeforeEach
        public void setUp() {
            curso = new EducacionFinanciera("Ahorro Básico", "Curso sobre ahorro", 4,
                    "Principiante", "ABC123", new Categoria("Educación", TipoMovimiento.GASTO),
                    new Date());
        }

        /**
         * Verifica que el constructor por defecto inicializa la fecha de solicitud correctamente.
         */
        @Test
        public void testConstructorPorDefecto() {
            solicitud = new SolicitudCurso();
            assertNotNull(solicitud.getFechaSolicitud(), "La fecha de solicitud no debe ser nula");
        }

        /**
         * Verifica que el constructor con parámetro asigna el curso correctamente y la fecha no sea nula.
         */
        @Test
        public void testConstructorConCurso() {
            solicitud = new SolicitudCurso(curso);
            assertEquals(curso, solicitud.getCursoSolicitado(), "El curso asignado debe coincidir con el esperado");
            assertNotNull(solicitud.getFechaSolicitud(), "La fecha de solicitud no debe ser nula");
        }

        /**
         * Verifica los métodos getter y setter para el curso solicitado.
         */
        @Test
        public void testGetSetCursoSolicitado() {
            solicitud = new SolicitudCurso();
            solicitud.setCursoSolicitado(curso);
            assertEquals(curso, solicitud.getCursoSolicitado(), "Debe devolver el curso correctamente asignado");
        }

        /**
         * Verifica los métodos getter y setter para la fecha de solicitud.
         */
        @Test
        public void testGetSetFechaSolicitud() {
            solicitud = new SolicitudCurso();
            Date nuevaFecha = new Date();
            solicitud.setFechaSolicitud(nuevaFecha);
            assertEquals(nuevaFecha, solicitud.getFechaSolicitud(), "Debe devolver la fecha correctamente asignada");
        }

        /**
         * Verifica que el método toString incluye datos relevantes del curso y la fecha.
         */
        @Test
        public void testToString() {
            solicitud = new SolicitudCurso(curso);
            String result = solicitud.toString();
            assertTrue(result.contains("Solicitud de curso"), "La cadena debe contener 'Solicitud de curso'");
            assertTrue(result.contains(curso.getTitulo()), "La cadena debe contener el título del curso");
        }
    }








