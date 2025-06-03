package ec.edu.uce.dominio;

import java.io.Serializable;
import java.util.Date;

import ec.edu.uce.util.ExcepcionMifo;

/**
 * Representa un curso de educación financiera.
 * Contiene información sobre el título, descripción, duración,
 * nivel de dificultad, modalidad, categoría y fecha de inicio.
 * Esta clase mantiene una relación de asociación con SolicitudCurso:
 * 1 EducacionFinanciera puede tener 0 o más SolicitudCurso (1 a 0..n).
 */
public class EducacionFinanciera implements Serializable {

    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descripcion;
    private int duracionSemanas;
    private String nivel;
    private String modalidad;
    private Date fechaInicio;
    private Categoria categoria;

    private SolicitudCurso[] solicitudes;
    private int numSolicitudes;

    /**
     * Constructor por defecto con valores predeterminados.
     */
    public EducacionFinanciera() {
        this("Sin título", "Sin descripción", 0, "Desconocido", "Desconocido", new Categoria(), new Date());
    }

    /**
     * Constructor con parámetros para inicializar el curso.
     */
    public EducacionFinanciera(String titulo, String descripcion, int duracionSemanas, String nivel,
                               String modalidad, Categoria categoria, Date fechaInicio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracionSemanas = duracionSemanas;
        this.nivel = nivel;
        this.modalidad = modalidad;
        this.categoria = categoria;
        this.fechaInicio = fechaInicio;
        this.solicitudes = new SolicitudCurso[10];
        this.numSolicitudes = 0;
    }

    // Getters y Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionSemanas() {
        return duracionSemanas;
    }

    public void setDuracionSemanas(int duracionSemanas) {
        this.duracionSemanas = duracionSemanas;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve un arreglo con las solicitudes asociadas (copia para seguridad).
     */
    public SolicitudCurso[] getSolicitudes() {
        SolicitudCurso[] copia = new SolicitudCurso[numSolicitudes];
        System.arraycopy(solicitudes, 0, copia, 0, numSolicitudes);
        return copia;
    }

    /**
     * Agrega una nueva solicitud asociada a este curso.
     * @param solicitud Solicitud a agregar.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si la solicitud es nula.
     */
    public void agregarSolicitud(SolicitudCurso solicitud) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (solicitud == null) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("La solicitud no puede ser nula.");
        }
        if (numSolicitudes == solicitudes.length) {
            SolicitudCurso[] aux = new SolicitudCurso[numSolicitudes + 10];
            System.arraycopy(solicitudes, 0, aux, 0, numSolicitudes);
            solicitudes = aux;
        }
        solicitudes[numSolicitudes++] = solicitud;
    }

    /**
     * Obtiene una solicitud en un índice específico.
     * @param indice Índice de la solicitud.
     * @return SolicitudCurso en la posición indicada.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si índice inválido.
     */
    public SolicitudCurso consultarSolicitud(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice < 0 || indice >= numSolicitudes) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice inválido para obtener solicitud.");
        }
        return solicitudes[indice];
    }

    /**
     * Edita una solicitud en el índice indicado, cambiando la solicitud por otra.
     * @param indice Índice a editar.
     * @param nuevaSolicitud Nueva solicitud que reemplaza a la anterior.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si índice inválido o solicitud nula.
     */
    public void editarSolicitud(int indice, SolicitudCurso nuevaSolicitud) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (nuevaSolicitud == null) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("La nueva solicitud no puede ser nula.");
        }
        if (indice < 0 || indice >= numSolicitudes) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice inválido para editar solicitud.");
        }
        solicitudes[indice] = nuevaSolicitud;
    }

    /**
     * Elimina una solicitud por índice.
     * @param indice Índice a eliminar.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si índice inválido.
     */
    public void eliminarSolicitud(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice < 0 || indice >= numSolicitudes) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice inválido para eliminar solicitud.");
        }
        for (int i = indice; i < numSolicitudes - 1; i++) {
            solicitudes[i] = solicitudes[i + 1];
        }
        solicitudes[numSolicitudes - 1] = null;
        numSolicitudes--;
    }

    /**
     * Elimina una solicitud por objeto.
     * @param solicitud Solicitud a eliminar.
     * @throws ExcepcionMifo.MovimientoInvalidoExcepcion si solicitud nula o no encontrada.
     */
    public void eliminarSolicitud(SolicitudCurso solicitud) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (solicitud == null) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("La solicitud a eliminar no puede ser nula.");
        }
        int index = -1;
        for (int i = 0; i < numSolicitudes; i++) {
            if (solicitudes[i].equals(solicitud)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("La solicitud no existe.");
        }
        eliminarSolicitud(index);
    }

    /**
     * Equals para comparar dos cursos.
     */
    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o != null && o instanceof EducacionFinanciera) {
            EducacionFinanciera that = (EducacionFinanciera) o;

            // Compara todos los campos importantes
            if (this.duracionSemanas == that.duracionSemanas
                    && this.titulo.equals(that.titulo)
                    && this.descripcion.equals(that.descripcion)
                    && this.nivel.equals(that.nivel)
                    && this.modalidad.equals(that.modalidad)
                    && this.categoria.equals(that.categoria)
                    && this.fechaInicio.equals(that.fechaInicio)) {
                result = true;
            }
        }
        return result;
    }
    /**
     * toString con información completa del curso.
     */
    @Override
    public String toString() {
        return "Curso de Educación Financiera:\n" +
                "Título: " + titulo + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Duración (semanas): " + duracionSemanas + "\n" +
                "Nivel: " + nivel + "\n" +
                "Modalidad: " + modalidad + "\n" +
                "Categoría: " + categoria + "\n" +
                "Fecha de inicio: " + fechaInicio + "\n" +
                "Número de solicitudes: " + numSolicitudes;
    }
}
