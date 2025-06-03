package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo;
import java.util.Date;
/**
 * Clase que representa un usuario del sistema financiero.
 * Contiene información personal y arreglos dinámicos de presupuestos y solicitudes de curso.
 */
public class Usuario {
    private String nombre;
    private String correo;
    private String cedula;
    private String contrasena;
    private int codigo;

    /**
     * Arreglo de presupuestos asociados a este usuario.
     * Relación: Un Usuario puede tener uno o más Presupuestos.
     * Multiplicidad: 1 Usuario --- 1..n Presupuesto
     */
    private Presupuesto[] presupuestos;
    private int numPresupuestos;

    /**
     * Arreglo de solicitudes de cursos asociadas a este usuario.
     * Relación: Un Usuario puede tener cero o más SolicitudCurso.
     * Multiplicidad: 1 Usuario --- 0..n SolicitudCurso
     */
    private SolicitudCurso[] solicitudes;
    private int numSolicitudes;

    /**
     * Constructor principal para crear un usuario con nombre, contraseña, correo,
     * número inicial de presupuestos y código.
     *
     * @param nombre         nombre del usuario
     * @param contrasena     contraseña del usuario
     * @param correo         correo electrónico del usuario
     * @param numPresupuestos número inicial de presupuestos
     * @param codigo         código identificador del usuario
     */
    public Usuario(String nombre, String contrasena, String correo, int numPresupuestos, int codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.presupuestos = new Presupuesto[10];
        this.numPresupuestos = numPresupuestos;
        this.solicitudes = new SolicitudCurso[10]; // Inicializar también solicitudes
        this.numSolicitudes = 0;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre nuevo nombre a establecer
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el código identificador del usuario.
     *
     * @return código del usuario
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el código identificador del usuario.
     *
     * @param codigo nuevo código a establecer
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return correo electrónico
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo nuevo correo a establecer
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la cédula del usuario.
     *
     * @return cédula del usuario
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     *
     * @param cedula nueva cédula a establecer
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena nueva contraseña a establecer
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene una copia del arreglo de presupuestos.
     *
     * @return arreglo con los presupuestos actuales
     */
    public Presupuesto[] getPresupuestos() {
        Presupuesto[] result = new Presupuesto[this.numPresupuestos];
        System.arraycopy(this.presupuestos, 0, result, 0, this.numPresupuestos);
        return result;
    }

    /**
     * Obtiene el número actual de presupuestos asociados.
     *
     * @return número de presupuestos
     */
    public int getNumPresupuestos() {
        return numPresupuestos;
    }

    /**
     * Establece el número de presupuestos actuales.
     *
     * @param numPresupuestos nuevo número de presupuestos
     */
    public void setNumPresupuestos(int numPresupuestos) {
        this.numPresupuestos = numPresupuestos;
    }

    public void cambiarContrasena(String contrasenaActual, String nuevaContrasena) throws ExcepcionMifo.ContrasenaInvalidaExcepcion {
        if (this.contrasena.equals(contrasenaActual)) {
            this.contrasena = nuevaContrasena;
            System.out.println("Contraseña cambiada correctamente.");
        } else {
            throw new ExcepcionMifo.ContrasenaInvalidaExcepcion("La contraseña actual no coincide. No se pudo cambiar la contraseña.");
        }
    }

    public void crearPresupuesto(Double presupuesto, Date fecha) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        Presupuesto nuevoPresupuesto = new Presupuesto(presupuesto, fecha);
        if (validarDuplicado(nuevoPresupuesto)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un presupuesto con el mismo valor y fecha.");
        }
        if (numPresupuestos == presupuestos.length) {
            Presupuesto[] aux = presupuestos;
            presupuestos = new Presupuesto[aux.length + 1];
            System.arraycopy(aux, 0, presupuestos, 0, aux.length);
        }
        presupuestos[numPresupuestos] = nuevoPresupuesto;
        numPresupuestos++;
    }
    public String crearPresupuesto(Presupuesto nuevoPresupuesto) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (validarDuplicado(nuevoPresupuesto)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un presupuesto con el mismo valor y fecha.");
        }

