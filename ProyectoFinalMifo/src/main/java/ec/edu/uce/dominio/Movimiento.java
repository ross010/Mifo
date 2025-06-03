package ec.edu.uce.dominio;

import ec.edu.uce.util.ComprobacionMenu;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Date;

/**
 * Clase abstracta que representa un movimiento financiero.
 * Contiene atributos comunes como descripción, monto, fecha y categoría.
 * El tipo de movimiento se maneja en las clases hijas.
 *
 * @author MIFO
 */
public abstract class Movimiento {

    /** Descripción del movimiento */
    protected String descripcion;

    /** Monto del movimiento */
    protected double monto;

    /** Fecha en que se realiza el movimiento */
    protected Date fecha;

    /** Categoría asociada al movimiento */
    protected Categoria categoria;

    /**
     * Constructor por defecto que inicializa:
     * descripción con "Sin descripción",
     * monto con 0.0,
     * fecha con la fecha actual,
     * categoría con "Otro".
     */
    public Movimiento() {
        this.descripcion = "Sin descripción";
        this.monto = 0.0;
        this.fecha = new Date();
        this.categoria = new Categoria("Otro");
    }

    /**
     * Constructor que inicializa todos los atributos.
     *
     * @param descripcion Descripción del movimiento
     * @param monto Monto del movimiento
     * @param fecha Fecha del movimiento
     * @param categoria Categoría asociada
     */
    public Movimiento(String descripcion, double monto, Date fecha, Categoria categoria) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    /**
     * Constructor que inicializa descripción, monto y categoría,
     * asignando la fecha actual por defecto.
     *
     * @param descripcion Descripción del movimiento
     * @param monto Monto del movimiento
     * @param categoria Categoría asociada
     */
    public Movimiento(String descripcion, double monto, Categoria categoria) {
        this(descripcion, monto, new Date(), categoria);
    }

    /**
     * Constructor que inicializa descripción y categoría,
     * asignando monto 0.0 y fecha actual por defecto.
     *
     * @param descripcion Descripción del movimiento
     * @param categoria Categoría asociada
     */
    public Movimiento(String descripcion, Categoria categoria) {
        this(descripcion, 0.0, new Date(), categoria);
    }

    /* -------------------- Getters -------------------- */

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    /* -------------------- Setters -------------------- */

    /**
     * Establece la descripción del movimiento.
     *
     * @param descripcion Nueva descripción
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si la descripción no es válida
     */
    public void setDescripcion(String descripcion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (!ComprobacionMenu.validarDescripcion(descripcion)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Descripción inválida.");
        }
        this.descripcion = descripcion.trim();
    }

    /**
     * Establece el monto del movimiento.
     *
     * @param monto Nuevo monto
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si el monto no es válido
     */
    public void setMonto(double monto) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Double montoValido = ComprobacionMenu.validarMonto(String.valueOf(monto));
        if (montoValido == null) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Monto inválido.");
        }
        this.monto = monto;
    }

    /**
     * Establece la fecha del movimiento.
     *
     * @param fecha Nueva fecha
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si la fecha es nula
     */
    public void setFecha(Date fecha) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (fecha == null) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    /**
     * Establece la categoría del movimiento.
     *
     * @param categoria Nueva categoría
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si la categoría es inválida
     */
    public void setCategoria(Categoria categoria) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (categoria == null || categoria.getNombreCategoria() == null || categoria.getNombreCategoria().isEmpty()) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Categoría inválida.");
        }
        this.categoria = categoria;
    }

    /* -------------------- Métodos sobreescritos -------------------- */

    /**
     * Compara dos movimientos para determinar si son iguales.
     * Dos movimientos son iguales si tienen igual descripción, monto, fecha y categoría.
     *
     * @param object Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (this == object) {
            result = true;
        } else if (object != null && getClass() == object.getClass()) {
            Movimiento otroMovimiento = (Movimiento) object;
            boolean montoIgual = Double.compare(this.monto, otroMovimiento.monto) == 0;
            boolean fechaIgual = (this.fecha == null && otroMovimiento.fecha == null) ||
                    (this.fecha != null && this.fecha.equals(otroMovimiento.fecha));
            boolean categoriaIgual = (this.categoria == null && otroMovimiento.categoria == null) ||
                    (this.categoria != null && this.categoria.equals(otroMovimiento.categoria));
            boolean descripcionIgual = (this.descripcion == null && otroMovimiento.descripcion == null) ||
                    (this.descripcion != null && this.descripcion.equals(otroMovimiento.descripcion));

            if (montoIgual && fechaIgual && categoriaIgual && descripcionIgual) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Calcula el código hash basado en descripción, monto, fecha y categoría.
     *
     * @return Código hash del objeto
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + Double.hashCode(monto);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }

    /**
     * Representación en texto del movimiento.
     * Muestra descripción, monto, fecha y categoría.
     *
     * @return Cadena con la información del movimiento
     */
    @Override
    public String toString() {
        return "\nDescripcion= " + descripcion
                + "\nMonto= " + monto
                + "\nFecha= " + fecha
                + "\nCategoria= " + (categoria != null ? categoria.getNombreCategoria() : "Sin categoria");
    }

    /* -------------------- Métodos abstractos -------------------- */

    /**
     * Registra el movimiento.
     *
     * @return true si el registro fue exitoso, false en caso contrario
     */
    public abstract boolean registrar();

    /**
     * Valida si un movimiento es duplicado de otro.
     *
     * @param movimiento Movimiento a comparar
     * @return true si es duplicado, false si no
     */
    public abstract boolean validarDuplicado(Movimiento movimiento);

    /**
     * Realiza el movimiento, con las validaciones necesarias.
     *
     * @throws ExcepcionMifo.SaldoInsuficienteExcepcion si no hay saldo suficiente
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si el movimiento es inválido
     */
    public abstract void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, ExcepcionMifo.MovimientoInvalidoExcepcion;

}
