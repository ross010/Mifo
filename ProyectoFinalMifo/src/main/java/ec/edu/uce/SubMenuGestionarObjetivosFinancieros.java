package ec.edu.uce;
import ec.edu.uce.dominio.*;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class SubMenuGestionarObjetivosFinancieros {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Scanner entrada = new Scanner(System.in);
    private ObjetivoFinanciero[] objetivosArray = new ObjetivoFinanciero[100];
    private final Empresa empresa = new Empresa();
    private int objetivoCount = 0;
    private SubMenuGestionarCategoria subMenuCategoria = new SubMenuGestionarCategoria();
    public void menuGestionarObjetivosFinancieros() {
        int seleccion;
        do {
            mostrarMenuGestionarObjetivosFinancieros();
            seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 6);
            try {
                procesarOpcionGestionarObjetivosFinancieros(seleccion);
            } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (seleccion != 5 && seleccion != 6);
    }

    private void mostrarMenuGestionarObjetivosFinancieros() {
        System.out.println();
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |      Gestionar Objetivos Financieros   |");
        System.out.println("  --------------------------------------- |");
        System.out.println(" |  1. Crear Objetivos Financieros        |");
        System.out.println(" |  2. Editar Objetivos Financieros       |");
        System.out.println(" |  3. Eliminar Objetivos Financieros     |");
        System.out.println(" |  4. Consultar Objetivos Financieros    |");
        System.out.println(" |  5. Volver al menú principal           |");
        System.out.println(" |  6. Salir del programa                 |");
        System.out.println("  ---------------------------------------");
        System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
    }

    private void procesarOpcionGestionarObjetivosFinancieros(int seleccion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        switch (seleccion) {
            case 1:
                crearObjetivoFinanciero();
                break;
            case 2:
                editarObjetivoFinanciero();
                break;
            case 3:
                eliminarObjetivoFinanciero();
                break;
            case 4:
                consultarObjetivoFinanciero();
                break;
            case 5:
                System.out.println("Volviendo al menú principal...");
                break;
            case 6:
                System.out.println("Cerrando el sistema.");
                System.out.println("Gracias por haber confiado en Mifo.");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private void crearObjetivoFinanciero() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        System.out.println(" --------------------------------------------------------");

        String descripcion;
        while (true) {
            System.out.print(" | Ingrese la descripción del objetivo financiero: ");
            descripcion = entrada.nextLine();
            if (ComprobacionMenu.validarDescripcion(descripcion)) {
                break;
            }
            System.out.println("La descripción no puede estar vacía.");
        }

        Double monto;
        while (true) {
            System.out.print(" | Ingrese el monto del objetivo financiero: ");
            String montoStr = entrada.nextLine();
            monto = ComprobacionMenu.validarMonto(montoStr);
            if (monto != null) {
                break;
            }
            System.out.println("Monto inválido. Ingrese un número válido.");
        }

        Date fecha;
        while (true) {
            System.out.print(" | Ingrese la fecha del objetivo financiero (dia/mes/año): ");
            String fechaStr = entrada.nextLine();
            fecha = ComprobacionMenu.validarFecha(fechaStr);
            if (fecha != null) {
                break;
            }
            System.out.println("Fecha inválida. Ingrese la fecha en formato correcto.");
        }

        Categoria categoria = pedirOCrearCategoria();

        ObjetivoFinanciero objetivo = new ObjetivoFinanciero(descripcion, monto, fecha, categoria);
        objetivosArray[objetivoCount++] = objetivo;
        System.out.println(" -> Objetivo financiero creado con éxito.");
    }
    /**
     * Método que solicita al usuario ingresar una categoría existente o crear una nueva.
     * Si la categoría no existe, pregunta si desea crearla con un tipo de movimiento.
     *
     * @return La categoría existente o recién creada.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si ocurre un error en la creación de la categoría.
     */
    /**
     * Solicita al usuario que ingrese el nombre de una categoría ya creada previamente.
     * Muestra las categorías disponibles y sólo acepta una categoría existente.
     *
     * @return La categoría seleccionada por el usuario.
     */
    private Categoria pedirCategoriaExistente() {
        while (true) {
            // Mostrar las categorías disponibles (usa la instancia)
            subMenuCategoria.mostrarCategoriasDisponibles();

            System.out.print("Ingrese el nombre exacto de la categoría que desea seleccionar: ");
            String nombreCategoria = entrada.nextLine().trim();

            // Buscar la categoría en el arreglo (usa la instancia)
            Categoria categoria = subMenuCategoria.buscarCategoriaPorNombreCategoria(nombreCategoria);

            if (categoria != null) {
                return categoria; // categoría válida seleccionada
            } else {
                System.out.println("Categoría no encontrada. Por favor, ingrese un nombre válido.");
            }
        }
    }
    private TipoMovimiento pedirTipoMovimiento() {
        TipoMovimiento[] tipos = TipoMovimiento.values();
        while (true) {
            System.out.println("Seleccione el tipo de movimiento:");
            for (int i = 0; i < tipos.length; i++) {
                System.out.println((i + 1) + ". " + tipos[i]);
            }
            System.out.print("Ingrese la opción: ");
            try {
                int opcion = Integer.parseInt(entrada.nextLine());
                if (opcion >= 1 && opcion <= tipos.length) {
                    return tipos[opcion - 1];
                } else {
                    System.out.println("Opción no válida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }

    private void editarObjetivoFinanciero() throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (objetivoCount == 0) {
            System.out.println("No hay objetivos financieros registrados.");
            return;
        }

        System.out.print(" | Ingrese la descripción del objetivo financiero que desea editar: ");
        String descripcion = entrada.nextLine();

        ObjetivoFinanciero objetivoEditar = null;
        for (int i = 0; i < objetivoCount; i++) {
            if (objetivosArray[i].getDescripcion().equalsIgnoreCase(descripcion)) {
                objetivoEditar = objetivosArray[i];
                break;
            }
        }

        if (objetivoEditar == null) {
            System.out.println("No se encontró ningún objetivo financiero con la descripción especificada.");
            return;
        }

        System.out.print(" | Ingrese la nueva descripción: ");
        String nuevaDescripcion = entrada.nextLine();
        Double nuevoMonto;
        while (true) {
            System.out.print(" | Ingrese el nuevo monto: ");
            String montoStr = entrada.nextLine();
            nuevoMonto = ComprobacionMenu.validarMonto(montoStr);
            if (nuevoMonto != null) break;
            System.out.println("Monto inválido.");
        }

        Date nuevaFecha;
        while (true) {
            System.out.print(" | Ingrese la nueva fecha (dia/mes/año): ");
            String fechaStr = entrada.nextLine();
            nuevaFecha = ComprobacionMenu.validarFecha(fechaStr);
            if (nuevaFecha != null) break;
            System.out.println("Fecha inválida.");
        }

        Categoria nuevaCategoria = pedirOCrearCategoria();

        objetivoEditar.setDescripcion(nuevaDescripcion);
        objetivoEditar.setMonto(nuevoMonto);
        objetivoEditar.setFecha(nuevaFecha);
        objetivoEditar.setCategoria(nuevaCategoria);

        System.out.println(" -> Objetivo financiero editado con éxito.");
    }

    private void eliminarObjetivoFinanciero() {
        if (objetivoCount == 0) {
            System.out.println("No hay objetivos financieros registrados.");
            return;
        }

        System.out.print(" | Ingrese la descripción del objetivo a eliminar: ");
        String descripcion = entrada.nextLine();

        int index = -1;
        for (int i = 0; i < objetivoCount; i++) {
            if (objetivosArray[i].getDescripcion().equalsIgnoreCase(descripcion)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("No se encontró ningún objetivo con esa descripción.");
            return;
        }

        for (int i = index; i < objetivoCount - 1; i++) {
            objetivosArray[i] = objetivosArray[i + 1];
        }
        objetivosArray[--objetivoCount] = null;
        System.out.println(" -> Objetivo financiero eliminado con éxito.");
    }

    private void consultarObjetivoFinanciero() {
        if (objetivoCount == 0) {
            System.out.println("No hay objetivos financieros registrados.");
            return;
        }

        System.out.print(" | Ingrese la descripción del objetivo que desea consultar: ");
        String descripcion = entrada.nextLine();

        for (int i = 0; i < objetivoCount; i++) {
            if (objetivosArray[i].getDescripcion().equalsIgnoreCase(descripcion)) {
                System.out.println("Información del objetivo financiero:");
                System.out.println(objetivosArray[i]);
                return;
            }
        }

        System.out.println("No se encontró ningún objetivo con esa descripción.");
    }
    /**
     * Método que pide al usuario ingresar datos para crear una nueva categoría y la devuelve.
     * @return La nueva categoría creada con los datos ingresados por el usuario.
     */
    private Categoria pedirOCrearCategoria() {
        String nombreCategoria;
        TipoMovimiento tipoMovimiento;

        while (true) {
            System.out.print("Ingrese el nombre de la categoría: ");
            nombreCategoria = entrada.nextLine().trim();
            if (ComprobacionMenu.validarDescripcion(nombreCategoria)) {
                try {
                    empresa.buscarCategoriaPorNombre(nombreCategoria);
                    System.out.println("La categoría ya existe. Intente con otro nombre.");
                } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
                    // No existe la categoría, puede crearla
                    break;
                }
            } else {
                System.out.println("El nombre no puede estar vacío.");
            }
        }

        while (true) {
            System.out.println("Seleccione el tipo de movimiento:");
            TipoMovimiento[] movimientos = TipoMovimiento.values();
            for (int i = 0; i < movimientos.length; i++) {
                System.out.println((i + 1) + ". " + movimientos[i]);
            }
            System.out.print("Ingrese la opción: ");
            if (entrada.hasNextInt()) {
                int opcionMovimiento = entrada.nextInt();
                entrada.nextLine(); // limpiar buffer
                if (opcionMovimiento >= 1 && opcionMovimiento <= movimientos.length) {
                    tipoMovimiento = movimientos[opcionMovimiento - 1];
                    break;
                } else {
                    System.out.println("Opción no válida, intente nuevamente.");
                }
            } else {
                System.out.println("Debe ingresar un número válido.");
                entrada.nextLine(); // limpiar buffer
            }
        }

        return new Categoria(nombreCategoria, tipoMovimiento);
    }
}