public class Estudiante extends Persona {
    private String carrera;
    private double promedio;

    public Estudiante(String nombre, int edad, String carrera, double promedio) {
        super(nombre, edad);
        if (carrera == null || carrera.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: La carrera no puede ser vacía.");
        }
        if (promedio < 0 || promedio > 10) {
            throw new IllegalArgumentException("Error: El promedio debe estar entre 0 y 10.");
        }
        this.carrera = carrera;
        this.promedio = promedio;
    }

    public boolean esAptoBeca() {
        return promedio >= 8.5;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("--- Datos del Estudiante ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Carrera: " + carrera);
        System.out.println("Promedio: " + String.format("%.1f", promedio));
    }

    public void inscribir(String semestre) {
        System.out.println(nombre + " inscrito/a al " + semestre + " semestre.");
    }

    public void inscribir(String semestre, String modalidad) {
        System.out.println(nombre + " inscrito/a al " + semestre + " semestre bajo modalidad " + modalidad + ".");
    }

    public String getCarrera() {
        return carrera;
    }

    public double getPromedio() {
        return promedio;
    }
}