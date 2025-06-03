package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Date;
/**
 * Clase que representa un presupuesto que contiene movimientos financieros
 * (ingresos y gastos) con sus respectivas categorías.
 */
public class Presupuesto {
    private double presupuesto;
    private Date fecha;
    private double gastoTotal;
    private double ingresoTotal;
    /**
     * Usuario propietario de este presupuesto.
     * Relación: Cada Presupuesto pertenece a un único Usuario.
     * Multiplicidad: 1 Presupuesto --- 1 Usuario
     */
    private Usuario usuario;
    /**
     * Arreglo privado que contiene los movimientos del presupuesto.
     * Relación de composición: Un Presupuesto contiene múltiples Movimientos.
     * Multiplicidad: 1 Presupuesto --- 1..n Movimientos
     */
    private Movimiento[] movimientos;
    private int numMovimientos;
    private TipoPresupuesto tipo;

    /**
     * Constructor completo.
     *
     * @param presupuesto presupuesto inicial
     * @param fecha fecha del presupuesto
     * @param gastoTotal gasto total registrado
     * @param ingresoTotal ingreso total registrado
     * @param numMovimientos número de movimientos registrados
     * @param tipo tipo de presupuesto (anual, mensual, semanal)
     */
    public Presupuesto(double presupuesto, Date fecha, double gastoTotal, double ingresoTotal, int numMovimientos, TipoPresupuesto tipo) {
        this.presupuesto = presupuesto;
        this.fecha = fecha;
        this.gastoTotal = gastoTotal;
        this.ingresoTotal = ingresoTotal;
        this.movimientos = new Movimiento[10];
        this.numMovimientos = numMovimientos;
        this.tipo = tipo;
    }

    /**
     * Constructor básico con presupuesto y fecha, inicializa sin movimientos.
     *
     * @param presupuesto presupuesto inicial
     * @param fecha fecha del presupuesto
     */
    public Presupuesto(Double presupuesto, Date fecha) {
        this(presupuesto, fecha, 0.0, 0.0, 0, null);
    }

    /**
     * Constructor básico con usuario asignado.
     *
     * @param presupuesto presupuesto inicial
     * @param fecha fecha del presupuesto
     * @param usuario usuario propietario del presupuesto
     */

    public Presupuesto(double presupuesto, Date fecha, Usuario usuario) {
        this(presupuesto, fecha, 0.0, 0.0, 0, null);
        this.usuario = usuario;
    }

    // Getters y setters

    public double getPresupuesto() {
        return this.presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getGastoTotal() {
        return this.gastoTotal;
    }

    public void setGastoTotal(double gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public double getIngresoTotal() {
        return this.ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    public Movimiento[] getMovimientos() {
        Movimiento[] result = new Movimiento[this.numMovimientos];
        System.arraycopy(this.movimientos, 0, result, 0, this.numMovimientos);
        return result;
    }

    public int getNumMovimientos() {
        return this.numMovimientos;
    }

    public void setNumMovimientos(int numMovimientos) {
        this.numMovimientos = numMovimientos;
    }

    public TipoPresupuesto getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoPresupuesto tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // Métodos para agregar, editar, eliminar, buscar movimientos

    /**
     * Agrega un movimiento al presupuesto.
     * Este método es parte de la composición: solo la clase Presupuesto puede
     * agregar movimientos a su arreglo interno. El arreglo es privado y no
     * hay forma externa de modificarlo directamente.
     *
     * @param movimiento Movimiento a agregar
     */
    public void agregarMovimiento(Movimiento movimiento) {
        if (this.numMovimientos == this.movimientos.length) {
            Movimiento[] nuevoArray = new Movimiento[this.movimientos.length * 2];
            System.arraycopy(this.movimientos, 0, nuevoArray, 0, this.movimientos.length);
            this.movimientos = nuevoArray;
        }

        this.movimientos[this.numMovimientos] = movimiento;
        this.numMovimientos++;

        if (movimiento instanceof Ingreso) {
            this.presupuesto += movimiento.getMonto();
            this.ingresoTotal += movimiento.getMonto();
        } else if (movimiento instanceof Gasto) {
            this.presupuesto -= movimiento.getMonto();
            this.gastoTotal += movimiento.getMonto();
        }
    }

    public String agregarMovimiento(String descripcion, double monto, Date fecha, boolean esIngreso, Categoria categoria) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Movimiento nuevoMovimiento;
        if (esIngreso) {
            nuevoMovimiento = new Ingreso(descripcion, monto, fecha, categoria);
        } else {
            nuevoMovimiento = new Gasto(descripcion, monto, fecha, categoria);
        }

        for (int i = 0; i < this.numMovimientos; i++) {
            if (this.movimientos[i] != null && this.movimientos[i].equals(nuevoMovimiento)) {
                throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un movimiento con la misma descripción, monto y fecha.");
            }
        }

        this.agregarMovimiento(nuevoMovimiento);

        return "Descripción: " + descripcion
                + "\nMonto: " + monto
                + "\nFecha: " + fecha
                + "\nCategoría: " + (categoria != null ? categoria.getNombreCategoria() : "Sin categoría");
    }
    /**
     * Edita un movimiento existente.
     * Solo Presupuesto puede modificar los movimientos, reforzando la composición:
     * los objetos Movimiento no existen sin el Presupuesto que los contiene.
     *
     * @param indice Índice del movimiento a editar
     * @param nuevaDescripcion Nueva descripción del movimiento
     * @param nuevoMonto Nuevo monto del movimiento
     * @param nuevaFecha Nueva fecha del movimiento
     * @param esIngreso True si es ingreso, false si es gasto
     * @param categoria Nueva categoría asignada
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si el índice es inválido
     */
    public void editarMovimiento(int indice, String nuevaDescripcion, double nuevoMonto, Date nuevaFecha, boolean esIngreso, Categoria categoria) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice < 0 || indice >= this.numMovimientos) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de movimiento inválido.");
        }

