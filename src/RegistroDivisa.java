import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroDivisa {

    private final Moneda moneda;
    private final LocalDateTime fecha;
    private final DateTimeFormatter formatter;
    private final double monto;

    public RegistroDivisa(Moneda moneda, LocalDateTime fecha, double monto) {
        this.moneda = moneda;
        this.fecha = fecha;
        this.monto = monto;
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha(){
        return fecha.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("Conversión de %s a %s:\n" +
                        "  Monto original: %s %s\n" +
                        "  Monto convertido: %s %s\n" +
                        "  Fecha: %s\n",
                moneda.base_code(), moneda.target_code(),
                monto, moneda.base_code(),
                moneda.conversion_result(), moneda.target_code(),
                getFecha());
    }
}
