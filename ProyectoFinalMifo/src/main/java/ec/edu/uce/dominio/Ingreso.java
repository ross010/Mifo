package ec.edu.uce.dominio;

import ec.edu.uce.util.ExcepcionMifo;
import java.util.Date;

public class Ingreso extends Movimiento {

    // atributo propio de Ingreso
    private final TipoMovimiento tipoMovimiento = TipoMovimiento.INGRESO;

    // Constructor por defecto
    public Ingreso() {
        super("Sin descripción", 0.0, new Date(), new Categoria("Otro"));
    }

    // Constructor con todos los parámetros excepto tipoMovimiento (es propio)
    public Ingreso(String descripcion, double monto, Date fecha, Categoria categoria) {
        super(descripcion, monto, fecha, categoria);
    }

    // Constructor con descripción, monto y categoría (fecha actual)
    public Ingreso(String descripcion, double monto, Categoria categoria) {
        super(descripcion, monto, new Date(), categoria);
    }

    // Constructor con descripción y categoría (monto 0.0, fecha actual)
    public Ingreso(String descripcion, Categoria categoria) {
        super(descripcion, 0.0, new Date(), categoria);
    }

    // Constructor con descripción, monto y fecha (categoría "Otro")
    public Ingreso(String descripcion, double monto, Date fecha) {
        this(descripcion, monto, fecha, new Categoria("Otro"));
    }

    // Constructor con descripción y monto (fecha actual y categoría "Otro")
    public Ingreso(String descripcion, double monto) {
        this(descripcion, monto, new Date(), new Categoria("Otro"));
    }

    // Constructor con solo descripción (monto 0.0, fecha actual, categoría "Otro")
    public Ingreso(String descripcion) {
        this(descripcion, 0.0, new Date(), new Categoria("Otro"));
    }

    // Getter para tipoMovimiento (atributo propio)
    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    @Override
    public boolean registrar() {
        if (this.monto <= 0.0) {
            return false;
        } else {
            System.out.println("Ingreso registrado: \nDescripción: " + descripcion
                    + "\nMonto: " + monto
                    + "\nFecha: " + fecha
                    + "\nCategoría: " + categoria.getNombreCategoria()
                    + "\nTipo de Movimiento: " + tipoMovimiento);
            return true;
        }
    }

    @Override
    public boolean validarDuplicado(Movimiento otroMovimiento) {
        if (!(otroMovimiento instanceof Ingreso)) {
            return false;
        }
        Ingreso otroIngreso = (Ingreso) otroMovimiento;
        return descripcion.equals(otroIngreso.descripcion) &&
                monto == otroIngreso.monto &&
                fecha.equals(otroIngreso.fecha) &&
                categoria.equals(otroIngreso.categoria);
    }

    @Override
    public void realizar() throws ExcepcionMifo.SaldoInsuficienteExcepcion, ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (this.monto <= 0.0) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("El monto del ingreso debe ser mayor que cero.");
        }
        System.out.println("Realizando ingreso: \nDescripción: " + descripcion
                + "\nMonto: " + monto
                + "\nFecha: " + fecha
                + "\nCategoría: " + categoria.getNombreCategoria()
                + "\nTipo de Movimiento: " + tipoMovimiento);
    }

    @Override
    public String toString() {
        return "Ingreso { " + super.toString() + ", TipoMovimiento= " + tipoMovimiento + " }";
    }
}
