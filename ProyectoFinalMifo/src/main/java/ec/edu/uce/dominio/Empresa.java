package ec.edu.uce.dominio;
import ec.edu.uce.util.ExcepcionMifo;
import ec.edu.uce.util.ExcepcionMifo.MovimientoInvalidoExcepcion;
import ec.edu.uce.util.ExcepcionMifo.UsuarioNoEncontradoExcepcion;
import java.util.Arrays;
import java.util.Date;

/**
 * Representa una empresa en el sistema, administrando usuarios, categorías y cursos de educación financiera.
 */
public class Empresa {
    /**
     * Representa una empresa que tiene una lista de usuarios asociados.
     * Relación de asociación (1 a muchos): Esta clase contiene un arreglo de usuarios,
     * indicando que una empresa puede tener múltiples usuarios asociados.
     */
    private Usuario[] usuarios;
    // Cantidad de usuarios registrados
    private int numUsuarios;
    /**
     * Arreglo estático para almacenar categorías.
     * Relación de asociación (1 a muchos): Una empresa puede tener múltiples categorías.
     */
    private static Categoria[] categorias;
    // Cantidad de categorías registradas
    private static int numCategorias;
    /**
     * Arreglo para almacenar cursos de educación financiera asociados a la empresa.
     *
     * Representa una relación de asociación entre Empresa y EducacionFinanciera,
     * donde una empresa puede estar asociada con múltiples cursos (1 a muchos).
     */
    private EducacionFinanciera[] educacionesFinancieras;
    /**
     * Cantidad de cursos de educación financiera registrados en la empresa.
     */
    private int numEducacionesFinancieras;

    // Contador para generar códigos únicos de usuario
    private int usuarioCodigoContador = 0;

    // Contador para generar códigos únicos de categoría
    private int categoriaCodigoContador = 0;

    // Contador para generar códigos únicos de educación financiera
    private int educacionCodigoContador = 0;
    // Constructor por defecto
    public Empresa() {
        this(0, 0, 0);
    }
    /**
     * Constructor con parámetros para inicializar la empresa con tamaños de arreglos dados.
     * Forma parte de la sobrecarga de constructores.
     * @param numUsuarios número inicial para el arreglo de usuarios.
     * @param numCategorias número inicial para el arreglo de categorías.
     * @param numEducacionesFinancieras número inicial para el arreglo de cursos.
     */
    public Empresa(int numUsuarios, int numCategorias, int numEducacionesFinancieras) {
        this.usuarios = new Usuario[numUsuarios];
        this.numUsuarios = 0;
        this.categorias = new Categoria[numCategorias];
        this.numCategorias = 0;
        this.educacionesFinancieras = new EducacionFinanciera[numEducacionesFinancieras];
        this.numEducacionesFinancieras = 0;
    }

    // Genera un nuevo código único de usuario
    public int agregarCodigoUsuario() {
        return ++this.usuarioCodigoContador;
    }
    /**
     * Crea un usuario con un código único y lo almacena en la empresa.
     * La empresa crea y almacena usuarios, estableciendo así una relación de asociación.
     * Se utiliza un arreglo Usuario[], lo que implica que puede haber múltiples usuarios dentro de una empresa,
     * reflejando una multiplicidad de 1 a muchos.
     * @param nombre     nombre del usuario a crear
     * @param contrasena contraseña del usuario
     * @param correo     correo electrónico del usuario
     * @return el usuario creado y almacenado
     */
    public String agregarUsuarioConCodigo(String nombre, String contrasena, String correo) {
        int codigo = this.agregarCodigoUsuario();
        if (this.numUsuarios == this.usuarios.length) {
            Usuario[] aux = new Usuario[this.usuarios.length + 1];
            System.arraycopy(this.usuarios, 0, aux, 0, this.usuarios.length);
            this.usuarios = aux;
        }
        this.usuarios[this.numUsuarios] = new Usuario(nombre, contrasena, correo,0 , codigo);
        this.numUsuarios++;
        return "Código: " + codigo + "\nNombre: " + nombre + "\nContraseña: " + contrasena + "\nCorreo: " + correo;

    }
    /**
     * Devuelve una representación en cadena de la empresa,
     * incluyendo la cantidad de usuarios, categorías y cursos de educación financiera registrados.
     * También muestra el detalle básico de cada usuario, categoría y curso.
     *
     * @return Cadena con información de la empresa.
     */
    // Genera un código único para una categoría
    public int agregarCodigoCategoria() {
        return ++categoriaCodigoContador;
    }
    public String agregarCategoriaConCodigo(String nombreCategoria) {
        int codigo = agregarCodigoCategoria();

        if (numCategorias == categorias.length) {
            Categoria[] aux = new Categoria[categorias.length + 1];
            System.arraycopy(categorias, 0, aux, 0, categorias.length);
            categorias = aux;
        }

        categorias[numCategorias] = new Categoria(nombreCategoria);
        numCategorias++;

        return "Código: " + codigo + "\nNombre Categoría: " + nombreCategoria;
    }
    public int agregarCodigoEducacionFinanciera() {
        return ++educacionCodigoContador;
    }
    public String agregarEducacionFinancieraConCodigo(
            String titulo,
            String descripcion,
            int duracionSemanas,
            String nivel,
            String modalidad,
            Categoria categoria,
            Date fechaInicio) {

        int codigo = agregarCodigoEducacionFinanciera();

        if (numEducacionesFinancieras == educacionesFinancieras.length) {
            EducacionFinanciera[] aux = new EducacionFinanciera[educacionesFinancieras.length + 1];
            System.arraycopy(educacionesFinancieras, 0, aux, 0, educacionesFinancieras.length);
            educacionesFinancieras = aux;
        }

        educacionesFinancieras[numEducacionesFinancieras] = new EducacionFinanciera(
                titulo, descripcion, duracionSemanas, nivel, modalidad, categoria, fechaInicio);

        numEducacionesFinancieras++;

        return "Código: " + codigo +
                "\nTítulo: " + titulo +
                "\nDescripción: " + descripcion +
                "\nDuración (semanas): " + duracionSemanas +
                "\nNivel: " + nivel +
                "\nModalidad: " + modalidad +
                "\nCategoría: " + categoria.toString() +
                "\nFecha de inicio: " + fechaInicio.toString();
    }

