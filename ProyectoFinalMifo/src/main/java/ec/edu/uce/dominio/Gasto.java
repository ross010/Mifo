package ec.edu.uce.dominio;

import ec.edu.uce.util.ExcepcionMifo;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Gasto hereda de la clase abstracta Movimiento.
 * Representa un movimiento de tipo gasto con sus características específicas.
 *
 * No contiene atributo TipoMovimiento porque esto se maneja en las subclases.
 *
 */
public class Gasto extends Movimiento {

    /**
     * Constructor por defecto.
     * Inicializa un gasto con categoría "Alimentación" y valores por defecto en los atributos heredados.
     */
    public Gasto() {
        super("Sin descripción", 0.0, new Date(), new Categoria("Alimentación"));
    }

    /**
     * Constructor principal.
     * Inicializa un gasto con todos sus atributos: descripción, monto, fecha y categoría.
     *
     * @param descripcion Descripción del gasto.
     * @param monto Monto del gasto.
     * @param fecha Fecha del gasto.
     * @param categoria Categoría del gasto.
     */
    public Gasto(String descripcion, double monto, Date fecha, Categoria categoria) {
        super(descripcion, monto, fecha, categoria);
    }

    /**
     * Constructor con descripción, monto y categoría.
     * La fecha se asigna como la fecha actual.
     *
     * @param descripcion Descripción del gasto.
     * @param monto Monto del gasto.
     * @param categoria Categoría del gasto.
     */
    public Gasto(String descripcion, double monto, Categoria categoria) {
        super(descripcion, monto, categoria);
    }

    /**
     * Constructor con descripción y categoría.
     * Monto se inicializa en 0.0 y fecha en actual.
     *
     * @param descripcion Descripción del gasto.
     * @param categoria Categoría del gasto.
     */
    public Gasto(String descripcion, Categoria categoria) {
        super(descripcion, categoria);
    }

    /**
     * Constructor con descripción, monto y fecha.
     * La categoría se establece por defecto en "Alimentación".
     *
     * @param descripcion Descripción del gasto.
     * @param monto Monto del gasto.
     * @param fecha Fecha del gasto.
     */
    public Gasto(String descripcion, double monto, Date fecha) {
        this(descripcion, monto, fecha, new Categoria("Alimentación"));
    }

    /**
     * Constructor con descripción y monto.
     * Fecha actual y categoría por defecto "Alimentación".
     *
     * @param descripcion Descripción del gasto.
     * @param monto Monto del gasto.
     */
    public Gasto(String descripcion, double monto) {
        this(descripcion, monto, new Date(), new Categoria("Alimentación"));
    }

    /**
     * Constructor solo con descripción.
     * Monto 0.0, fecha actual y categoría "Alimentación".
     *
     * @param descripcion Descripción del gasto.
     */
    public Gasto(String descripcion) {
        this(descripcion, 0.0, new Date(), new Categoria("Alimentación"));
    }

    /**
     * Registra el gasto validando que el monto sea mayor a cero.
     * En caso contrario lanza excepción y registra error en logger.
     *
     * @return true si se registró correctamente, false si hubo error.
     */
    @Override
    public boolean registrar() {
        if (this.getMonto() <= 0.0) {
            try {
                throw new ExcepcionMifo.MovimientoInvalidoExcepcion("El monto del gasto debe ser mayor que cero.");
            } catch (ExcepcionMifo.MovimientoInvalidoExcepcion ex) {
                Logger.getLogger(Gasto.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            System.out.println("Gasto registrado: \nDescripción: " + this.getDescripcion()
                    + "\nMonto: " + this.getMonto()
                    + "\nFecha: " + this.getFecha()
                    + "\nCategoría: " + this.getCategoria().getNombreCategoria());
            return true;
        }
    }

    /**
     * Representación en texto del gasto.
     *
     * @return Cadena con la información del gasto.
     */
    @Override
    public String toString() {
        return "Gasto:\n" + super.toString();
    }

    /**
     * Valida si otro movimiento es duplicado de este gasto.
     * Se considera duplicado si son de la misma clase y tienen
     * iguales descripción, monto, fecha y categoría.
     *
     * @param otroMovimiento Movimiento a comparar.
     * @return true si es duplicado, false en caso contrario.
     */
    @Override
    public boolean validarDuplicado(Movimiento otroMovimiento) {
        if (!(otroMovimiento instanceof Gasto)) {
            return false;
        }
        Gasto otroGasto = (Gasto) otroMovimiento;
        return this.getDescripcion().equals(otroGasto.getDescripcion())
                && Double.compare(this.getMonto(), otroGasto.getMonto()) == 0
                && this.getFecha().equals(otroGasto.getFecha())
                && this.getCategoria().equals(otroGasto.getCategoria());
    }

    /**
     * Realiza el gasto, validando que el monto sea válido.
     * Lanza excepción si el monto es menor o igual a cero.
     *
     * @throws ExcepcionMifo.SaldoInsuficienteExcepcion No implementada aquí.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si el monto es inválido.
     */
    @Override
    public void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (this.getMonto() <= 0.0) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("El monto del gasto debe ser mayor que cero.");
        }
        System.out.println("Realizando gasto: \nDescripción: " + this.getDescripcion()
                + "\nMonto: " + this.getMonto()
                + "\nFecha: " + this.getFecha()
                + "\nCategoría: " + this.getCategoria().getNombreCategoria());
    }

}
