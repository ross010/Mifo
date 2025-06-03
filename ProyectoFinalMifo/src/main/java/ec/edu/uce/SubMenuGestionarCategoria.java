/**
 * Clase que representa el submenú para gestionar categorías en el sistema MIFO.
 * Permite crear, editar, eliminar y consultar categorías dentro de la aplicación.
 */
package ec.edu.uce;
import ec.edu.uce.dominio.Categoria;
import ec.edu.uce.dominio.TipoMovimiento;
import ec.edu.uce.dominio.Empresa;
import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Scanner;
public class SubMenuGestionarCategoria {
    /**
     * Scanner para leer la entrada del usuario desde la consola.
     */
    private Scanner entrada = new Scanner(System.in);

    /**
     * Arreglo que almacena las categorías registradas en el sistema, con una capacidad máxima de 100 categorías.
     */
    private Categoria[] categoriasArray = new Categoria[100];

    /**
     * Instancia de la empresa que administra las categorías.
     */
    private final Empresa empresa = new Empresa();

    /**
     * Contador de categorías almacenadas en el sistema.
     */
    private int categoriaCount = 0;

    /**
     * Muestra el menú de gestión de categorías y procesa la opción elegida por el usuario.
     */
    public void menuGestionarCategoria() {
        int seleccion;
        do {
            mostrarMenuGestionarCategorias();
            seleccion = ComprobacionMenu.validarOpcionMenu(entrada, 6);
            try {
                procesarOpcionGestionarCategorias(seleccion);
            } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (seleccion != 5 && seleccion != 6);
    }

    /**
     * Muestra las opciones disponibles en el submenú de gestión de categorías.
     */
    private void mostrarMenuGestionarCategorias() {
        System.out.println();
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |           Gestionar Categoría            |");
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |  1. Crear Categoría                     |");
        System.out.println(" |  2. Editar Categoría                    |");
        System.out.println(" |  3. Eliminar Categoría                  |");
        System.out.println(" |  4. Consultar Categoría                 |");
        System.out.println(" |  5. Volver al menú principal            |");
        System.out.println(" |  6. Salir del programa                  |");
        System.out.println("  ---------------------------------------  ");
        System.out.println();
        System.out.print("Por favor, introduce el número correspondiente a la acción que deseas realizar: ");
    }

    /**
     * Procesa la opción elegida en el menú e invoca la funcionalidad correspondiente.
     *
     * @param seleccion Opción elegida por el usuario.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion Si se produce un error en la gestión de movimientos de categoría.
     */
    private void procesarOpcionGestionarCategorias(int seleccion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        switch (seleccion) {
            case 1:
                crearCategoria();
                break;
            case 2:
                editarCategoria();
                break;
            case 3:
                eliminarCategoria();
                break;
            case 4:
                consultarCategoria();
                break;
            case 5:
                System.out.println();
                System.out.println("Volviendo al menú principal...");
                return;
            case 6:
                System.out.println();
                System.out.println("                                     Cerrando el sistema.");
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Gracias por haber confiado en Mifo. Esperamos que nuestra plataforma te haya sido de gran ayuda.");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida, por favor intente de nuevo.");
        }
    }

