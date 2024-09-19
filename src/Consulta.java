import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {

    private String APIKEY = "54885fca1e4eeafa7a508292";
    private URI uri;
    private String base;
    private String objetivo;
    private double monto;
    private Gson gson = new Gson();

    //Inicializando atributos de la clase
    public Consulta(String base, String objetivo, double monto) {
        this.base = base;
        this.objetivo = objetivo;
        this.monto = monto;
    }

    public Moneda consultarDivisa()  {
        try {
            uri = new URI("https://v6.exchangerate-api.com/v6/" + APIKEY + "/pair/" + base + "/" + objetivo + "/" + monto);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Moneda.class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
