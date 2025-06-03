package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import java.util.Date;
import ec.edu.uce.util.ExcepcionMifo;
import static org.junit.jupiter.api.Assertions.*;
class EducacionFinancieraTest {
        @Test
        void testAgregarSolicitud() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            EducacionFinanciera curso = new EducacionFinanciera();
            SolicitudCurso solicitud = new SolicitudCurso();
            curso.agregarSolicitud(solicitud);
            System.out.println("Probando agregarSolicitud...");
            System.out.println("Tamaño esperado de la lista de solicitudes: 1 | Actual: " + curso.getSolicitudes().length);
            assertEquals(1, curso.getSolicitudes().length);
        }

        @Test
        void testEliminarSolicitudPorObjeto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            EducacionFinanciera curso = new EducacionFinanciera();
            SolicitudCurso solicitud = new SolicitudCurso();
            curso.agregarSolicitud(solicitud);

            // Ahora eliminamos la solicitud usando el método que recibe objeto
            curso.eliminarSolicitud(solicitud);
            System.out.println("Probando eliminarSolicitud por objeto...");
            System.out.println("Tamaño esperado de la lista de solicitudes después de eliminar: 0 | Actual: " + curso.getSolicitudes().length);
            assertEquals(0, curso.getSolicitudes().length);
        }

        @Test
        void testEliminarSolicitudPorIndice() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
            EducacionFinanciera curso = new EducacionFinanciera();
            SolicitudCurso solicitud1 = new SolicitudCurso();
            SolicitudCurso solicitud2 = new SolicitudCurso();
            curso.agregarSolicitud(solicitud1);
            curso.agregarSolicitud(solicitud2);

            // Eliminamos por índice (por ejemplo, eliminar la primera solicitud)
            curso.eliminarSolicitud(0);

            System.out.println("Probando eliminarSolicitud por índice...");
            System.out.println("Tamaño esperado de la lista de solicitudes después de eliminar: 1 | Actual: " + curso.getSolicitudes().length);

            assertEquals(1, curso.getSolicitudes().length);
            // Verificamos que la solicitud remanente es la segunda que agregamos
            assertEquals(solicitud2, curso.getSolicitudes()[0]);
        }
    }

