public class EstudianteBecado extends Estudiante {
    private String tipoBeca; // Académica, Económica, etc.

    public EstudianteBecado(String nombre, int edad, String carrera, double promedio, String tipoBeca) {
        super(nombre, edad, carrera, promedio);
        if (tipoBeca == null || tipoBeca.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: El tipo de beca no puede ser vacío.");
        }
        this.tipoBeca = tipoBeca;
    }

    public double aplicarDescuento() {
        double descuento = 0.0;
        if ("Académica".equalsIgnoreCase(tipoBeca) && getPromedio() >= 9.0) {
            descuento = 0.50; // 50%
            System.out.println("Descuento aplicado: 50% (Beca Académica por alto promedio)");
        } else if ("Económica".equalsIgnoreCase(tipoBeca)) {
            descuento = 0.40; // 40%
            System.out.println("Descuento aplicado: 40% (Beca Económica)");
        } else {
            descuento = 0.30; // 30% por defecto para otros tipos
            System.out.println("Descuento aplicado: 30% (Otro tipo de beca)");
        }
        return descuento;
    }

    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("Tipo de Beca: " + tipoBeca);
        System.out.println("Es apto para beca: " + (esAptoBeca() ? "Sí" : "No"));
    }

    public String getTipoBeca() {
        return tipoBeca;
    }
}