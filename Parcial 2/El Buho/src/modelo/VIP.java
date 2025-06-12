package modelo;

public class VIP extends Servicio {
    private static final double RECARGO_VIP = 0.30;
    private String servicioAdicional1;
    private String servicioAdicional2;
    private String servicioAdicional3;
    private int numMaletas;

    // Constructor
    public VIP(boolean maletaAdicional, String servicioAdicional3) {
        super(maletaAdicional);
        this.servicioAdicional1 = "Televisión"; [cite: 6]
        this.servicioAdicional2 = "Internet"; [cite: 6]
        this.servicioAdicional3 = servicioAdicional3;
        this.numMaletas = 2; // Por defecto, dos maletas incluidas
        if (maletaAdicional) {
            this.numMaletas++; // Si hay maleta adicional, se suma
        }
    }

    public String getServicioAdicional1() {
        return servicioAdicional1;
    }


    public String getServicioAdicional2() {
        return servicioAdicional2;
    }

    public String getServicioAdicional3() {
        return servicioAdicional3;
    }

    public void setServicioAdicional3(String servicioAdicional3) {
        this.servicioAdicional3 = servicioAdicional3;
    }

    public int getNumMaletas() {
        return numMaletas;
    }

    @Override
    public double calcularCostoServicio(double precioBaseRuta) {
        double costoConRecargo = precioBaseRuta * (1 + RECARGO_VIP);
        if (tieneMaletaAdicional()) {
            costoConRecargo += 5.0;
        }
        return costoConRecargo;
    }

    @Override //
    public void describirServicio() {
        System.out.println("--- Servicio VIP ---");
        System.out.println("Recargo del " + (RECARGO_VIP * 100) + "% sobre el pasaje normal."); [cite: 6]
        System.out.println("Servicios incluidos: " + servicioAdicional1 + ", " + servicioAdicional2 + ", " + servicioAdicional3 + "."); [cite: 6]
        System.out.println("Espacio para " + numMaletas + " maleta(s)."); [cite: 7]
        if (tieneMaletaAdicional()) {
            System.out.println("Incluye una maleta adicional (costo extra)."); [cite: 6]
        }
    }
}