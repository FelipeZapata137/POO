import java.util.ArrayList;
import java.util.List;

class Ejemplo {

    private String nombreIndividual;
    private static List<String> nombres = new ArrayList<>();

    public Ejemplo(String nombreIndividual) {
        this.nombreIndividual = nombreIndividual;
        agregarNombre(nombreIndividual);
    }

    public String getNombreIndividual() {
        return nombreIndividual;
    }

    public void setNombreIndividual(String nombreIndividual) {
        this.nombreIndividual = nombreIndividual;
    }

    private static void agregarNombre(String nombre) {
        nombres.add(nombre);
    }

    public static List<String> getNombres() {
        return new ArrayList<>(nombres);
    }
}

public class Main {
    public static void main(String[] args) {

        System.out.println("\tAGENDA DE NOMBRES: ");

        Ejemplo ejemplo1 = new Ejemplo("Felipe");
        Ejemplo ejemplo2 = new Ejemplo("Isabella");
        Ejemplo ejemplo3 = new Ejemplo("Eva");

        System.out.println("Su lista es:");
        List<String> listaNombres = Ejemplo.getNombres();
        for (int i = 0; i < listaNombres.size(); i++) {
            System.out.println((i + 1) + ". " + listaNombres.get(i));
        }

        System.out.println("\tCAMBIANDO NOMBRES...");
        ejemplo1.setNombreIndividual("Feli");
        ejemplo2.setNombreIndividual("Bella");
        ejemplo3.setNombreIndividual("Evie");
        System.out.println("Nuevo nombre 1. " + ejemplo1.getNombreIndividual());
        System.out.println("Nuevo nombre 2. " + ejemplo2.getNombreIndividual());
        System.out.println("Nuevo nombre 3. " + ejemplo3.getNombreIndividual());

        System.out.println("\tFin");
    }
}