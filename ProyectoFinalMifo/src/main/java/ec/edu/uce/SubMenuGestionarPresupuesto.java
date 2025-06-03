package ec.edu.uce;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Presupuesto;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class SubMenuGestionarPresupuesto {
    Scanner entrada = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Usuario usuario;
    public SubMenuGestionarPresupuesto(Usuario usuario) {
        this.usuario = usuario;
    }
    public void mostrarMenuGestionarPresupuesto() {
        System.out.println();
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |          Gestionar presupuesto         |");
        System.out.println("  --------------------------------------- |");
        System.out.println(" |  1. Ingresar presupuesto               |");
        System.out.println(" |  2. Editar presupuesto                 |");
        System.out.println(" |  3. Eliminar presupuesto               |");
        System.out.println(" |  4. Consultar presupuesto              |");
        System.out.println(" |  5. Volver al menú principal           |");
        System.out.println(" |  6. Salir del programa                 |");
        System.out.println("   _______________________________________ ");
        System.out.println();
        System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
    }
    public void menuGestionarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        boolean continuar = true;
        while (continuar) {
            mostrarMenuGestionarPresupuesto();
            int seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 6);
            switch (seleccion) {
                case 1:
                    try {
                        ingresarPresupuesto();
                    } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
                        System.out.println("Hubo un error al ingresar el presupuesto: " + e.getMessage());
                    }
                    break;
                case 2:
                    editarPresupuesto();
                    break;
                case 3:
                    eliminarPresupuesto();
                    break;
                case 4:
                    consultarPresupuesto();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    continuar = false;
                    break;
                case 6:
                    System.out.println();
                    System.out.println("                                      Cerrando el sistema.");
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                    System.exit(0);
                    break;
            }
            System.out.println();
        }
    }
    public void ingresarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        System.out.println();
        System.out.println("    ------------------------------------------- ");
        double presupuesto = 0;
        while (presupuesto == 0) {
            System.out.print(" | Ingrese el presupuesto: ");
            presupuesto = ComprobacionMenu.validarMonto(entrada);
        }

        Date fecha = null;
        while (fecha == null) {
            System.out.println();
            System.out.print(" | Ingrese la fecha (dd/MM/yyyy): ");
            String fechaStr = entrada.nextLine();
            fecha = ComprobacionMenu.validarFecha(fechaStr);
            if (fecha == null) {
                System.out.println();
                System.out.println("Fecha inválida, no se puede crear el presupuesto.");
            }
        }

        try {
            usuario.crearPresupuesto(presupuesto, fecha);
            System.out.println();
            System.out.println(" -> Presupuesto guardado con éxito. ");
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            System.out.println();
            System.out.println(e.getMessage());
        }
    }

    public void editarPresupuesto() {
        System.out.println(" ----------------------------------------------");
        Presupuesto[] presupuestos = usuario.getPresupuestos();

        if (presupuestos.length == 0) {
            System.out.println();
            System.out.println(" No hay presupuestos guardados para editar. ");
            return;
        }

        consultarPresupuesto();
        System.out.println();
        System.out.print(" Seleccione el índice del presupuesto a editar: ");
        int indice;
        try {
            indice = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Índice no válido. ");
            return;
        }

        if (indice < 0 || indice >= presupuestos.length) {
            System.out.println(" Índice no válido. ");
            return;
        }
        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.println();
                System.out.print(" | Ingrese el nuevo presupuesto: ");
                double presupuesto = ComprobacionMenu.validarMonto(entrada.nextLine());
                if (presupuesto == 0) {
                    System.out.println("| El monto ingresado no es válido. ");
                    continue;
                }
                System.out.print(" | Ingrese la nueva fecha (dd/MM/yyyy): ");
                String fechaStr = entrada.nextLine();
                Date fecha = dateFormat.parse(fechaStr);

                Presupuesto p = presupuestos[indice];
                p.setPresupuesto(presupuesto);
                p.setFecha(fecha);
                System.out.println();
                System.out.println(" -> Presupuesto editado con éxito. ");
                inputValido = true;
            } catch (ParseException e) {
                System.out.println(" Error al editar presupuesto: Formato de fecha incorrecto. ");
            }
        }
    }
    public void eliminarPresupuesto() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        System.out.println("  --------------------------------------------------------");
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println(" No hay presupuestos guardados para eliminar. ");
            return;
        }
        consultarPresupuesto();
        System.out.println();
        System.out.print(" ¿Está seguro de que desea eliminar un presupuesto? (s/n): ");
        String confirmacion = entrada.nextLine();
        if (confirmacion.equalsIgnoreCase("s")) {
            System.out.println();
            System.out.print(" Seleccione el índice del presupuesto a eliminar: ");
            int indice;
            try {
                indice = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Índice no válido.");
                return;
            }

            if (indice < 0 || indice >= presupuestos.length) {
                System.out.println(" Índice no válido.");
                return;
            }

            usuario.eliminarPresupuesto(indice);
            System.out.println();
            System.out.println(" Presupuesto eliminado correctamente.");
        } else {
            System.out.println(" Operación cancelada.");
        }
    }
    public void consultarPresupuesto() {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        System.out.println();
        if (presupuestos == null || presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados. Por favor, cree un presupuesto primero.");
            return;
        } else {
            for (int i = 0; i < presupuestos.length; i++) {
                Presupuesto p = presupuestos[i];
                System.out.println(i + ") Presupuesto: " + p.getPresupuesto() + ", Fecha: " + dateFormat.format(p.getFecha()));
            }
        }
    }
}
