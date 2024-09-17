import java.time.LocalDateTime;
import java.util.*;

public class Conversion {

    private List<RegistroDivisa> historialDivisas;
    private Map<Integer, String[]> DIVISAS = Map.of();
    RegistroDivisa divisa;

    private final Scanner scanner = new Scanner(System.in);
    private double monto;

    public Conversion() {
        this.historialDivisas = new ArrayList<>();
        DIVISAS = Map.of(
                1, new String[]{"USD", "ARS"},
                2, new String[]{"ARS", "USD"},
                3, new String[]{"USD", "BRL"},
                4, new String[]{"BRL", "USD"},
                5, new String[]{"USD", "COP"},
                6, new String[]{"COP", "USD"}
        );
    }

    public void realizarConversionPersonalizada() {
        try {
            scanner.nextLine();
            System.out.println("Ingresa la divisa base: ");
            String monedaBase = scanner.nextLine();

            System.out.println("Ingresa la divisa objetivo a convertir: ");
            String monedaObjetivo = scanner.nextLine();

            System.out.println("Ingrese el monto: ");
            monto = Double.parseDouble(scanner.nextLine());

            procesarConversion(monedaBase, monedaObjetivo, monto);

        } catch (NumberFormatException e) {
            System.out.println("El monto ingresado no es válido.");
            scanner.nextLine();
        }
    }

    public void realizarConversionAutomatica(int opcion) {
        try {

            System.out.println("Ingrese el monto a convertir: ");
            monto = scanner.nextDouble();

            String[] divisas = getDivisaOrigen(opcion);
            String divisaOrigen = divisas[0];
            String divisaDestino = divisas[1];

            procesarConversion(divisaOrigen, divisaDestino, monto);

        } catch (InputMismatchException e) {
            System.out.println("El monto ingresado no es válido.");
            scanner.nextLine();
        }
    }

    private void procesarConversion(String monedaBase, String monedaObjetivo, double monto) {
        try {
            Consulta consulta = new Consulta(monedaBase, monedaObjetivo, monto);
            Moneda moneda = consulta.consultarDivisa();
            this.setHistorialDivisas(moneda, monto);
            System.out.println(Main.conversion);
        } catch (Exception e) {
            System.out.println("Error al procesar la conversión: " + e.getMessage());
        }
    }

    String[] getDivisaOrigen(int opcion) {
        return DIVISAS.get(opcion);
    }

    List<RegistroDivisa> getHistorialDivisas() {
        return historialDivisas;
    }

    public void setHistorialDivisas(Moneda moneda, double monto) {
        this.historialDivisas.add(new RegistroDivisa(moneda, LocalDateTime.now(), monto));
    }

    @Override
    public String toString() {

        divisa = historialDivisas.get(historialDivisas.size() - 1);
        return "El valor " + divisa.getMonto() + "[" + divisa.getMoneda().base_code() +
                "] correspondiente al valor final de =>>> "
                + divisa.getMoneda().conversion_result() + "[" + divisa.getMoneda().target_code() + "]";
    }
}