    /**
     * Crea una nueva categoría verificando la validez de los datos ingresados.
     */
    private void crearCategoria() {
        System.out.println(" --------------------------------------------------------");
        String nombreCategoria;
        TipoMovimiento tipoMovimiento;

        // Validación del nombre de la categoría
        while (true) {
            System.out.print(" | Ingrese el nombre de la categoría: ");
            nombreCategoria = entrada.nextLine().trim();
            if (ComprobacionMenu.validarDescripcion(nombreCategoria))
                if (buscarCategoriaPorNombreCategoria(nombreCategoria) == null) {
                    break;
                } else {
                    System.out.println("La categoría ya existe. Intente con otro nombre.");
                }
            else {
                System.out.println("El nombre no puede estar vacío.");
            }
        }

        // Selección del tipo de movimiento
        while (true) {
            System.out.println(" | Seleccione el tipo de movimiento:");
            TipoMovimiento[] movimientos = TipoMovimiento.values();
            for (int i = 0; i < movimientos.length; i++) {
                System.out.println("   " + (i + 1) + ". " + movimientos[i]);
            }
            System.out.print("Ingrese la opción: ");
            if (entrada.hasNextInt()) {
                int opcionMovimiento = entrada.nextInt();
                entrada.nextLine(); // Limpiar buffer
                if (opcionMovimiento >= 1 && opcionMovimiento <= movimientos.length) {
                    tipoMovimiento = movimientos[opcionMovimiento - 1];
                    break;
                } else {
                    System.out.println("Opción no válida, intente nuevamente.");
                }
            } else {
                System.out.println("Debe ingresar un número válido.");
                entrada.nextLine(); // Limpiar buffer
            }
        }

        // Creación de la categoría
        Categoria categoria = new Categoria(nombreCategoria, tipoMovimiento);
        categoriasArray[categoriaCount++] = categoria;
        empresa.agregarCategoria(categoria);
        System.out.println(" -> Categoría creada con éxito.");
    }

