public abstract class Persona {
    protected String nombre;
    protected int edad;

    public Persona(String nombre, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El nombre no puede ser vacío.");
        }
        if (edad < 18) {
            throw new IllegalArgumentException("Error: La edad debe ser 18 o mayor.");
        }
        this.nombre = nombre;
        this.edad = edad;
    }

    public abstract void mostrarDatos();

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}