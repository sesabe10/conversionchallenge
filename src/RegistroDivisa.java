import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegistroDivisa {

    private Moneda moneda;
    private LocalDateTime fecha;
    private DateTimeFormatter formatter;
    private double monto;

    public  RegistroDivisa(){}

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

    @Override
    public String toString() {
        return "Moneda: " + moneda.base_code()+ " " + monto + " ==> " + moneda.target_code()+ " " + moneda.conversion_result() + " | " +
                "Fecha: " + fecha.format(formatter) + "\n";
    }
}
