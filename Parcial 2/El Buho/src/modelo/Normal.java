package modelo;

import java.util.Arrays;
import java.util.List;

public class Normal extends Servicio {
    private String posicionAsiento;
    private int numMaletasIncluidas;

    private static final List<String> POSICIONES_ASIENTO = Arrays.asList("Ventana", "Pasillo", "Atrás", "Adelante");
    private static final double COSTO_MALETA_ADICIONAL = 5.0;

    public Normal(boolean maletaAdicional, String posicionAsiento) {
        super(maletaAdicional);
        this.numMaletasIncluidas = 1;
        setPosicionAsiento(posicionAsiento);
    }

    public String getPosicionAsiento() {
        return posicionAsiento;
    }

    public void setPosicionAsiento(String posicionAsiento) {
        if (POSICIONES_ASIENTO.contains(posicionAsiento)) {
            this.posicionAsiento = posicionAsiento;
        } else {
            System.out.println("ADVERTENCIA: Posición de asiento '" + posicionAsiento + "' inválida. Estableciendo por defecto a 'Ventana'.");
            this.posicionAsiento = "Ventana";
        }
    }

    public int getNumMaletas() {
        return numMaletasIncluidas + (tieneMaletaAdicional() ? 1 : 0);
    }

    @Override
    public double calcularCostoServicio(double precioBaseRuta) {
        double costoTotal = precioBaseRuta;
        if (tieneMaletaAdicional()) {
            costoTotal += COSTO_MALETA_ADICIONAL;
        }
        return costoTotal;
    }

    @Override
    public void describirServicio() {
        System.out.println("--- Servicio Normal ---");
        System.out.println("Tipo de Asiento: " + posicionAsiento);
        System.out.println("Maletas permitidas: " + getNumMaletas() + " (1 incluida, " + (tieneMaletaAdicional() ? "1 adicional con costo extra)" : "sin adicional)")); [cite: 5, 6]
    }

    public static String getPosicionAsientoByIndex(int index) {
        if (index >= 0 && index < POSICIONES_ASIENTO.size()) {
            return POSICIONES_ASIENTO.get(index);
        }
        return null;
    }

    public static int getNumeroOpcionesAsiento() {
        return POSICIONES_ASIENTO.size();
    }
}