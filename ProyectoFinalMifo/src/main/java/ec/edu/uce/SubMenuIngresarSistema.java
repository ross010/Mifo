/**
 * Clase que representa el submenú de ingreso al sistema en MIFO.
 * Permite al usuario gestionar sus credenciales, incluyendo edición, eliminación y consulta.
 */
package ec.edu.uce;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Scanner;
public class SubMenuIngresarSistema {

    /**
     * Scanner para leer la entrada del usuario desde la consola.
     */
    private Scanner entrada = new Scanner(System.in);

    /**
     * Instancia del usuario que ha iniciado sesión.
     */
    private Usuario usuario;

    /**
     * Constructor de la clase que inicializa el usuario actual.
     *
     * @param usuario Instancia del usuario autenticado.
     */
    public SubMenuIngresarSistema(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Muestra el submenú de ingreso al sistema y procesa la opción elegida por el usuario.
     */
    public void menuIngresarSistema() {
        mostrarMenuIngresarSistema();
        int seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 5);
        procesarOpcionIngresarSistema(seleccion);
    }

    /**
     * Muestra las opciones disponibles dentro del submenú de ingreso al sistema.
     */
    private void mostrarMenuIngresarSistema() {
        System.out.println();
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |            Ingresar al sistema         |");
        System.out.println("  --------------------------------------- |");
        System.out.println(" |  1. Editar credenciales                |");
        System.out.println(" |  2. Eliminar credenciales              |");
        System.out.println(" |  3. Consultar credenciales             |");
        System.out.println(" |  4. Volver al menú principal           |");
        System.out.println(" |  5. Salir del programa                 |");
        System.out.println("  ---------------------------------------");
        System.out.println();
        System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
    }

    /**
     * Procesa la opción elegida en el menú e invoca la funcionalidad correspondiente.
     *
     * @param seleccion Opción elegida por el usuario.
     */
    private void procesarOpcionIngresarSistema(int seleccion) {
        switch (seleccion) {
            case 1:
                try {
                    if (validarCredenciales()) {
                        editarCredenciales();
                    } else {
                        System.out.println("Contraseña incorrecta. No se pueden editar las credenciales.");
                    }
                } catch (ExcepcionMifo.CredencialesInvalidasExcepcion e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    if (validarCredenciales()) {
                        eliminarCredenciales();
                        System.exit(0);
                    } else {
                        System.out.println("Contraseña incorrecta. No se pueden eliminar las credenciales.");
                    }
                } catch (ExcepcionMifo.CredencialesInvalidasExcepcion e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    if (validarCredenciales()) {
                        consultarCredenciales();
                    } else {
                        System.out.println("Contraseña incorrecta. No se pueden consultar las credenciales.");
                    }
                } catch (ExcepcionMifo.CredencialesInvalidasExcepcion e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.println();
                System.out.println("Volviendo al menú principal...");
                return;
            case 5:
                System.out.println();
                System.out.println("                                     Cerrando el sistema.");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                System.exit(0);
                break;
        }
    }

    /**
     * Valida las credenciales del usuario solicitando su contraseña actual.
     *
     * @return true si la contraseña es válida, false en caso contrario.
     * @throws ExcepcionMifo.CredencialesInvalidasExcepcion Si la contraseña ingresada es incorrecta.
     */
    private boolean validarCredenciales() throws ExcepcionMifo.CredencialesInvalidasExcepcion {
        System.out.print(" | Ingrese su contraseña actual: ");
        String contrasena = entrada.nextLine();
        if (usuario.getContrasena().equals(contrasena)) {
            return true;
        } else {
            throw new ExcepcionMifo.CredencialesInvalidasExcepcion("Contraseña incorrecta. No se pueden validar las credenciales.");
        }
    }

    /**
     * Permite al usuario editar su nombre y contraseña verificando su validez.
     */
    private void editarCredenciales() {
        String nuevoNombre;
        String nuevaContrasena;

        // Validación del nombre de usuario
        do {
            System.out.print(" | Ingrese nuevo nombre (solo letras): ");
            nuevoNombre = entrada.nextLine();
            if (!nuevoNombre.matches("[a-zA-Z]+")) {
                System.out.println("Error: El nombre debe contener solo letras.");
            }
        } while (!nuevoNombre.matches("[a-zA-Z]+"));

        // Validación de la nueva contraseña
        do {
            System.out.print(" | Ingrese nueva contraseña (mínimo 8 caracteres con al menos una letra): ");
            nuevaContrasena = entrada.nextLine();
            if (nuevaContrasena.length() < 8 || !nuevaContrasena.matches(".*[a-zA-Z].*")) {
                System.out.println(" La contraseña debe tener al menos 8 caracteres con al menos una letra.");
            }
        } while (nuevaContrasena.length() < 8 || !nuevaContrasena.matches(".*[a-zA-Z].*"));

        usuario.setNombre(nuevoNombre);
        usuario.setContrasena(nuevaContrasena);
        System.out.println("Credenciales editadas correctamente.");
    }

    /**
     * Elimina las credenciales del usuario borrando su nombre y contraseña.
     */
    private void eliminarCredenciales() {
        usuario.setNombre("");
        usuario.setContrasena("");
        System.out.println("Credenciales eliminadas correctamente.");
        System.out.println("Para volver a utilizar Mifo, deberá crear una nueva cuenta.");
    }

    /**
     * Consulta y muestra en pantalla las credenciales del usuario actual.
     */
    private void consultarCredenciales() {
        System.out.println(" | Código: " + usuario.getCodigo());
        System.out.println(" | Nombre: " + usuario.getNombre());
        System.out.println(" | Contraseña: " + usuario.getContrasena());
    }
}