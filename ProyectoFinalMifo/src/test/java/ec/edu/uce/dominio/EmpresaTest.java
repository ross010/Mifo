package ec.edu.uce.dominio;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ec.edu.uce.util.ExcepcionMifo;

import java.util.Date;

/**
 * Prueba unitaria para {@link Empresa}.
 * Se verifican la creación de usuarios, categorías y cursos de educación financiera.
 */
class EmpresaTest {

    /**
     * Prueba el constructor por defecto de la clase Empresa.
     * Se espera que la empresa se inicialice correctamente sin datos.
     */
    @Test
    void testConstructorPorDefecto() {
        Empresa empresa = new Empresa();
        System.out.println("Probando constructor por defecto...");
        System.out.println("Instancia de empresa creada correctamente.");
        assertNotNull(empresa);
    }

    /**
     * Prueba la creación de un usuario dentro de la empresa.
     * Se verifica que el usuario sea agregado correctamente y se muestre con su código asignado.
     */
    @Test
    void testCrearUsuarioConCodigo() {
        Empresa empresa = new Empresa();
        String usuarioInfo = empresa.agregarUsuarioConCodigo("Juan", "1234", "juan@correo.com");

        System.out.println("Probando creación de usuario...");
        System.out.println("Salida obtenida:\n" + usuarioInfo);

        assertTrue(usuarioInfo.contains("Código:"));
        assertTrue(usuarioInfo.contains("Nombre: Juan"));
        assertTrue(usuarioInfo.contains("Correo: juan@correo.com"));
    }

    /**
     * Prueba la generación de código único para una categoría.
     * Se verifica que el código generado sea mayor a cero.
     */
    @Test
    void testCrearCodigoCategoria() {
        Empresa empresa = new Empresa();
        double codigo = empresa.agregarCodigoCategoria();

        System.out.println("Probando generación de código de categoría...");
        System.out.println("Código generado: " + codigo);

        assertTrue(codigo > 0);
    }

    /**
     * Prueba la creación de una categoría dentro de la empresa.
     * Se verifica que la categoría sea agregada correctamente.
     */
    @Test
    void testCrearCategoriaConCodigo() {
        Empresa empresa = new Empresa();
        String categoriaInfo = empresa.agregarCategoriaConCodigo("Alimentación");

        System.out.println("Probando creación de categoría...");
        System.out.println("Salida obtenida:\n" + categoriaInfo);

        assertTrue(categoriaInfo.contains("Código:"));
        assertTrue(categoriaInfo.contains("Nombre Categoría: Alimentación"));
    }

    /**
     * Prueba el método {@link Empresa#buscarCategoriaPorNombre(String)}.
     * Se verifica que la búsqueda de una categoría existente funcione correctamente.
     */
    @Test
    void testBuscarCategoriaPorNombre() {
        Empresa empresa = new Empresa();
        empresa.agregarCategoria(new Categoria("Transporte"));

        System.out.println("Probando búsqueda de categoría...");
        assertDoesNotThrow(() -> empresa.buscarCategoriaPorNombre("Transporte"));
        assertThrows(ExcepcionMifo.MovimientoInvalidoExcepcion.class, () -> empresa.buscarCategoriaPorNombre("NoExiste"));
    }

    /**
     * Prueba la creación de un curso de educación financiera con código.
     * Se verifica que el curso sea registrado correctamente.
     */
    @Test
    void testCrearEducacionFinancieraConCodigo() {
        Empresa empresa = new Empresa();
        String cursoInfo = empresa.agregarEducacionFinancieraConCodigo(
                "Ahorro Inteligente",
                "Curso sobre ahorro y finanzas personales",
                5,
                "Intermedio",
                "COD123",
                new Categoria("Educación"),
                new Date()
        );
        System.out.println("Probando creación de curso de educación financiera...");
        System.out.println("Salida obtenida:\n" + cursoInfo);

        assertTrue(cursoInfo.contains("Código:"));
        assertTrue(cursoInfo.contains("Título: Ahorro Inteligente"));
        assertTrue(cursoInfo.contains("Descripción: Curso sobre ahorro y finanzas personales"));
    }

    /**
     * Prueba el método {@link Empresa#consultarUsuario()}.
     * Se verifica que los usuarios registrados puedan ser listados correctamente.
     */
    @Test
    void testConsultarUsuario() {
        Empresa empresa = new Empresa();
        empresa.agregarUsuarioConCodigo("Maria", "pass123", "maria@correo.com");

        String usuarios = empresa.consultarUsuario();

        System.out.println("Probando consulta de usuario...");
        System.out.println("Salida obtenida:\n" + usuarios);

        assertTrue(usuarios.contains("Maria"));
    }

    /**
     * Prueba el método {@link Empresa#buscarUsuario(int)}.
     * Se verifica que la búsqueda de usuario por índice sea correcta.
     */
    @Test
    void testBuscarUsuario() {
        Empresa empresa = new Empresa();
        empresa.agregarUsuarioConCodigo("Pedro", "clave567", "pedro@correo.com");

        System.out.println("Probando búsqueda de usuario...");
        assertDoesNotThrow(() -> empresa.buscarUsuario(0));
        assertThrows(ExcepcionMifo.UsuarioNoEncontradoExcepcion.class, () -> empresa.buscarUsuario(5));
    }
}