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
                case 1: ;
                case 2: {
                    String origen = leerString("Introduce un origen válido: ");
                    System.out.println(gestor.consultaViajes(origen));}
                case 3: ;
                case 4: ;
                case 5: ;
                case 6: ;
            }
        }


    }

    public static String leerString(String mensaje) {
        System.out.println(mensaje);
        return sc.next();

    }

    public static int leerInt(String mensaje) {
        System.out.println(mensaje);
        return sc.nextInt();
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