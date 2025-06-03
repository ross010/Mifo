/**
 * Clase que representa el submenú para gestionar movimientos financieros en el sistema MIFO.
 * Permite a los usuarios crear, editar, consultar y eliminar movimientos.
 */
package ec.edu.uce;
import ec.edu.uce.dominio.*;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ec.edu.uce.dominio.TipoMovimiento;
public class SubMenuGestionarMovimiento {
    /**
     * Scanner para leer la entrada del usuario desde la consola.
     */
    private final Scanner entrada;

    /**
     * Usuario autenticado que gestionará los movimientos.
     */
    private final Usuario usuario;

    /**
     * Constructor que inicializa el submenú con un usuario autenticado.
     *
     * @param usuario Instancia del usuario actual.
     */
    public SubMenuGestionarMovimiento(Usuario usuario) {
        this.entrada = new Scanner(System.in);
        this.usuario = usuario;
    }

    /**
     * Muestra el menú de gestión de movimientos y procesa la opción elegida por el usuario.
     *
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error en la gestión del movimiento.
     */
    public void menuGestionarMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        int seleccion;
        do {
            mostrarMenuGestionarMovimiento();
            seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 6);
            procesarOpcionGestionarMovimiento(seleccion);
        } while (seleccion != 6);
    }

    /**
     * Muestra las opciones disponibles en el submenú de gestión de movimientos.
     */
    private void mostrarMenuGestionarMovimiento() {
        System.out.println();
        System.out.println("   ------------------------------------- ");
        System.out.println(" |          Gestionar Movimiento         |");
        System.out.println(" |--------------------------------------  ");
        System.out.println(" | 1. Crear Movimiento                   |");
        System.out.println(" | 2. Editar Movimiento                  |");
        System.out.println(" | 3. Consultar Movimiento               |");
        System.out.println(" | 4. Eliminar Movimiento                |");
        System.out.println(" | 5. Volver al menú principal           |");
        System.out.println(" | 6. Salir del programa                 |");
        System.out.println("  ---------------------------------------");
        System.out.println();
        System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
    }

    /**
     * Procesa la opción elegida en el menú e invoca la funcionalidad correspondiente.
     *
     * @param seleccion Opción elegida por el usuario.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error en la gestión del movimiento.
     */
    private void procesarOpcionGestionarMovimiento(int seleccion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        switch (seleccion) {
            case 1:
                crearMovimiento();
                break;
            case 2:
                editarMovimiento();
                break;
            case 3:
                consultarMovimiento();
                break;
            case 4:
                eliminarMovimiento();
                break;
            case 5:
                System.out.println("Volviendo al menú principal...");
                break;
            case 6:
                System.out.println("\n                                      Cerrando el sistema.");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
                break;
        }
    }

    /**
     * Valida que la descripción ingresada por el usuario cumpla con los requisitos mínimos.
     *
     * @return Descripción validada del movimiento.
     */
    private String validarDescripcion() {
        String descripcion;
        while (true) {
            descripcion = entrada.nextLine().trim();
            if (descripcion.isEmpty()) {
                System.out.print("La descripción no puede estar vacía. Intente de nuevo: ");
            } else if (descripcion.length() < 5) {
                System.out.print("La descripción debe tener al menos 5 letras. Intente de nuevo: ");
            } else {
                return descripcion;
            }
        }
    }

    /**
     * Valida que el monto ingresado por el usuario sea un número positivo.
     *
     * @return Monto validado del movimiento.
     */
    private double validarMonto() {
        while (true) {
            try {
                String input = entrada.nextLine().trim();
                double monto = Double.parseDouble(input);
                if (monto <= 0) {
                    System.out.print("Error: El monto debe ser un número positivo. Intenta de nuevo: ");
                } else {
                    return monto;
                }
            } catch (NumberFormatException e) {
                System.out.print("Error: Debe ingresar un número válido. Intenta de nuevo: ");
            }
        }
    }

    /**
     * Valida y convierte la fecha ingresada por el usuario en el formato correcto.
     *
     * @return Fecha validada en formato `Date`.
     */
    private Date validarFecha() {
        Date fecha = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        while (fecha == null) {
            String fechaStr = entrada.nextLine().trim();
            try {
                fecha = formato.parse(fechaStr);
            } catch (ParseException e) {
                System.out.print("Error: Fecha inválida, ingrese en formato dd/MM/yyyy: ");
            }
        }
        return fecha;
    }

    /**
     * Clase que representa el submenú para gestionar movimientos financieros en el sistema MIFO.
     * Permite a los usuarios crear, editar, consultar y eliminar movimientos dentro de presupuestos definidos.
     */
    private void crearMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados. Por favor, cree un presupuesto primero.");
            return;
        }

        // Selección de presupuesto para el movimiento
        System.out.println("Seleccione el presupuesto para este movimiento:");
        for (int i = 0; i < presupuestos.length; i++) {
            System.out.println((i + 1) + ") Presupuesto: " + presupuestos[i].getPresupuesto() + ", Fecha: " + presupuestos[i].getFecha());
        }

        int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
        Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

        // Selección del tipo de movimiento usando enum TipoMovimiento
        System.out.println("****************************");
        System.out.println("Seleccione el tipo de movimiento:");
        TipoMovimiento[] tipos = TipoMovimiento.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i].name());
        }
        int tipoSeleccionado = ComprobacionMenu.validarOpcionMenu(entrada, tipos.length);
        TipoMovimiento tipoMovimiento = tipos[tipoSeleccionado - 1];

        entrada.nextLine(); // limpiar buffer

        System.out.print("Ingrese la descripción: ");
        String descripcion = validarDescripcion();

        System.out.print("Ingrese el monto: ");
        double monto = validarMonto();

        System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
        Date fecha = validarFecha();

        Categoria categoria = new Categoria(tipoMovimiento.name(), tipoMovimiento);

        Movimiento movimiento;
        if (tipoMovimiento == TipoMovimiento.INGRESO) {
            movimiento = new Ingreso(descripcion, monto, fecha, categoria);
        } else {
            movimiento = new Gasto(descripcion, monto, fecha, categoria);
        }

        presupuestoSeleccionado.agregarMovimiento(movimiento);
        System.out.println("Movimiento creado con éxito: " + movimiento);
    }
    /**
     * Permite al usuario editar un movimiento existente dentro de un presupuesto seleccionado.
     *
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error en la edición del movimiento.
     */
    private void editarMovimiento() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos guardados.");
            return;
        }

        // Selección de presupuesto
        System.out.println("Seleccione el presupuesto:");
        for (int i = 0; i < presupuestos.length; i++) {
            System.out.println((i + 1) + ") Presupuesto: " + presupuestos[i].getPresupuesto());
        }

        int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
        Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

        Movimiento[] movimientos = presupuestoSeleccionado.getMovimientos();
        if (movimientos.length == 0) {
            System.out.println("No hay movimientos.");
            return;
        }

        mostrarMovimientos(movimientos);

        System.out.print("Ingrese el número del movimiento: ");
        int indice = ComprobacionMenu.validarOpcionMenu(entrada, movimientos.length) - 1;

        entrada.nextLine();
        System.out.print("Nueva descripción: ");
        String descripcion = validarDescripcion();

        System.out.print("Nuevo monto: ");
        double monto = validarMonto();

        System.out.print("Nueva fecha (dd/MM/yyyy): ");
        Date fecha = validarFecha();

        Movimiento mov = movimientos[indice];
        mov.setDescripcion(descripcion);
        mov.setMonto(monto);
        mov.setFecha(fecha);

        System.out.println("Movimiento actualizado.");
    }

    /**
     * Elimina un movimiento seleccionado por el usuario dentro de un presupuesto.
     */
    private void eliminarMovimiento() {
        try {
            Presupuesto[] presupuestos = usuario.getPresupuestos();
            if (presupuestos.length == 0) {
                System.out.println("No hay presupuestos.");
                return;
            }

            // Selección de presupuesto
            System.out.println("Seleccione el presupuesto:");
            for (int i = 0; i < presupuestos.length; i++) {
                System.out.println((i + 1) + ") Presupuesto: " + presupuestos[i].getPresupuesto());
            }

            int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
            Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

            Movimiento[] movimientos = presupuestoSeleccionado.getMovimientos();
            if (movimientos.length == 0) {
                System.out.println("No hay movimientos.");
                return;
            }

            mostrarMovimientos(movimientos);

            System.out.print("Número del movimiento a eliminar: ");
            int indice = ComprobacionMenu.validarOpcionMenu(entrada, movimientos.length) - 1;

            presupuestoSeleccionado.eliminarMovimiento(indice);
            System.out.println("Movimiento eliminado con éxito.");
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            Logger.getLogger(SubMenuGestionarMovimiento.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Consulta y muestra en pantalla los movimientos dentro de un presupuesto seleccionado.
     */
    private void consultarMovimiento() {
        Presupuesto[] presupuestos = usuario.getPresupuestos();
        if (presupuestos.length == 0) {
            System.out.println("No hay presupuestos.");
            return;
        }

        // Selección de presupuesto
        System.out.println("Seleccione el presupuesto:");
        for (int i = 0; i < presupuestos.length; i++) {
            System.out.println((i + 1) + ") Presupuesto: " + presupuestos[i].getPresupuesto());
        }

        int presupuestoIndex = ComprobacionMenu.validarOpcionMenu(entrada, presupuestos.length) - 1;
        Presupuesto presupuestoSeleccionado = presupuestos[presupuestoIndex];

        Movimiento[] movimientos = presupuestoSeleccionado.getMovimientos();
        if (movimientos.length == 0) {
            System.out.println("No hay movimientos.");
            return;
        }

        mostrarMovimientos(movimientos);
    }

    /**
     * Muestra en pantalla una lista de movimientos dentro de un presupuesto.
     *
     * @param movimientos Arreglo de movimientos a mostrar.
     */
    private void mostrarMovimientos(Movimiento[] movimientos) {
        for (int i = 0; i < movimientos.length; i++) {
            Movimiento m = movimientos[i];
            if (m != null) {
                System.out.println((i + 1) + ". " + m);
            }
        }
    }
}
