package ec.edu.uce.dominio;
import java.io.Serializable;
/**
 * Representa una categoría utilizada en el sistema.
 * Cada categoría tiene un nombre y un tipo de movimiento asociado.
 * Implementa la interfaz Serializable para permitir la persistencia de datos.
 */
public class Categoria implements Serializable {

    // Arreglo estático para almacenar un número limitado de categorías.
    private static Categoria[] categorias = new Categoria[10];

    // Contador estático utilizado para asignar identificadores únicos.
    private static int contadorId = 0;

    // Nombre de la categoría.
    private String nombreCategoria;

    // Tipo de movimiento asociado a la categoría. */
    private TipoMovimiento tipoMovimiento;

    /**
     * Constructor por defecto que asigna una categoría sin nombre y sin tipo de movimiento.
     * Forma parte de la sobrecarga de constructores en esta clase.
     */
    public Categoria() {
        this("Sin categoria", null);
    }

    /**
     * Constructor que inicializa la categoría con un nombre específico.
     * Forma parte de la sobrecarga de constructores en esta clase.
     * @param nombreCategoria Nombre de la categoría.
     */
    public Categoria(String nombreCategoria) {
        this(nombreCategoria, null);
    }

    /**
     * Constructor que inicializa la categoría con un nombre y un tipo de movimiento específico.
     * @param nombreCategoria Nombre de la categoría.
     * @param tipoMovimiento Tipo de movimiento asociado.
     */
    public Categoria(String nombreCategoria, TipoMovimiento tipoMovimiento) {
        this.nombreCategoria = nombreCategoria;
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * Obtiene el nombre de la categoría.
     * @return Nombre de la categoría.
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Establece un nuevo nombre para la categoría.
     * @param nombreCategoria Nuevo nombre de la categoría.
     */

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Obtiene el tipo de movimiento asociado a la categoría.
     * @return Tipo de movimiento de la categoría.
     */
    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    /**
     * Establece un nuevo tipo de movimiento para la categoría.
     * @param tipoMovimiento Nuevo tipo de movimiento.
     */
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }
    /**
     * Sobrescribe el método toString() de la clase Object.
     * Devuelve una representación en cadena de la categoría.
     * @return Cadena con el nombre y el tipo de movimiento de la categoría.
     */
    @Override
    public String toString() {
        return "Categoria: " + nombreCategoria + ", Tipo: " + tipoMovimiento;
    }
    /**
     * Compara la categoría con otro objeto para determinar si son iguales.
     * Dos categorías son iguales si tienen el mismo nombre.
     * @param object Objeto a comparar.
     * @return true si tienen el mismo nombre, false en caso contrario.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Categoria otraCategoria) {
            return this.nombreCategoria.equals(otraCategoria.nombreCategoria);
        }
        return false;
    }
}