    /**
     * Edita una categoría existente verificando su validez.
     */
    /**
     * Edita una categoría existente verificando su validez y permitiendo cambios en su nombre y tipo de movimiento.
     */
    private void editarCategoria() {
        // Verifica si hay categorías registradas antes de proceder.
        if (categoriaCount == 0) {
            System.out.println("No hay categorías registradas.");
            return;
        }

        System.out.print(" | Ingrese el nombre de la categoría que desea editar: ");
        String nombreCategoriaAntiguo = entrada.nextLine().trim();

        // Busca la categoría por su nombre.
        Categoria categoriaEditar = buscarCategoriaPorNombreCategoria(nombreCategoriaAntiguo);
        if (categoriaEditar == null) {
            System.out.println("No se encontró ninguna categoría con el nombre especificado.");
            return;
        }

        String nuevoNombreCategoria;
        TipoMovimiento nuevoTipoMovimiento;

        // Validación del nuevo nombre de la categoría
        while (true) {
            System.out.print(" | Ingrese el nuevo nombre de la categoría: ");
            nuevoNombreCategoria = entrada.nextLine().trim();
            if (ComprobacionMenu.validarDescripcion(nuevoNombreCategoria)) {
                if (buscarCategoriaPorNombreCategoria(nuevoNombreCategoria) == null || nuevoNombreCategoria.equalsIgnoreCase(nombreCategoriaAntiguo)) {
                    break;
                } else {
                    System.out.println("Ya existe otra categoría con ese nombre.");
                }
            } else {
                System.out.println("El nombre no puede estar vacío.");
            }
        }
        // Selección del nuevo tipo de movimiento
        while (true) {
            System.out.println(" | Seleccione el nuevo tipo de movimiento:");
            TipoMovimiento[] movimientos = TipoMovimiento.values();
            for (int i = 0; i < movimientos.length; i++) {
                System.out.println("   " + (i + 1) + ". " + movimientos[i]);
            }
            System.out.print("Ingrese la opción: ");
            if (entrada.hasNextInt()) {
                int opcionMovimiento = entrada.nextInt();
                entrada.nextLine(); // Limpia el buffer
                if (opcionMovimiento >= 1 && opcionMovimiento <= movimientos.length) {
                    nuevoTipoMovimiento = movimientos[opcionMovimiento - 1];
                    break;
                } else {
                    System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Debe ingresar un número.");
                entrada.nextLine(); // Limpia el buffer
            }
        }
        // Actualiza los datos de la categoría con los nuevos valores ingresados
        categoriaEditar.setNombreCategoria(nuevoNombreCategoria);
        categoriaEditar.setTipoMovimiento(nuevoTipoMovimiento);

        System.out.println(" -> Categoría editada con éxito.");
    }
    /**
     * Elimina una categoría existente del sistema.
     * Solicita al usuario el nombre de la categoría que desea eliminar y la remueve si se encuentra registrada.
     */
    private void eliminarCategoria() {
        // Verifica si hay categorías registradas antes de proceder.
        if (categoriaCount == 0) {
            System.out.println("No hay categorías registradas.");
            return;
        }

        System.out.print(" | Ingrese el nombre de la categoría que desea eliminar: ");
        String nombreCategoriaEliminar = entrada.nextLine().trim();

        // Busca la categoría por su nombre y obtiene su índice en el arreglo.
        int indexEliminar = -1;
        for (int i = 0; i < categoriaCount; i++) {
            if (categoriasArray[i].getNombreCategoria().equalsIgnoreCase(nombreCategoriaEliminar)) {
                indexEliminar = i;
                break;
            }
        }

        // Si la categoría no se encuentra, informa al usuario y finaliza el proceso.
        if (indexEliminar == -1) {
            System.out.println("No se encontró ninguna categoría con ese nombre.");
            return;
        }

        // Elimina la categoría desplazando las siguientes categorías para llenar el espacio vacío.
        for (int i = indexEliminar; i < categoriaCount - 1; i++) {
            categoriasArray[i] = categoriasArray[i + 1];
        }
        categoriasArray[--categoriaCount] = null;

        System.out.println(" -> Categoría eliminada con éxito.");
    }

    /**
     * Consulta y muestra en pantalla la información de una categoría existente.
     * Solicita al usuario el nombre de la categoría y muestra sus detalles si está registrada.
     */
    private void consultarCategoria() {
        // Verifica si hay categorías registradas antes de proceder.
        if (categoriaCount == 0) {
            System.out.println("No hay categorías registradas.");
            return;
        }

        System.out.print(" | Ingrese el nombre de la categoría que desea consultar: ");
        String nombreCategoriaConsulta = entrada.nextLine().trim();

        // Busca la categoría por su nombre.
        Categoria categoriaConsulta = buscarCategoriaPorNombreCategoria(nombreCategoriaConsulta);
        if (categoriaConsulta == null) {
            System.out.println("No se encontró ninguna categoría con ese nombre.");
            return;
        }

        // Muestra los detalles de la categoría encontrada.
        System.out.println("Información de la categoría:");
        System.out.println("Nombre: " + categoriaConsulta.getNombreCategoria());
        System.out.println("Tipo Movimiento: " + categoriaConsulta.getTipoMovimiento());
    }

    /**
     * Busca una categoría por nombre dentro del arreglo de categorías.
     *
     * @param nombreCategoria Nombre de la categoría a buscar.
     * @return La categoría encontrada o null si no existe.
     */
    public Categoria buscarCategoriaPorNombreCategoria(String nombreCategoria) {
        for (int i = 0; i < categoriaCount; i++) {
            if (categoriasArray[i].getNombreCategoria().equalsIgnoreCase(nombreCategoria)) {
                return categoriasArray[i];
            }
        }
        return null;
    }
    /**
     * Muestra todas las categorías actualmente registradas en el sistema.
     */
    public void mostrarCategoriasDisponibles() {
        if (categoriaCount == 0) {
            System.out.println("No hay categorías registradas.");
            return;
        }
        System.out.println("Categorías disponibles:");
        for (int i = 0; i < categoriaCount; i++) {
            System.out.println(" - " + categoriasArray[i].getNombreCategoria() + " (" + categoriasArray[i].getTipoMovimiento() + ")");
        }
    }
    /**
     * Método que pide al usuario ingresar datos para crear una nueva categoría y la devuelve.
     * @return La nueva categoría creada con los datos ingresados por el usuario.
     */
    public Categoria pedirOCrearCategoria() {
        String nombreCategoria;
        TipoMovimiento tipoMovimiento;

        while (true) {
            System.out.print("Ingrese el nombre de la categoría: ");
            nombreCategoria = entrada.nextLine().trim();
            if (ComprobacionMenu.validarDescripcion(nombreCategoria)) {
                if (buscarCategoriaPorNombreCategoria(nombreCategoria) == null) {
                    break;
                } else {
                    System.out.println("La categoría ya existe. Intente con otro nombre.");
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