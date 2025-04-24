import java.util.Scanner;

class Empleado{
    // Atributos
    String nombre;
    String cargo;
    int salario;
    String fechaIngreso;

    // Constructor
    public Empleado(String nombre, String cargo, int salario, String fechaIngreso){
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }
    // XD
    //Métod para mostrar información
    void mostrarInformacion() {
        System.out.println("--- Ficha de empleado ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Cargo: " + cargo);
        System.out.println("Salario: " + salario);
        System.out.println("Fecha de Ingreso: " + fechaIngreso);
        System.out.println("-------------------------");
    }
}

public class Empresa {
    public static void main(String[] args) {
        //Creando el Scanner
        Scanner teclado = new Scanner(System.in);
        System.out.println("=== REGISTRO DE EMPLEADOS DE LA EMPRESA ===");
        //Registrando empleado #1
        System.out.println("\nRegistrando primer empleado...");
        System.out.println("Ingrese el nombre del primer empleado: ");
        String nombreUno = teclado.nextLine();
        System.out.println("Ingrese el cargo de "+nombreUno+": ");
        String cargoUno = teclado.nextLine();
        System.out.println("Ingrese el salario de "+nombreUno+": ");
        int salarioUno = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Ingrese la fecha de ingreso de "+nombreUno+" (dd/mm/yyyy): ");
        String fechaIngresoUno = teclado.nextLine();

        //Creando el objeto empleado 1
        Empleado empleado1 = new Empleado(nombreUno, cargoUno, salarioUno, fechaIngresoUno);
        System.out.println(nombreUno+" ha sido registrado.");

        // Registrando segundo empleado
        System.out.println("\nRegistrando segundo empleado...");
        System.out.println("Ingrese el nombre del segundo empleado: ");
        String nombreDos = teclado.nextLine();
        System.out.println("Ingrese el cargo de "+nombreDos+": ");
        String cargoDos = teclado.nextLine();
        System.out.println("Ingrese el salario de "+nombreDos+": ");
        int salarioDos = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Ingrese el fecha de ingreso de "+nombreDos+" (dd/mm/yyyy): ");
        String fechaIngresoDos = teclado.nextLine();
        // Creando segundo objeto empleado dos
        Empleado empleado2 = new Empleado(nombreDos, cargoDos, salarioDos, fechaIngresoDos);
        System.out.println(nombreDos+" ha sido registrado.");

        //Registrando empleado 3
        System.out.println("\nRegistrando tercer empleado...");
        System.out.println("Ingrese el nombre del tercer empleado: ");
        String nombreTres = teclado.nextLine();
        System.out.println("Ingrese el cargo de "+nombreTres+": ");
        String cargoTres = teclado.nextLine();
        System.out.println("Ingrese el salario de "+nombreTres+": ");
        int salarioTres = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Ingrese el fecha de ingreso de "+nombreTres+" (dd/mm/yyyy): ");
        String fechaIngresoTres = teclado.nextLine();
        //Creando objeto 3 empleado tres
        Empleado empleado3 = new Empleado(nombreTres, cargoTres, salarioTres, fechaIngresoTres);
        System.out.println(nombreTres+" ha sido registrado.");

        System.out.println("\n=== MOSTRANDO INFORMACIÓN DE LOS EMPLEADOS REGISTRADOS ===");
        empleado1.mostrarInformacion();
        empleado2.mostrarInformacion();
        empleado3.mostrarInformacion();
        System.out.println("\nRegistro finalizado.");

        teclado.close();
    }
}