    public String agregarUsuario(String nombre, String contrasena, String correo) {
        int codigo = agregarCodigoUsuario();
        if (numUsuarios == usuarios.length) {
            Usuario[] aux = usuarios;
            usuarios = new Usuario[aux.length + 1];
            System.arraycopy(aux, 0, usuarios, 0, aux.length);
        }
        usuarios[numUsuarios] = new Usuario(nombre, contrasena, correo, numUsuarios,codigo);
        numUsuarios++;
        return "Código: " + codigo + "\nNombre: " + nombre + "\nContraseña: " + contrasena + "\nCorreo: " + correo;

    }
    public String agregarUsuario(Usuario nuevoUsuario) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i].equals(nuevoUsuario)) {
                throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe un usuario con este nombre.");
            }
        }

        if (numUsuarios == usuarios.length) {
            int nuevoTamano = usuarios.length == 0 ? 10 : usuarios.length * 2;
            Usuario[] aux = new Usuario[nuevoTamano];
            System.arraycopy(usuarios, 0, aux, 0, usuarios.length);
            usuarios = aux;
        }
        usuarios[numUsuarios] = nuevoUsuario;
        numUsuarios++;
        return "Usuario creado con éxito.\nNombre: " + nuevoUsuario.getNombre() + "\nContraseña: " + nuevoUsuario.getContrasena() + "\nCorreo: " + nuevoUsuario.getCorreo();
    }

    public String consultarUsuario() {
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : usuarios) {
            if (usuario != null) {
                texto.append(usuario).append("\n");
            }
        }
        return texto.toString();
    }

    public String consultarUsuario(Usuario nuevoUsuario) throws ExcepcionMifo.UsuarioNoEncontradoExcepcion {
        StringBuilder texto = new StringBuilder();
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equalsIgnoreCase(nuevoUsuario.getNombre() + nuevoUsuario.getContrasena() + nuevoUsuario.getCorreo())) {
                texto.append(usuario).append("\n");
            }
        }
        if (texto.length() > 0) {
            return texto.toString();
        } else {
            throw new ExcepcionMifo.UsuarioNoEncontradoExcepcion(nuevoUsuario.getNombre());
        }
    }

    public void editarUsuario(int indice, String nombre, String contrasena, String correo) {
        if (indice >= 0 && indice < numUsuarios) {
            usuarios[indice].setNombre(nombre);
            usuarios[indice].setContrasena(contrasena);
            usuarios[indice].setCorreo(correo);
        }
    }

    public void editarUsuario(int indice, Usuario nuevoUsuario) throws UsuarioNoEncontradoExcepcion {
        if (indice >= 0 && indice < numUsuarios) {
            usuarios[indice].setNombre(nuevoUsuario.getNombre());
            usuarios[indice].setContrasena(nuevoUsuario.getContrasena());
            usuarios[indice].setCorreo(nuevoUsuario.getCorreo());
        } else {
            throw new UsuarioNoEncontradoExcepcion("Usuario no encontrado con índice: " + indice);
        }
    }
    public void eliminarUsuario(int indice) throws UsuarioNoEncontradoExcepcion {
        if (indice < 0 || indice >= numUsuarios) {
            throw new UsuarioNoEncontradoExcepcion("Usuario no encontrado con índice: " + indice);
        }
        // Mover elementos hacia la izquierda para sobreescribir el eliminado
        for (int i = indice; i < numUsuarios - 1; i++) {
            usuarios[i] = usuarios[i + 1];
        }
        // Liberar la última referencia
        usuarios[numUsuarios - 1] = null;
        numUsuarios--;
    }
    public boolean validarCredenciales(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuario(int indice) throws UsuarioNoEncontradoExcepcion {
        if (indice >= 0 && indice < numUsuarios) {
            return usuarios[indice];
        } else {
            throw new UsuarioNoEncontradoExcepcion("Usuario no encontrado con índice: " + indice);
        }
    }

    public Usuario buscarUsuarioPorNombre(Usuario nuevoUsuario) throws UsuarioNoEncontradoExcepcion {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equalsIgnoreCase(nuevoUsuario.getNombre() + nuevoUsuario.getContrasena() + nuevoUsuario.getCorreo())) {
                return usuario;
            }
        }
        throw new UsuarioNoEncontradoExcepcion(nuevoUsuario.getNombre());
    }
    public String inicializarUsuarios() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(agregarUsuario("Usuario1", "Contraseña1", "usuario1@example.com")).append("\n");
        resultado.append(agregarUsuario("Usuario2", "Contraseña2", "usuario2@example.com")).append("\n");
        resultado.append(agregarUsuario("Usuario3", "Contraseña3", "usuario3@example.com")).append("\n");
        return resultado.toString();
    }
    public boolean validarDuplicado(String nombre) throws MovimientoInvalidoExcepcion {
        for (Usuario usuario : usuarios) {
            if (usuario != null && usuario.getNombre().equals(nombre)) {
                throw new MovimientoInvalidoExcepcion("Ya existe un usuario con el mismo nombre.");
            }
        }
        return false;
    }

    /**
     * Agrega una categoría ya creada al arreglo de categorías de la empresa.
     * Esto representa una relación de asociación entre Empresa y Categoria,
     * donde Empresa mantiene referencias a múltiples categorías.
     */
    public String agregarCategoria(Categoria nuevaCategoria) {
        if (numCategorias == categorias.length) {
            Categoria[] aux = new Categoria[categorias.length + 1];
            System.arraycopy(categorias, 0, aux, 0, categorias.length);
            categorias = aux;
        }
        categorias[numCategorias++] = nuevaCategoria;
        return "Nombre Categoria: " + nuevaCategoria.getNombreCategoria();
    }
    public String consultarCategoria(Categoria nuevaCategoria) {
        StringBuilder texto = new StringBuilder();
        for (Categoria categoria : categorias) {
            if (categoria != null && categoria.getNombreCategoria().equalsIgnoreCase(nuevaCategoria.getNombreCategoria())) {
                texto.append(categoria).append("\n");
            }
        }
        return texto.length() > 0 ? texto.toString() : "Categoria no encontrada";
    }

    public void editarCategoria(int indice, String nombreCategoria) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            categorias[indice].setNombreCategoria(nombreCategoria);
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public void editarCategoria(int indice, Categoria nuevaCategoria) throws MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            categorias[indice] = nuevaCategoria;
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public void eliminarCategoria(Categoria categoriaAEliminar) throws MovimientoInvalidoExcepcion {
        if (categoriaAEliminar == null) {
            throw new MovimientoInvalidoExcepcion("La categoría a eliminar no puede ser nula.");
        }
        int indice = -1;
        // Buscar índice de la categoría
        for (int i = 0; i < numCategorias; i++) {
            if (categorias[i] != null && categorias[i].getNombreCategoria().equalsIgnoreCase(categoriaAEliminar.getNombreCategoria())) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            throw new MovimientoInvalidoExcepcion("Categoría no encontrada: " + categoriaAEliminar.getNombreCategoria());
        }
        // Desplazar las categorías posteriores hacia la izquierda
        for (int i = indice; i < numCategorias - 1; i++) {
            categorias[i] = categorias[i + 1];
        }
        // Poner null en la última posición
        categorias[numCategorias - 1] = null;
        numCategorias--;
    }

    public static Categoria buscarCategoria(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numCategorias) {
            return categorias[indice];
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de categoría inválido.");
        }
    }

    public static Categoria buscarCategoria(Categoria nuevaCategoria) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (Categoria categoria : categorias) {
            if (categoria != null && categoria.getNombreCategoria().equalsIgnoreCase(nuevaCategoria.getNombreCategoria())) {
                return nuevaCategoria;
            }
        }
        throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Categoría no encontrada: " + nuevaCategoria.getNombreCategoria());
    }


    public String inicializarCategorias() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(agregarCategoriaConCodigo("Categoria1")).append("\n");
        resultado.append(agregarCategoriaConCodigo("Categoria2")).append("\n");
        resultado.append(agregarCategoriaConCodigo("Categoria3")).append("\n");
        return resultado.toString();
    }
    public String agregarEducacionFinanciera(String titulo, String contenido) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacion : educacionesFinancieras) {
            if (educacion != null && educacion.getTitulo().equals(titulo)) {
                throw new MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
            }
        }
        if (numEducacionesFinancieras == educacionesFinancieras.length) {
            EducacionFinanciera[] aux = educacionesFinancieras;
            educacionesFinancieras = new EducacionFinanciera[aux.length + 1];
            System.arraycopy(aux, 0, educacionesFinancieras, 0, aux.length);
        }
        int duracionSemanas = 4;
        String nivel = "Básico";
        String modalidad = "Virtual";
        Categoria categoria = numCategorias > 0 ? categorias[0] : null;
        Date fechaInicio = new Date(); // fecha actual

        educacionesFinancieras[numEducacionesFinancieras] = new EducacionFinanciera(
                titulo, contenido, duracionSemanas, nivel, modalidad, categoria, fechaInicio
        );
        numEducacionesFinancieras++;

        return "Título: " + titulo + "\nContenido: " + contenido;
    }
    public String agregarEducacionFinanciera(EducacionFinanciera nuevaEducacion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacion : educacionesFinancieras) {
            if (educacion != null && educacion.getTitulo().equals(nuevaEducacion.getTitulo())) {
                throw new MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
            }
        }

        if (numEducacionesFinancieras == educacionesFinancieras.length) {
            EducacionFinanciera[] aux = educacionesFinancieras;
            educacionesFinancieras = new EducacionFinanciera[aux.length + 1];
            System.arraycopy(aux, 0, educacionesFinancieras, 0, aux.length);
        }
        educacionesFinancieras[numEducacionesFinancieras] = nuevaEducacion;
        numEducacionesFinancieras++;
        return "Titulo: " + nuevaEducacion.getTitulo() + "\nContenido: " + nuevaEducacion.getDescripcion();
    }

    public String consultarEducacionFinanciera() {
        StringBuilder texto = new StringBuilder();
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null) {
                texto.append(educacionFinanciera).append("\n");
            }
        }
        return texto.toString();
    }

    public String consultarEducacionFinanciera(EducacionFinanciera nuevaEducacionFinanciera) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        StringBuilder texto = new StringBuilder();
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null && educacionFinanciera.equals(nuevaEducacionFinanciera.getTitulo() + nuevaEducacionFinanciera.getDescripcion())) {
                texto.append(educacionFinanciera).append("\n");
            }
        }
        if (texto.length() > 0) {
            return texto.toString();
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + nuevaEducacionFinanciera.getTitulo());
        }
    }

    public void editarEducacionFinanciera(int indice, String nuevoTitulo, String nuevoContenido) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            int duracionSemanas = 4;
            String nivel = "Básico";
            String modalidad = "Virtual";
            Categoria categoria = numCategorias > 0 ? categorias[0] : null;
            Date fechaInicio = new Date();

            EducacionFinanciera nuevaEducacion = new EducacionFinanciera(
                    nuevoTitulo, nuevoContenido, duracionSemanas, nivel, modalidad, categoria, fechaInicio
            );

            educacionesFinancieras[indice] = nuevaEducacion;
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public void editarEducacionFinanciera(int indice, EducacionFinanciera nuevaEducacion) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            educacionesFinancieras[indice] = nuevaEducacion;
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public void eliminarEducacionFinanciera(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            EducacionFinanciera[] aux = educacionesFinancieras;
            educacionesFinancieras = new EducacionFinanciera[aux.length - 1];
            if (indice == 0) {
                System.arraycopy(aux, 1, educacionesFinancieras, 0, educacionesFinancieras.length);
            } else if (indice == numEducacionesFinancieras - 1) {
                System.arraycopy(aux, 0, educacionesFinancieras, 0, educacionesFinancieras.length);
            } else {
                System.arraycopy(aux, 0, educacionesFinancieras, 0, indice);
                System.arraycopy(aux, indice + 1, educacionesFinancieras, indice, educacionesFinancieras.length - indice);
            }
            numEducacionesFinancieras--;
        } else {
            throw new MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public boolean eliminarEducacionFinanciera(String titulo) throws MovimientoInvalidoExcepcion {
        int indexToRemove = -1;
        for (int i = 0; i < educacionesFinancieras.length; i++) {
            if (educacionesFinancieras[i].getTitulo().equals(titulo)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + titulo);
        }

        EducacionFinanciera[] newArray = new EducacionFinanciera[educacionesFinancieras.length - 1];
        System.arraycopy(educacionesFinancieras, 0, newArray, 0, indexToRemove);
        System.arraycopy(educacionesFinancieras, indexToRemove + 1, newArray, indexToRemove, educacionesFinancieras.length - indexToRemove - 1);

        educacionesFinancieras = newArray;
        return true;
    }

    public EducacionFinanciera buscarEducacionFinanciera(int indice) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (indice >= 0 && indice < numEducacionesFinancieras) {
            return educacionesFinancieras[indice];
        } else {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Índice de educación financiera inválido.");
        }
    }

    public EducacionFinanciera buscarEducacionFinanciera(String titulo) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null && educacionFinanciera.getTitulo().equalsIgnoreCase(titulo)) {
                return educacionFinanciera;
            }
        }
        throw new MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + titulo);
    }

    public EducacionFinanciera buscarEducacionFinanciera(EducacionFinanciera nuevaEducacionFinanciera) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (EducacionFinanciera educacionFinanciera : educacionesFinancieras) {
            if (educacionFinanciera != null &&
                    educacionFinanciera.getTitulo().equalsIgnoreCase(nuevaEducacionFinanciera.getTitulo())) {
                return educacionFinanciera;
            }
        }
        throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Educación financiera no encontrada: " + nuevaEducacionFinanciera.getTitulo());
    }
    public String inicializarEducacionesFinancieras() {
        StringBuilder resultado = new StringBuilder();
        try {
            resultado.append(agregarEducacionFinanciera("El Buen uso del dinero", "buendin.pdf")).append("\n");
            resultado.append(agregarEducacionFinanciera("Las malas administracciones", "malad.pdf")).append("\n");
            resultado.append(agregarEducacionFinanciera("Como ahorrar", "ahorrar.pdf")).append("\n");
        } catch (ExcepcionMifo.MovimientoInvalidoExcepcion e) {
            e.printStackTrace();
        }
        return resultado.toString();
    }

    public boolean validarDuplicado(Object o) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        if (!(o instanceof EducacionFinanciera)) {
            return false;
        }
        EducacionFinanciera otraEducacion = (EducacionFinanciera) o;
        if (this.equals(otraEducacion)) {
            throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Ya existe una educación financiera con el mismo título.");
        }
        return false;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public void setNumUsuarios(int numUsuarios) {
        this.numUsuarios = numUsuarios;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria[] categorias) {
        this.categorias = categorias;
    }

    public int getNumCategorias() {
        return numCategorias;
    }

    public void setNumCategorias(int numCategorias) {
        this.numCategorias = numCategorias;
    }

    public EducacionFinanciera[] getEducacionesFinancieras() {
        return educacionesFinancieras;
    }

    public void setEducacionesFinancieras(EducacionFinanciera[] educacionesFinancieras) {
        this.educacionesFinancieras = educacionesFinancieras;
    }

    public int getNumEducacionesFinancieras() {
        return numEducacionesFinancieras;
    }

    public void setNumEducacionesFinancieras(int numEducacionesFinancieras) {
        this.numEducacionesFinancieras = numEducacionesFinancieras;
    }

    @Override
    public String toString() {
        return "Usuarios=" + Arrays.toString(usuarios)
                + "Num Usuarios=" + numUsuarios
                + "Educaciones Financieras=" + Arrays.toString(educacionesFinancieras)
                + "Num Educaciones Financieras=" + numEducacionesFinancieras
                + "Categorias=" + Arrays.toString(categorias)
                + "Num Categorias " + numCategorias;
    }

    public Categoria buscarCategoriaPorNombre(String nombreCategoria) throws ExcepcionMifo.MovimientoInvalidoExcepcion {
        for (int i = 0; i < numCategorias; i++) {
            if (categorias[i].getNombreCategoria().equalsIgnoreCase(nombreCategoria)) {
                return categorias[i];
            }
        }
        throw new ExcepcionMifo.MovimientoInvalidoExcepcion("Categoría no encontrada: " + nombreCategoria);
    }
    }
