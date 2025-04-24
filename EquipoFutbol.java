import java.util.Scanner;

class Jugador {
    // Atributos
    String nombre;
    String posicion;
    int edad;
    int numeroCamiseta;

    // Constructor de la clase
    public Jugador(String nombreInicial, String posicionInicial, int edadInicial, int numeroInicial) {
        System.out.println("-> Registrando un nuevo jugador...");
        nombre = nombreInicial;
        posicion = posicionInicial;
        edad = edadInicial;
        numeroCamiseta = numeroInicial;
    }

    // Métod para mostrar la información
    void mostrarInformacion() {
        System.out.println("--- Ficha del Jugador ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Posición: " + posicion);
        System.out.println("Edad: " + edad);
        System.out.println("Número de Camiseta: " + numeroCamiseta);
        System.out.println("-------------------------");
    }
}

public class EquipoFutbol {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("=== REGISTRO DE JUGADORES DEL EQUIPO ===");

        System.out.println("\nRegistrando Jugador 1 ...");
        Jugador jugador1 = new Jugador("Cristiano Ronaldo", "Delantero", 40, 7);
        System.out.println("¡Jugador 1 registrado!");

        //Jugador 2 y combinamos con Scanner para que ingresen datos restantes
        System.out.println("\nRegistrando Jugador 2...");
        String nombreJugador2 = "Luka Modric";
        int numeroJugador2 = 10;
        // Atributos que faltan usando Scanner
        System.out.print("Introduce la POSICIÓN del Jugador 2 ("+ nombreJugador2 +"): ");
        String posicionJugador2 = teclado.nextLine();
        System.out.print("Introduce la EDAD del Jugador 2 ("+ nombreJugador2 +"): ");
        int edadJugador2 = teclado.nextInt();
        teclado.nextLine();

        Jugador jugador2 = new Jugador(nombreJugador2, posicionJugador2, edadJugador2, numeroJugador2);
        System.out.println("¡Jugador 2 registrado!");

        // Atributos jugador 3 con Scanner
        System.out.println("\nRegistrando Jugador 3...");
        System.out.print("Introduce el NOMBRE del Jugador 3: ");
        String nombreJugador3 = teclado.nextLine();
        System.out.print("Introduce la POSICIÓN del Jugador 3: ");
        String posicionJugador3 = teclado.nextLine();
        System.out.print("Introduce la EDAD del Jugador 3: ");
        int edadJugador3 = teclado.nextInt();
        System.out.print("Introduce el NÚMERO DE CAMISETA del Jugador 3: ");
        int numeroJugador3 = teclado.nextInt();
        teclado.nextLine();

        Jugador jugador3 = new Jugador(nombreJugador3, posicionJugador3, edadJugador3, numeroJugador3);
        System.out.println("¡Jugador 3 registrado!");

        //Mostrar la información
        System.out.println("\n=== MOSTRANDO INFORMACIÓN DE LOS JUGADORES REGISTRADOS ===");
        jugador1.mostrarInformacion();
        jugador2.mostrarInformacion();
        jugador3.mostrarInformacion();

        teclado.close();
    }
}
