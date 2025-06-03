package ec.edu.uce;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ExcepcionMifo;
class MenuMifoTest {
    public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Usuario usuario = new Usuario("admin", "contraseña", "admin@gmail.com",0, 1);
        MenuMifo menuMifo = new MenuMifo(usuario);
        menuMifo.menuMifo();
    }
}

