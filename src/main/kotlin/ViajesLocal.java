import java.util.Scanner;

public class ViajesLocal {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestorViajes gestor = new GestorViajes();

        String codcli = leerString("Cuál es tu código de cliente?");
        System.out.println();

        int option = 0;

        while (option != 1) {
            mostrarMenu();

            option = leerInt("Introduce una opción válida.");

            switch (option) {
                case 1:
                    gestor.guardaDatos();
                    break;
                case 2: {
                    String origen = leerString("Introduce un origen válido: ");
                    System.out.println(gestor.consultaViajes(origen));
                }break;
                case 3: {
                    String codviaje = leerString("Introduce un código de viaje válido: ");
                    System.out.println(gestor.reservaViaje(codviaje, codcli));
                }break;
                case 4: {
                    String codviaje = leerString("Introduce un código de viaje válido: ");
                    System.out.println(gestor.anulaReserva(codviaje, codcli));
                }break;
                case 5: {
                    generaViaje(gestor, codcli);
                }break;
                case 6: {
                    String codviaje = leerString("Introduce un código de viaje válido: ");
                    System.out.println(gestor.borraViaje(codviaje, codcli));
                }break;
            }
        }

    }

    public static void generaViaje(GestorViajes gestor, String codcli) {
        String origen = leerString("Introduce el origen del viaje:");
        String destino = leerString("Introduce el destino del viaje:");
        String fecha = leerString("Introduce una fecha válida para el viaje:");
        long precio = leerLong("Introduce un precio válido");
        long numPlazas = leerLong("Introduce un número de plazas válidas.");

        try {
            System.out.println(gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numPlazas));
        } catch (Exception e) {
            System.out.println("Fecha no válida bobo.");
        }
        ;

    }

    public static String leerString(String mensaje) {
        System.out.println(mensaje);
        return sc.next();

    }

    public static int leerInt(String mensaje) {
        System.out.println(mensaje);
        return sc.nextInt();
    }

    public static long leerLong(String mensaje) {
        System.out.println(mensaje);
        return sc.nextLong();
    }

    public static void mostrarMenu() {
        System.out.println("1.- Salir del programa guardando los datos en el fichero.");
        System.out.println("2.- Mostrar los datos de los viajes con un origen dado.");
        System.out.println("3.- Reservar un viaje.");
        System.out.println("4.- Anular una reserva.");
        System.out.println("5.- Ofertar un nuevo viaje.");
        System.out.println("6.- Borrar un viaje ofertado.");
        System.out.println();
    }

    public static void limpiarPantalla() {
        System.out.println("Presiona enter para continuar...");
        sc.next();

        for(int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}