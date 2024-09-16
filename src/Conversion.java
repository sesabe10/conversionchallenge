import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conversion {

    private List<RegistroDivisa> historialDivisas;
    private final Map<Integer, String[]> DIVISAS;

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

    RegistroDivisa divisa = historialDivisas.get(historialDivisas.size() - 1);
    return "El valor " + divisa.getMonto() + "[" + divisa.getMoneda().base_code() + "] correspondiente al valor final de =>>> " + divisa.getMoneda().conversion_result() + "[" + divisa.getMoneda().target_code() + "]";

    }
}
