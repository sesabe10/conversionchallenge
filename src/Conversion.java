import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Conversion {

    private final List<RegistroDivisa> historialDivisas;
    private final Map<Integer, String[]> DIVISAS;
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

            System.out.println("Ingresa la divisa base: ");
            String monedaBase = scanner.nextLine();

            System.out.println("Ingresa la divisa objetivo a convertir: ");
            String monedaObjetivo = scanner.nextLine();

            System.out.println("Ingrese el monto: ");
            monto = scanner.nextDouble();

            if (validarMonto(monto)){
                procesarConversion(monedaBase, monedaObjetivo, monto);
            }else{
                System.out.println("Debe ingresar un monto valido");
            }
            scanner.nextLine();

        } catch (NumberFormatException | InputMismatchException | IllegalStateException e) {
            System.out.println("Error ha ingresado un caracter no valido");
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

            if (validarMonto(monto)){
                 procesarConversion(divisaOrigen, divisaDestino, monto);
            }else{
                System.out.println("Debe ingresar un monto valido");
            }

        } catch (InputMismatchException e) {
            System.out.println("El monto ingresado no es válido.");
            scanner.nextLine();
        }
    }

    private void procesarConversion(String monedaBase, String monedaObjetivo, double monto) {
        try {

            System.out.println("Procesando conversion...");
            Consulta consulta = new Consulta(monedaBase, monedaObjetivo, monto);
            Moneda moneda = consulta.consultarDivisa();
            this.setHistorialDivisas(moneda, monto);
            System.out.println(this);

        } catch (Exception e) {
            System.out.println("Error al procesar la conversión: " + e.getMessage());
        }
    }

    private boolean validarMonto(double monto){
        return !(monto <= 0);
    }

    String[] getDivisaOrigen(int opcion) {
        return DIVISAS.get(opcion);
    }

    List<String> getHistorialDivisas() {
        List<String> historialStrings = new ArrayList<>();
        for (RegistroDivisa registroDivisa : historialDivisas) {
            historialStrings.add(registroDivisa.toString());
        }
        return historialStrings;
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
