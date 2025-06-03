package ec.edu.uce;
import ec.edu.uce.dominio.Categoria;
import ec.edu.uce.dominio.EducacionFinanciera;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class SubMenuGestionarEducacionFinanciera {
    private EducacionFinanciera[] cursos = new EducacionFinanciera[0];
    private int numCursos = 0;
    private Scanner entrada = new Scanner(System.in);
    public void menuGestionarEducacionFinanciera() {
        boolean continuar = true;
        while (continuar) {
            System.out.println();
            System.out.println("  ----------------------------------------------  ");
            System.out.println(" |       Gestionar Educación Financiera          |");
            System.out.println("  ----------------------------------------------  ");
            System.out.println(" |  1. Crear Educación Financiera                |");
            System.out.println(" |  2. Editar Educación Financiera               |");
            System.out.println(" |  3. Eliminar Educación Financiera             |");
            System.out.println(" |  4. Consultar Educación Financiera            |");
            System.out.println(" |  5. Salir del sistema                         |");
            System.out.println("  ----------------------------------------------  ");
            System.out.println();
            System.out.print("Por favor, selecciona una opción: ");
            String opcion = entrada.nextLine();

            switch (opcion) {
                case "1":
                    crearCurso();
                    break;
                case "2":
                    editarCurso();
                    break;
                case "3":
                    eliminarCurso();
                    break;
                case "4":
                    menuConsultaCursos();
                    break;
                case "5":
                    continuar = false;
                    System.out.println("\nSaliendo del sistema. ¡Gracias por usar la plataforma MIFO!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        }
    }

    // Crear nuevo curso
    public void crearCurso() {
        System.out.println("\n--- CREAR NUEVO CURSO ---");
        System.out.print("Título: ");
        String titulo = entrada.nextLine();
        System.out.print("Descripción: ");
        String descripcion = entrada.nextLine();
        System.out.print("Duración en semanas: ");
        int duracion = Integer.parseInt(entrada.nextLine());
        System.out.print("Nivel: ");
        String nivel = entrada.nextLine();
        System.out.print("Modalidad: ");
        String modalidad = entrada.nextLine();
        System.out.print("Nombre de la categoría: ");
        String nombreCategoria = entrada.nextLine();
        Categoria categoria = new Categoria(nombreCategoria);
        Date fechaInicio = new Date();

        EducacionFinanciera curso = new EducacionFinanciera(titulo, descripcion, duracion, nivel, modalidad, categoria, fechaInicio);

        if (numCursos == cursos.length) {
            EducacionFinanciera[] temp = cursos;
            cursos = new EducacionFinanciera[numCursos + 1];
            System.arraycopy(temp, 0, cursos, 0, numCursos);
        }
        cursos[numCursos] = curso;
        numCursos++;
        System.out.println("Curso creado exitosamente.");
    }

    // Editar un curso
    public void editarCurso() {
        System.out.print("Ingrese el título del curso a editar: ");
        String titulo = entrada.nextLine();

        int indice = buscarCurso(titulo); // este método lo incluimos más abajo
        if (indice == -1) {
            System.out.println("Curso no encontrado.");
            return;
        }

        EducacionFinanciera curso = cursos[indice]; // ← aquí el arreglo correcto

        System.out.println("EDITANDO: " + curso.getTitulo());

        System.out.print("Nuevo título (Enter para mantener): ");
        String nuevoTitulo = entrada.nextLine();
        if (!nuevoTitulo.isEmpty()) {
            curso.setTitulo(nuevoTitulo);
        }

        System.out.print("Nueva descripción (Enter para mantener): ");
        String nuevaDescripcion = entrada.nextLine();
        if (!nuevaDescripcion.isEmpty()) {
            curso.setDescripcion(nuevaDescripcion);
        }

        System.out.print("Nueva duración (0 para mantener): ");
        int nuevaDuracion = Integer.parseInt(entrada.nextLine());
        if (nuevaDuracion > 0) {
            curso.setDuracionSemanas(nuevaDuracion);
        }

        System.out.print("Nuevo nivel (Enter para mantener): ");
        String nuevoNivel = entrada.nextLine();
        if (!nuevoNivel.isEmpty()) {
            curso.setNivel(nuevoNivel);
        }

        System.out.print("Nueva modalidad (Enter para mantener): ");
        String nuevaModalidad = entrada.nextLine();
        if (!nuevaModalidad.isEmpty()) {
            curso.setModalidad(nuevaModalidad);
        }

        System.out.print("Nueva categoría (Enter para mantener): ");
        String nuevaCategoria = entrada.nextLine();
        if (!nuevaCategoria.isEmpty()) {
            curso.setCategoria(new Categoria(nuevaCategoria));
        }

        System.out.println("Curso actualizado correctamente.");
    }

    public void eliminarCurso() {
        System.out.print("Ingrese el título del curso a eliminar: ");
        String titulo = entrada.nextLine();
        int indice = buscarCurso(titulo);
        if (indice == -1) {
            System.out.println("Curso no encontrado.");
            return;
        }
        for (int i = indice; i < numCursos - 1; i++) {
            cursos[i] = cursos[i + 1];
        }

        cursos[numCursos - 1] = null;
        numCursos--;

        System.out.println("Curso eliminado exitosamente.");
    }
    private void menuConsultaCursos() {
        boolean regresar = false;
        while (!regresar) {
            System.out.println();
            System.out.println("  -----------------------------------------------  ");
            System.out.println(" |        Consultar Educación Financiera          |");
            System.out.println("  -----------------------------------------------  ");
            System.out.println(" |  1. Ver cursos disponibles                     |");
            System.out.println(" |  2. Buscar cursos por categoría                |");
            System.out.println(" |  3. Ver detalle de un curso                    |");
            System.out.println(" |  4. Inscribirse a un curso                     |");
            System.out.println(" |  5. Volver al menú anterior                    |");
            System.out.println("  -----------------------------------------------  ");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            String opcion = entrada.nextLine();

            switch (opcion) {
                case "1":
                    verCursosDisponibles();
                    break;
                case "2":
                    buscarCursosPorCategoria();
                    break;
                case "3":
                    verDetalleCurso();
                    break;
                case "4":
                    inscribirseCurso();
                    break;
                case "5":
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println();
        }
    }

    private void verCursosDisponibles() {
        System.out.println("\n----- Cursos Disponibles -----");
        if (numCursos == 0) {
            System.out.println("No hay cursos registrados.");
        } else {
            for (int i = 0; i < numCursos; i++) {
                System.out.println("- " + cursos[i].getTitulo());
            }
        }
    }
    private void buscarCursosPorCategoria() {
        String categoria = solicitarCadenaNoVacia("Ingrese la categoría a buscar: ");
        boolean encontrado = false;
        for (int i = 0; i < numCursos; i++) {
            if (cursos[i].getCategoria().getNombreCategoria().equalsIgnoreCase(categoria)) {
                System.out.println(cursos[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron cursos en esa categoría.");
        }
    }
    private void verDetalleCurso() {
        System.out.print("Ingrese el título del curso para ver el detalle: ");
        String titulo = entrada.nextLine();

        int indice = buscarCurso(titulo);
        if (indice == -1) {
            System.out.println("Curso no encontrado.");
            return;
        }

        EducacionFinanciera curso = cursos[indice];
        System.out.println("\n----- Detalle del Curso -----");
        System.out.println("Título: " + curso.getTitulo());
        System.out.println("Descripción: " + curso.getDescripcion());
        System.out.println("Duración (semanas): " + curso.getDuracionSemanas());
        System.out.println("Nivel: " + curso.getNivel());
        System.out.println("Modalidad: " + curso.getModalidad());
        System.out.println("Categoría: " + curso.getCategoria().getNombreCategoria());
        System.out.println("Fecha de inicio: " + curso.getFechaInicio());
    }
    private void inscribirseCurso() {
        String nombre = solicitarCadenaNoVacia("Ingrese el nombre del curso al que desea inscribirse: ");
        for (int i = 0; i < numCursos; i++) {
            if (cursos[i].getTitulo().equalsIgnoreCase(nombre)) {
                System.out.println("¡Inscripción exitosa al curso: " + cursos[i].getTitulo() + "!");
                return;
            }
        }
        System.out.println("Curso no encontrado.");
    }
    // Métodos auxiliares para validaciones y entradas

    private String solicitarCadenaNoVacia(String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = entrada.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Entrada no puede estar vacía. Intente de nuevo.");
            }
        } while (input.isEmpty());
        return input;
    }

    private String solicitarCadenaOpcional(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }
    private int buscarCurso(String titulo) {
        for (int i = 0; i < numCursos; i++) {
            if (cursos[i].getTitulo().equalsIgnoreCase(titulo)) {
                return i;
            }
        }
        return -1;
    }
    private int solicitarEnteroPositivo(String mensaje) {
        int valor = -1;
        do {
            System.out.print(mensaje);
            String input = entrada.nextLine().trim();
            try {
                valor = Integer.parseInt(input);
                if (valor <= 0) {
                    System.out.println("Por favor ingrese un número entero positivo.");
                    valor = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
            }
        } while (valor <= 0);
        return valor;
    }

    public static void main(String[] args) {
        SubMenuGestionarEducacionFinanciera menu = new SubMenuGestionarEducacionFinanciera();
        menu.menuGestionarEducacionFinanciera();
    }
}
