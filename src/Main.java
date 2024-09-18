import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Conversion conversion = new Conversion();
        int opcion;
        String menu = """
                 $══════════════════════════════════════════════$
                 ║ Sea bienvenid@ al Conversor de Moneda        ║ \s
                 ║                                              ║
                 ║ 1) Dólar =>> Peso argentino                  ║
                 ║ 2) Peso argentino =>> Dólar                  ║
                 ║ 3) Dólar =>> Real brasileño                  ║
                 ║ 4) Real brasileño =>> Dólar                  ║
                 ║ 5) Dólar =>> Peso colombiano                 ║
                 ║ 6) Peso colombiano =>> Dólar                 ║
                 ║ 7) Otras Divisas                             ║
                 ║ 8) Salir                                     ║
                 ║ Elija una opción valida:                     ║ \s
                 $══════════════════════════════════════════════$
                \s""";
        
        while (true) {

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();

                if (opcion == 8) break;
                if (opcion < 1 || opcion >= 9) {
                    System.out.println("Opción inválida. Por favor, ingrese un número entre 1 y 8.");
                    continue;
                }

                if (opcion == 7) {
                    conversion.realizarConversionPersonalizada();
                } else conversion.realizarConversionAutomatica(opcion);

            } catch (InputMismatchException e) {
                System.out.println("La opción ingresada no es válida. Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
        System.out.println("Total Conversiones: " + conversion.getHistorialDivisas().size() + "\n");
        List<String> historial = conversion.getHistorialDivisas();
        for (String hist : historial) {
            System.out.println(hist);
        }
    }
}
