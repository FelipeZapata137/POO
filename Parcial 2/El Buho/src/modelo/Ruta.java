package modelo;

import java.util.HashMap;
import java.util.Map;

public class Ruta {
    private static final Map<String, Double> PRECIOS_RUTAS_BASE = new HashMap<>();

    static {
        PRECIOS_RUTAS_BASE.put("Quito-Guayaquil", 20.00);
        PRECIOS_RUTAS_BASE.put("Quito-Puyo", 15.00);
        PRECIOS_RUTAS_BASE.put("Quito-Tulcán", 17.50);
        PRECIOS_RUTAS_BASE.put("Quito-Riobamba", 17.50);

        PRECIOS_RUTAS_BASE.put("Guayaquil-Quito", 20.00);
        PRECIOS_RUTAS_BASE.put("Puyo-Quito", 15.00);
        PRECIOS_RUTAS_BASE.put("Tulcán-Quito", 17.50);
        PRECIOS_RUTAS_BASE.put("Riobamba-Quito", 17.50);
    }

    private String nombreRuta;
    private double precioBase;

    public Ruta() {
    }

    public Ruta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
        this.precioBase = PRECIOS_RUTAS_BASE.getOrDefault(nombreRuta, 0.0);
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
        this.precioBase = PRECIOS_RUTAS_BASE.getOrDefault(nombreRuta, 0.0);
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public static void mostrarRutasDisponibles() {
        System.out.println("\n--- Rutas Disponibles y Precios Base ---");
        for (Map.Entry<String, Double> entrada : PRECIOS_RUTAS_BASE.entrySet()) {
            System.out.println("Ruta: " + entrada.getKey() + " | Precio: $" + String.format("%.2f", entrada.getValue()));
        }
        System.out.println("----------------------------------------");
    }

    public static boolean existeRuta(String nombreRuta) {
        return PRECIOS_RUTAS_BASE.containsKey(nombreRuta);
    }
}