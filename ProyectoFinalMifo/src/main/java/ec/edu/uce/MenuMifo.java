package ec.edu.uce;
import java.util.Scanner;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ComprobacionMenu;
/**
 * Esta clase MenuMifo representa el menú principal de nuestro sistema MIFO.
 * Este sistema permite la gestión financiera personal a través de varios submenús.
 * El menú se presenta en consola y permite al usuario navegar por las distintas
 * funcionalidades del sistema.
 *
 */
public class MenuMifo {
    // Scanner para la entrada de datos del usuario.
    Scanner entrada = new Scanner(System.in);
    /**
     * Clase que agrupa los submenús relacionados con la gestión financiera.
     * Contiene instancias finales de varios submenús que permiten al usuario
     * ingresar al sistema, gestionar objetivos financieros, educación financiera,
     * movimientos, presupuestos y categorías.
     */
    private final SubMenuIngresarSistema subMenuIngresarSistema;
    private final SubMenuGestionarObjetivosFinancieros subMenuGestionarObjetivosFinancieros;
    private final SubMenuGestionarEducacionFinanciera subMenuGestionarEducacionFinanciera;
    private final SubMenuGestionarMovimiento subMenuGestionarMovimiento;
    private final SubMenuGestionarPresupuesto subMenuGestionarPresupuesto;
    private final SubMenuGestionarCategoria subMenuGestionarCategoria;
    // Usuario que accede al sistema.
    private Usuario usuario;
    /**
     * Constructor de la clase
     * @param usuario Usuario que accede al sistema.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error con los movimientos financieros.
     */
    public MenuMifo(Usuario usuario) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        this.usuario = usuario;
        this.subMenuIngresarSistema = new SubMenuIngresarSistema(usuario);
        this.subMenuGestionarObjetivosFinancieros = new SubMenuGestionarObjetivosFinancieros();
        this.subMenuGestionarEducacionFinanciera = new SubMenuGestionarEducacionFinanciera();
        this.subMenuGestionarPresupuesto = new SubMenuGestionarPresupuesto(usuario);
        this.subMenuGestionarCategoria = new SubMenuGestionarCategoria();
        this.subMenuGestionarMovimiento = new SubMenuGestionarMovimiento(usuario);
        menuMifo();
    }
    /**
     * Método que gestiona la ejecución del menú principal.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error al seleccionar una opción.
     */
    public void menuMifo() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        int seleccion;
        do {
            mostrarMenuMifo();
            seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 7);
            procesarOpcionMenuMifo(seleccion);
        } while (seleccion != 7);
    }
    /**
     * Muestra el menú principal del sistema MIFO en la consola.
     */
    private void mostrarMenuMifo() {
        System.out.println();
        System.out.println("  ________________________________________ ");
        System.out.println(" |                 MIFO                   |");
        System.out.println(" |          Mis Finanzas Foráneas         |");
        System.out.println(" | Tu gestión financiera comienza aquí    |");
        System.out.println(" |          BIENVENIDO AL MENU            |");
        System.out.println("  ________________________________________ ");
        System.out.println(" |                                        |");
        System.out.println(" |  1. Ingresar al Sistema                |");
        System.out.println(" |  2. Gestionar Presupuesto              |");
        System.out.println(" |  3. Gestionar Categoria                |");
        System.out.println(" |  4. Gestionar Educación Financiera     |");
        System.out.println(" |  5. Gestionar Objetivos Financieros    |");
        System.out.println(" |  6. Gestionar Movimiento               |");
        System.out.println(" |  7. Salir                              |");
        System.out.println("   _______________________________________ ");
        System.out.println();
        System.out.print(" Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
    }
    /**
     * Procesa la opción seleccionada por el usuario en el menú principal.
     * @param seleccion Opción elegida por el usuario.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error en la gestión financiera.
     */
    private void procesarOpcionMenuMifo(int seleccion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        switch (seleccion) {
            case 1:
                subMenuIngresarSistema.menuIngresarSistema();
                break;
            case 2:
                subMenuGestionarPresupuesto.menuGestionarPresupuesto();
                break;
            case 3:
                subMenuGestionarCategoria.menuGestionarCategoria();
                break;
            case 4:
                subMenuGestionarEducacionFinanciera.menuGestionarEducacionFinanciera();
                break;
            case 5:
                subMenuGestionarObjetivosFinancieros.menuGestionarObjetivosFinancieros();
                break;
            case 6:
                subMenuGestionarMovimiento.menuGestionarMovimiento();
                break;
            case 7:
                System.out.println();
                System.out.println("                                     Cerrando el sistema.");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                System.exit(0);
                break;
        }
    }
    /**
     * Método principal que inicia la ejecución del sistema MIFO.
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error en la gestión financiera.
     */
    public static void main(String[] args) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Usuario usuario = new Usuario("Usuario predeterminado", "Contraseña predeterminado", "Correo predeterminado@example.com", 0,1);
        new MenuMifo(usuario);
    }
}
