package ec.edu.uce.dominio;
import java.util.Date;
public class SolicitudCurso {
    //private EducacionFinanciera cursoSolicitado; // Asociación con curso
    private Usuario usuario;

    private Date fechaSolicitud;

    public SolicitudCurso() {
        this.fechaSolicitud = new Date();
    }
    public SolicitudCurso(EducacionFinanciera cursoSolicitado) {
        this.cursoSolicitado = cursoSolicitado;
        this.fechaSolicitud = new Date();
    }

    public EducacionFinanciera getCursoSolicitado() {
        return cursoSolicitado;
    }

    public void setCursoSolicitado(EducacionFinanciera cursoSolicitado) {
        this.cursoSolicitado = cursoSolicitado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @Override
    public String toString() {
        return "Solicitud de curso:\n" +
                cursoSolicitado + "\n" +
                "Fecha de solicitud: " + fechaSolicitud;
    }
}




