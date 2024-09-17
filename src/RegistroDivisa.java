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
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
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
        return "Moneda: " + moneda.base_code()+ " " + monto + " ==> " + moneda.target_code()+ " " + moneda.conversion_result() + " | " +
                "Fecha: " + getFecha() + "\n";
    }
}
