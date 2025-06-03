package ec.edu.uce.util;

public class ExcepcionMifo extends Exception {
    public ExcepcionMifo(String message) { super(message); }
    public static class MovimientoInvalidoExcepcion extends ExcepcionMifo {
        public MovimientoInvalidoExcepcion(String mensaje) {
            super(mensaje);
        }
    }
    public static class UsuarioNoEncontradoExcepcion extends ExcepcionMifo {
        public UsuarioNoEncontradoExcepcion(String nombreUsuario) {
            super("Usuario '" + nombreUsuario + "' no encontrado.");
        }
    }
    public static class CredencialesInvalidasExcepcion extends ExcepcionMifo {
        public CredencialesInvalidasExcepcion(String mensaje) {
            super(mensaje);
        }
    }
    public static class NombreInvalidoExcepcion extends ExcepcionMifo {
        public NombreInvalidoExcepcion(String mensaje) {
            super(mensaje);
        }
    }
    public static class ContrasenaInvalidaExcepcion extends ExcepcionMifo {
        public ContrasenaInvalidaExcepcion(String mensaje) {
            super(mensaje);
        }
    }
    public static class SaldoInsuficienteExcepcion extends ExcepcionMifo {
        public SaldoInsuficienteExcepcion(String mensaje) {
            super(mensaje);
        }
    }

}

