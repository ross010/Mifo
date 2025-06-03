package ec.edu.uce.util;
public class ComprobacionCredenciales {
    public static boolean esValida(String contrasena) {
        return contrasena.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    }
}
