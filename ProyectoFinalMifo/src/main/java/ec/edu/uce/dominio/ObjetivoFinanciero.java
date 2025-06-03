package ec.edu.uce.dominio;
import java.io.Serializable;
import java.util.Date;
/**
 * Clase que representa un Objetivo Financiero,
 * con descripción, monto, fecha y categoría asociada.
 * Implementa Serializable para permitir su serialización.
 */
public class ObjetivoFinanciero implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descripcion;
    private double monto;
    private Date fecha;

    /**
     * Asociación con la clase Categoria (relación unidireccional).
     * Cada objetivo financiero está asociado a una categoría.
     */
    private Categoria categoria;

    /**
     * Constructor que inicializa un ObjetivoFinanciero con valores por defecto.
     *
     * Este constructor llama al constructor con parámetros,
     * asignando "Sin descripcion", monto 0.0, la fecha actual y una nueva Categoría vacía.
     *
     * @param ahorroParaViajes parámetro no utilizado (posiblemente para otro propósito).
     * @param i parámetro no utilizado.
     * @param date parámetro no utilizado.
     */
    public ObjetivoFinanciero(String ahorroParaViajes, int i, Date date) {
        this("Sin descripcion", 0.0, new Date(), new Categoria());
    }

    /**
     * Constructor con parámetros para inicializar el objetivo financiero con valores específicos.
     *
     * @param descripcion descripción del objetivo financiero.
     * @param monto monto asociado al objetivo.
     * @param fecha fecha límite o referencia para el objetivo.
     * @param categoria categoría asociada al objetivo financiero.
     */
    public ObjetivoFinanciero(String descripcion, double monto, Date fecha, Categoria categoria) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
    }


    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public boolean equals(Object object) {
        ObjetivoFinanciero otroObjetivo = null;
        boolean resp = false;

        if (object != null && object instanceof ObjetivoFinanciero) {
            otroObjetivo = (ObjetivoFinanciero) object;

            if (Double.compare(this.monto, otroObjetivo.monto) == 0
                    && this.descripcion.equals(otroObjetivo.descripcion)
                    && (this.fecha != null ? this.fecha.equals(otroObjetivo.fecha) : otroObjetivo.fecha == null)
                    && this.categoria.equals(otroObjetivo.categoria)) {
                resp = true;
            }
        }
        return resp;
    }
    /**
     * Devuelve una representación en cadena del ObjetivoFinanciero.
     *
     * Este método sobrescribe el método {@code toString()} de la clase {@link Object}
     * para mostrar una descripción legible de los atributos del objetivo.
     *
     * @return una cadena con la descripción, monto, fecha y categoría del objetivo financiero.
     */
    @Override
    public String toString() {
        return "Objetivo Financiero:\nDescripción: " + descripcion
                + "\nMonto: " + monto
                + "\nFecha: " + fecha
                + "\nCategoría: " + categoria + "\n";
    }
}
