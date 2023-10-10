import org.json.JSONArray;

public class Main {
    public static void main(String[] args) {
        GestorViajes gestorViajes = new GestorViajes();

        gestorViajes.generaDatos();

        String a = gestorViajes.mapa.keySet().stream().findFirst().orElse("");

        gestorViajes.anulaReserva(a,"");
    }
}
