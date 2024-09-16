import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Conversion {

    private int opcion = 0;
    private double monto;
    private Moneda moneda;

    private final Scanner scanner = new Scanner(System.in);
    private final Map<Integer, String[]> DIVISAS = Map.of(
            1, new String []{"USD", "ARS"},
            2, new String []{"ARS", "USD"},
            3, new String []{"USD", "BRL"},
            4, new String []{"BRL", "USD"},
            5, new String []{"USD", "COP"},
            6, new String []{"COP", "USD"}
    );

    public void iniciar() {

        do {
            mostrarMenu();
            try {
                opcion = scanner.nextInt();
                if (opcion != 7) {

                    System.out.println("Ingresa el monto a convertir: ");
                    monto = scanner.nextDouble();
                    String [] divisas = getDivisaOrigen(opcion);
                    String divisadeDeOrigen = divisas[0];
                    String divisadeDestino = divisas[1];

                    //Haciendo la consulta
                    Consulta consulta = new Consulta(divisadeDeOrigen, divisadeDestino, monto);
                    moneda = consulta.convertirMoneda();
                    System.out.println(moneda);

                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();

            }
        } while (opcion != 7);

        System.out.println("Hasta luego, gracias por usar nuestro sistema.");
    }

    private void mostrarMenu() {
        System.out.println("""
            **********************************************
            Sea bienvenid@ al Conversor de Moneda
           \s
            1) Dólar =>> Peso argentino
            2) Peso argentino =>> Dólar
            3) Dólar =>> Real brasileño
            4) Real brasileño =>> Dólar
            5) Dólar =>> Peso colombiano
            6) Peso colombiano =>> Dólar
            7) Salir               \s
            Elija una opción valida:
           \s""");
    }

    private String[] getDivisaOrigen(int opcion) {
        return DIVISAS.get(opcion);
    }

    @Override
    public String toString() {
        return "EL valor " + monto + " [" + moneda.base_code() + "] " + " corresponde al valor findal de =>>>"
                                    + moneda.conversion_result() + " [" + moneda.target_code() + "]";
    }
}
