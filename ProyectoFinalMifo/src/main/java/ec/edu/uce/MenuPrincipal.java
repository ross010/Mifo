package ec.edu.uce;
import java.util.Scanner;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.dominio.Empresa;
import org.jetbrains.annotations.Nullable;
public class MenuPrincipal {
    private Scanner entrada = new Scanner(System.in);
    private Usuario[] usuario = new Usuario[50];
    private int indiceUsuarios = 0;
    private Empresa empresa = new Empresa();
    public void mostrarMenuPrincipal() throws ExcepcionMifo {
        while (true) {
            System.out.println();
            System.out.println("  ---------------------------------------  ");
            System.out.println(" |             MENÚ PRINCIPAL             |");
            System.out.println("  --------------------------------------- |");
            System.out.println(" |  1. Crear Cuenta                       |");
            System.out.println(" |  2. Ingresar al Sistema                |");
            System.out.println(" |  3. Salir                              |");
            System.out.println("  ---------------------------------------");
            System.out.println();
            System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
            System.out.print("");
            int seleccion = ComprobacionMenu.validarOpcionMenu(entrada,3);
            switch (seleccion) {
                case 1:
                    System.out.println();
                    System.out.println("  ---------------------------------------  ");
                    System.out.println(" |             Crear Cuenta              |");
                    System.out.println("  ---------------------------------------  ");
                    crearCuenta();
                    break;

                case 2:
                    System.out.println();
                    System.out.println("  ---------------------------------------  ");
                    System.out.println(" |          Ingresar al sistema          |");
                    System.out.println("  ---------------------------------------  ");
                    ingresarAlSistema();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("                                     Cerrando el sistema.");
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println(" Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                    System.exit(0);
            }
        }
    }
    private void ingresarAlSistema() throws ExcepcionMifo {
        System.out.println();
        System.out.print(" | Nombre de usuario: ");
        String nombre = entrada.nextLine().trim();
        System.out.print(" | Contraseña: ");
        String contrasena = entrada.nextLine().trim();
        System.out.println("  ---------------------------------------  ");
        System.out.println();
        try {
            Usuario usuarioEncontrado = buscarUsuario(nombre, contrasena);
            if (usuarioEncontrado != null) {
                System.out.println();
                System.out.println(" Ingreso exitoso. Bienvenido, " + nombre + "!");
                new MenuMifo(usuarioEncontrado);
            } else {
                System.out.println();
                System.out.println(" Usuario encontrado, pero contraseña incorrecta. Inténtelo de nuevo.");
            }
        } catch (ExcepcionMifo.UsuarioNoEncontradoExcepcion e) {
            System.out.println();
            System.out.println(" Usuario incorrecto o no encontrado. Inténtelo de nuevo.");
        }
    }
    @Nullable
    private Usuario buscarUsuario(String nombre, String contrasena) throws ExcepcionMifo {
        for (int i = 0; i < indiceUsuarios; i++) {
            Usuario u = usuario[i];
            if (u.getNombre().equals(nombre)) {
                if (u.getContrasena().equals(contrasena)) {
                    return u;
                } else {
                    return null;
                }
            }
        }
        throw new ExcepcionMifo.UsuarioNoEncontradoExcepcion("Usuario '" + nombre + "' no encontrado.");
    }
    private void crearCuenta() {
        String nombre;
        String contrasena;
        String correo;
        while (true) {
            System.out.println();
            System.out.print(" | Ingrese un nombre: ");
            nombre = entrada.nextLine().trim();
            if (!ComprobacionMenu.validarNombreUsuario(nombre)) {
                System.out.println();
                System.out.println(" El nombre solo puede contener letras.");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println();
            System.out.print(" | Ingrese una contraseña: ");
            contrasena = entrada.nextLine().trim();
            if (!ComprobacionMenu.validarContrasena(contrasena)) {
                System.out.println();
                System.out.println(" La contraseña no es válida. Asegúrate de incluir los elementos necesarios.");
                System.out.println(" Debe contener al menos una letra.");
                System.out.println(" Debe contener al menos un dígito.");
                System.out.println(" Debe tener una longitud mínima de 8 caracteres.");
            } else {
                break;
            }
        }
        while (true) {
            System.out.println();
            System.out.print(" | Ingrese un correo electrónico: ");
            correo = entrada.nextLine().trim();
            if (!ComprobacionMenu.validarCorreo(correo)) {
                System.out.println();
                System.out.println(" Correo electrónico inválido. Intente con uno válido.");
            } else {
                break;
            }
        }
        System.out.println();
        for (int i = 0; i < indiceUsuarios; i++) {
            Usuario u = usuario[i];
            if (u.getNombre().equals(nombre)) {
                System.out.println();
                System.out.println(" Nombre en uso. Intente con uno diferente.");
                return;

            }
        }
        String resultadoCreacion = empresa.agregarUsuarioConCodigo(nombre, contrasena, correo);
        System.out.println(resultadoCreacion);
        int codigo = empresa.agregarCodigoUsuario() - 1;
        usuario[indiceUsuarios++] = new Usuario(nombre, contrasena, correo, 0,codigo);
        System.out.println();
        System.out.println("        Su cuenta ha sido registrada exitosamente. Ya puede ingresar al sistema");
        System.out.println();
    }
    public static void main(String[] args) throws ExcepcionMifo {
        new MenuPrincipal().mostrarMenuPrincipal();
    }
}