        if (numPresupuestos == presupuestos.length) {
            Presupuesto[] aux = presupuestos;
            presupuestos = new Presupuesto[aux.length + 1];
            System.arraycopy(aux, 0, presupuestos, 0, aux.length);
        }
        presupuestos[numPresupuestos] = nuevoPresupuesto;
        numPresupuestos++;

        return "Presupuesto: " + nuevoPresupuesto.getPresupuesto() + "\nFecha: " + nuevoPresupuesto.getFecha();
    }

    public String consultarPresupuesto() {
        StringBuilder texto = new StringBuilder();
        for (Presupuesto presupuesto : presupuestos) {
            if (presupuesto != null) {
                texto.append(presupuesto).append("\n");
            }
        }
        return texto.toString();
    }

    public void editarPresupuesto(int indice, double presupuesto, Date fecha, double gastoTotal, double ingresoTotal) {
        if (indice >= 0 && indice < numPresupuestos) {
            Presupuesto p = presupuestos[indice];
            if (p != null) {
                p.setPresupuesto(presupuesto);
                p.setFecha(fecha);
                p.setGastoTotal(gastoTotal);
                p.setIngresoTotal(ingresoTotal);
            } else {
                System.out.println("El presupuesto en el índice " + indice + " es nulo.");
            }
        } else {
            System.out.println("Índice fuera de rango: " + indice);
        }
    }

    public void editarPresupuesto(int indice, Presupuesto nuevoPresupuesto) {
        if (indice >= 0 && indice < numPresupuestos) {
            presupuestos[indice].setPresupuesto(nuevoPresupuesto.getPresupuesto());
            presupuestos[indice].setFecha(nuevoPresupuesto.getFecha());
            presupuestos[indice].setGastoTotal(nuevoPresupuesto.getGastoTotal());
            presupuestos[indice].setIngresoTotal(nuevoPresupuesto.getIngresoTotal());
        }
    }
    public Presupuesto buscarPresupuesto(int indice) {
        if (indice >= 0 && indice < numPresupuestos) {
            return presupuestos[indice];
        } else {
            return null;
        }
    }
    public void eliminarPresupuesto(int indice) {
        if (indice >= 0 && indice < numPresupuestos) {
            Presupuesto[] aux = presupuestos;
            presupuestos = new Presupuesto[aux.length - 1];
            System.arraycopy(aux, 0, presupuestos, 0, indice);
            System.arraycopy(aux, indice + 1, presupuestos, indice, presupuestos.length - indice);
            numPresupuestos--;
        }
    }

    public void inicializarPresupuestos() {
        try {
            this.crearPresupuesto(2000.0, new Date());
            this.crearPresupuesto(3000.0, new Date());
            this.crearPresupuesto(2500.0, new Date());
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            e.printStackTrace();
        }
    }
    public boolean validarDuplicado(Presupuesto nuevoPresupuesto) {
        for (int i = 0; i < numPresupuestos; i++) {
            Presupuesto presupuestoExistente = presupuestos[i];
            if (presupuestoExistente.getFecha().equals(nuevoPresupuesto.getFecha())
                    && Double.compare(presupuestoExistente.getPresupuesto(), nuevoPresupuesto.getPresupuesto()) == 0) {
                return true;
            }
        }
        return false;
    }
    public void inicializarUsuario(String nombre, String contrasena, String correo, int codigo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.codigo = codigo;
        this.presupuestos = new Presupuesto[0];
        this.numPresupuestos = 0;
    }
    public boolean equals(Object object) {
        Usuario otroUsuario = null;
        boolean resp = false;

        if (object != null && object instanceof Usuario) {
            otroUsuario = (Usuario) object;

            if (this.codigo == otroUsuario.codigo) {
                resp = true;
            }
        }
        return resp;
    }
    public int hashCode() {
        return codigo;
    }

    /**
     * Método toString sobrescrito para mostrar una representación en cadena
     * de la instancia de Usuario, mostrando atributos básicos y cantidades de arreglos.
     *
     * @return cadena representativa del usuario
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", cedula='" + cedula + '\'' +
                ", numPresupuestos=" + numPresupuestos +
                ", numSolicitudes=" + numSolicitudes +
                '}';
    }
}
