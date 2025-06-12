package modelo;

public class Pasajero extends Persona {
    private String telefono;
    private String email;

    public Pasajero() {
        super();
    }

    public Pasajero(String nombre, String apellido, String cedula, String telefono, String email) {
        super(nombre, apellido, cedula);
        this.telefono = telefono;
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void imprimirInformacion() {
        super.imprimirInformacion();
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
    }
}