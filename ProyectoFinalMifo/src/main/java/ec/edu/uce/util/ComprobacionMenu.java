package ec.edu.uce.util;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Calendar;
public class ComprobacionMenu {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static boolean validarDescripcion(@NotNull String descripcion) {
        descripcion = descripcion.trim();
        if (descripcion.isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return false;
        }
        if (descripcion.matches("\\d+")) {
            System.out.println("La descripción no puede contener solo números.");
            return false;
        }
        int letras = 0;
        for (char c : descripcion.toCharArray()) {
            if (Character.isLetter(c)) letras++;
        }
        if (letras < 5) {
            System.out.println("La descripción debe tener al menos 5 letras.");
            return false;
        }
        return true;
    }
    @Nullable
    public static Double validarMonto(String montoStr) {
        if (montoStr == null || montoStr.trim().isEmpty()) {
            System.out.println("El monto no puede estar vacío.");
            return null;
        }
        if (!montoStr.matches("^\\d+(\\.\\d{1,2})?$")) {
            System.out.println("El monto debe ser un número válido (puede tener hasta 2 decimales).");
            return null;
        }
        try {
            double monto = Double.parseDouble(montoStr);
            if (monto <= 0) {
                System.out.println("El monto debe ser mayor que cero.");
                return null;
            }
            return monto;
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el monto. Asegúrate de que sea un número válido.");
            return null;
        }
    }
    @Nullable
    /*public static Date validarFecha(String fechaStr) {
        Date fecha = null;
        try {
            if (!fechaStr.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.out.println(" La fecha ingresada no tiene el formato correcto (DIA/MES/AÑO). ");
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            fecha = sdf.parse(fechaStr);
            if (fecha.before(new Date())) {
                System.out.println(" La fecha ingresada está en el pasado. ");
                return null;
            }

        } catch (ParseException e) {
            System.out.println(" La fecha ingresada no es válida. ");
        }
        return fecha;
    }*/
    public static Date validarFecha(String fechaStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        if (!fechaStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            System.out.println(" Formato inválido. Use dd/MM/yyyy. Ej: 31/12/2025. ");
            return null;
        }

        try {
            Date fecha = dateFormat.parse(fechaStr);
            Date fechaMinima = dateFormat.parse("15/05/2025");

            if (fecha.before(fechaMinima)) {
                System.out.println("La fecha no puede ser anterior al 15/05/2025. ");
                return null;
            }

            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            int anio = cal.get(Calendar.YEAR);

            if (anio > 2030) {
                System.out.println(" El año no puede ser mayor a 2030. ");
                return null;
            }

            return fecha;

        } catch (ParseException e) {
            System.out.println(" Fecha inválida. Asegúrese de que sea real y siga el formato dd/MM/yyyy. ");
            return null;
        }
    }
    public static int validarOpcionMenu(@NotNull Scanner teclado, int maxOpcion) {
        int seleccion;
        while (true) {
            try {
                String input = teclado.nextLine();
                if (input.matches("\\d+")) {
                    seleccion = Integer.parseInt(input);
                    if (seleccion >= 1 && seleccion <= maxOpcion) {
                        break;
                    } else {
                        System.out.println();
                        System.out.print(" Opción fuera de rango. Por favor, elige un número entre 1 y " + maxOpcion + ".");
                    }
                } else if (input.matches(".*[a-zA-Z].*")) {
                    System.out.println();
                    System.out.print(" Entrada no válida. Por favor, ingrese un número entero, no letras. ");
                } else if (input.contains(".")) {
                    System.out.println();
                    System.out.print(" Entrada no válida. Por favor, ingrese un número entero, no decimales. ");
                } else {
                    System.out.println();
                    System.out.print(" Entrada no válida. Por favor, ingrese un número válido. ");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.print(" Entrada no válida. Por favor, ingrese un número válido. ");
            }
        }
        return seleccion;
    }
    @Contract(pure = true)
    public static boolean validarNombreUsuario(@NotNull String nombre) {
        return nombre.matches("[a-zA-Z]+");
    }
    @Contract(pure = true)
    public static boolean validarContrasena(@NotNull String contrasena) {
        return contrasena.matches("(?=.*[a-zA-Z])(?=.*\\d).{8,}");

    }
    @Contract(pure = true)
    public static boolean validarCorreo(@NotNull String correo) {
        return correo.matches("^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$");
    }
    public static boolean validarNombreMenu(@NotNull String nombre) {
        return !nombre.isEmpty();
    }
    public static boolean validarOpcionSubMenu(int opcion) {
        return opcion >= 1 && opcion <= 5;
    }
    public static int validarOpcionMenu1(@NotNull Scanner entrada, int maxOpcion) {
        int opcion;
        while (true) {
            while (!entrada.hasNextInt()) {
                System.out.print("Debe ingresar un número válido: ");
                entrada.next();
            }
            opcion = entrada.nextInt();
            entrada.nextLine();

            if (opcion < 1 || opcion > maxOpcion) {
                System.out.print("Opción fuera de rango, ingrese nuevamente: ");
            } else {
                break;
            }
        }
        return opcion;
    }
    public static double validarMonto(Scanner entrada) {
        double monto;
        final double MONTO_MAXIMO = 1_000_000;
        while (true) {
            String input = entrada.nextLine().trim();

            try {
                monto = Double.parseDouble(input);

                if (monto <= 0) {
                    System.out.println();
                    System.out.print(" El monto debe ser mayor a cero. Intente nuevamente: ");
                } else if (monto > MONTO_MAXIMO) {
                    System.out.println();
                    System.out.print(" El monto no puede superar " + MONTO_MAXIMO + ". Intente nuevamente: ");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println();
                System.out.print(" Entrada no válida. Por favor, ingrese un número válido. ");
            }
        }

        return monto;
    }
    /*public static Date validarFecha(@NotNull Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date fecha;

        while (true) {
            String fechaStr = scanner.nextLine();
            if (!fechaStr.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                System.out.println(" Formato inválido. Use dd/MM/yyyy. Ej: 31/12/2025. ");
                continue;
            }
            try {
                fecha = dateFormat.parse(fechaStr);
                Date fechaMinima = dateFormat.parse("12/05/2025");
                if (fecha.before(fechaMinima)) {
                    System.out.println("La fecha no puede ser anterior al 12/05/2025. ");
                    continue;
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(fecha);
                int anio = cal.get(Calendar.YEAR);
                if (anio > 2030) {
                    System.out.println(" El año no puede ser mayor a 2030. ");
                    continue;
                }
                break;

            } catch (ParseException e) {
                System.out.println(" Fecha inválida. Asegúrese de que sea real y siga el formato dd/MM/yyyy. ");
            }
        }

        return fecha;
    }*/
    public static boolean verificarContrasena(String contrasenaIngresada, String contrasenaHasheada) {
        String contrasenaHasheadaIngresada = hashearContrasena(contrasenaIngresada);
        return contrasenaHasheadaIngresada.equals(contrasenaHasheada);
    }
    public static String hashearContrasena(@NotNull String contrasena) {
        String hashedContrasena = null;
        try {
            KeySpec spec = new PBEKeySpec(contrasena.toCharArray(), "salt".getBytes(), 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            hashedContrasena = hexString.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("Error al hashear la contraseña: " + e.getMessage());
        }
        return hashedContrasena;
    }
    @Nullable
    public static Double validarPresupuesto(String presupuestoStr) {
        Double presupuesto = null;
        try {
            presupuesto = Double.parseDouble(presupuestoStr);
            if (presupuesto <= 0) {
                System.out.println("El presupuesto debe ser mayor que cero. ");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("El presupuesto debe ser un número y no puede estar vacío. ");
        }
        return presupuesto;
    }
}
