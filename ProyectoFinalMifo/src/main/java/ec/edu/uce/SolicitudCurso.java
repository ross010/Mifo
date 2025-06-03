package ec.edu.uce;
import java.util.Scanner;
public class SolicitudCurso {
    private Scanner entrada = new Scanner(System.in);
    private int verCursosDisponibles(int maxOpciones) {
        int seleccion = -1;
        while (seleccion < 1 || seleccion > maxOpciones) {
            try {
                seleccion = Integer.parseInt(entrada.nextLine());
                if (seleccion < 1 || seleccion > maxOpciones) {
                    System.out.print("Opción inválida. Ingrese un número entre 1 y " + maxOpciones + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingrese un número válido: ");
            }
        }
        return seleccion;
    }
    private void verCursosDisponibles() {
        System.out.println();
        System.out.println("  ---------------------------------------  ");
        System.out.println(" |         Cursos disponibles             |");
        System.out.println("  --------------------------------------- |");
        System.out.println(" |  1. Finanzas Personales                |");
        System.out.println(" |  2. Gastos Hormiga                     |");
        System.out.println(" |  3. Ahorro para el Futuro              |");
        System.out.println("  ---------------------------------------");
        System.out.println();
    }
    public void inscribirseCurso() {
        verCursosDisponibles();
        System.out.print(" | Ingrese el número del curso al que desea inscribirse: ");
        String seleccion = entrada.nextLine();
        switch (seleccion) {
            case "1":
                System.out.println("    -> Inscripción exitosa al curso: Finanzas Personales.");
                mostrarContenidoCurso("Finanzas Personales");
                break;
            case "2":
                System.out.println(" -> Inscripción exitosa al curso: Gastos Hormiga.");
                mostrarContenidoCurso("Gastos Hormiga");
                break;
            case "3":
                System.out.println(" -> Inscripción exitosa al curso: Ahorro para el Futuro.");
                mostrarContenidoCurso("Ahorro para el Futuro");
                break;
            default:
                System.out.println("            Curso no válido.");
        }
    }
    private void mostrarContenidoCurso(String curso) {
        switch (curso) {
            case "Finanzas Personales":
                System.out.println("Las finanzas personales son la gestión eficiente del dinero propio para cubrir necesidades, ahorrar y planificar el futuro.");
                System.out.println("En el caso de los estudiantes foráneos, es fundamental llevar un control detallado de ingresos y egresos, ya que");
                System.out.println("muchos viven lejos de casa con presupuestos limitados. Esto implica planificar gastos como alquiler, alimentación,");
                System.out.println("transporte, útiles académicos y emergencias. Una buena administración financiera permite evitar deudas innecesarias,");
                System.out.println("priorizar lo esencial y generar hábitos de ahorro que serán útiles durante toda la vida.");
                break;
            case "Gastos Hormiga":
                System.out.println("Los gastos hormiga son pequeños desembolsos diarios que, aunque parecen insignificantes,");
                System.out.println("al acumularse a lo largo del tiempo, pueden representar una parte considerable de tu presupuesto.");
                System.out.println("Para un estudiante foráneo, estos gastos pueden incluir el café diario, snacks, transporte innecesario,");
                System.out.println("suscripciones que no se usan o compras impulsivas. Identificarlos y reducirlos es clave para mantener");
                System.out.println("un control financiero efectivo mientras estudias lejos de casa.");
                break;
            case "Ahorro para el Futuro":
                System.out.println("El ahorro para el futuro consiste en reservar una parte de tus ingresos actuales para cubrir");
                System.out.println("necesidades futuras, emergencias o alcanzar metas personales. Como estudiante foráneo, ahorrar");
                System.out.println("puede ayudarte a enfrentar imprevistos, como gastos médicos o viajes inesperados, sin depender");
                System.out.println("de tus familiares o endeudarte. Es un hábito esencial para lograr estabilidad y autonomía financiera.");
                break;
            default:
                System.out.println("Contenido no disponible.");
        }
    }
}