        Movimiento movimientoOriginal = this.movimientos[indice];
        double montoAnterior = movimientoOriginal.getMonto();

        movimientoOriginal.setDescripcion(nuevaDescripcion);
        movimientoOriginal.setMonto(nuevoMonto);
        movimientoOriginal.setFecha(nuevaFecha);

        if (movimientoOriginal instanceof Gasto gasto) {
            gasto.setCategoria(categoria);
        } else if (movimientoOriginal instanceof Ingreso ingreso) {
            ingreso.setCategoria(categoria);
        }

        if (movimientoOriginal instanceof Ingreso) {
            if (esIngreso) {
                this.presupuesto = this.presupuesto - montoAnterior + nuevoMonto;
            } else {
                this.presupuesto = this.presupuesto - montoAnterior - nuevoMonto;
            }
        } else if (movimientoOriginal instanceof Gasto) {
            if (esIngreso) {
                this.presupuesto = this.presupuesto + montoAnterior + nuevoMonto;
            } else {
                this.presupuesto = this.presupuesto + montoAnterior - nuevoMonto;
            }
        }

        if (esIngreso) {
            this.ingresoTotal += nuevoMonto - montoAnterior;
        } else {
            this.gastoTotal += nuevoMonto - montoAnterior;
        }
    }
    /**
     * Elimina un movimiento por su índice.
     * Este método es parte de la composición: solo Presupuesto controla
     * el ciclo de vida de sus movimientos.
     *
     * @param indice Índice del movimiento a eliminar
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si el índice no es válido
     */
    public void eliminarMovimiento(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice < 0 || indice >= this.numMovimientos) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de movimiento inválido.");
        }

        Movimiento movimiento = this.movimientos[indice];
        if (movimiento instanceof Ingreso) {
            this.presupuesto -= movimiento.getMonto();
            this.ingresoTotal -= movimiento.getMonto();
        } else if (movimiento instanceof Gasto) {
            this.presupuesto += movimiento.getMonto();
            this.gastoTotal -= movimiento.getMonto();
        }

        for (int i = indice; i < this.numMovimientos - 1; i++) {
            this.movimientos[i] = this.movimientos[i + 1];
        }
        this.movimientos[this.numMovimientos - 1] = null;
        this.numMovimientos--;
    }

    public Movimiento buscarMovimiento(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice < 0 || indice >= this.numMovimientos) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de movimiento inválido.");
        }
        return this.movimientos[indice];
    }
    /**
     * Retorna un resumen en forma de texto de todos los movimientos registrados en el presupuesto.
     */
    public String consultarMovimientos() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < this.numMovimientos; i++) {
            texto.append(this.movimientos[i]).append("\n");
        }
        return texto.toString();
    }

    public String inicializarMovimientos() {
        StringBuilder resultado = new StringBuilder();
        try {
            resultado.append(this.agregarMovimiento("Sueldo", 1000.0, new Date(), true, new Categoria("Salario")));
            resultado.append("\n");
            resultado.append(this.agregarMovimiento("Pago luz", 20.0, new Date(), false, new Categoria("Servicios")));
            resultado.append("\n");
            resultado.append(this.agregarMovimiento("Pago agua", 30.0, new Date(), false, new Categoria("Servicios")));
            resultado.append("\n");
            resultado.append(this.agregarMovimiento("Venta libro", 25.0, new Date(), true, new Categoria("Ventas")));
            resultado.append("\n");
            resultado.append(this.agregarMovimiento("Internet", 10.0, new Date(), false, new Categoria("Servicios")));
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            return "Error al inicializar movimientos: " + e.getMessage();
        }
        return resultado.toString();
    }
    /**
     * Retorna una representación en cadena del objeto Presupuesto,
     * mostrando los atributos principales: presupuesto actual, fecha,
     * totales de gasto e ingreso, número de movimientos, tipo de presupuesto
     * y nombre del usuario propietario (si existe).
     *
     * @return una cadena con la información resumida del presupuesto
     */
    @Override
    public String toString() {
        return "Presupuesto{" +
                "presupuesto=" + presupuesto +
                ", fecha=" + fecha +
                ", gastoTotal=" + gastoTotal +
                ", ingresoTotal=" + ingresoTotal +
                ", numMovimientos=" + numMovimientos +
                ", tipo=" + tipo +
                ", usuario=" + (usuario != null ? usuario.getNombre() : "null") +
                '}';
    }
}
