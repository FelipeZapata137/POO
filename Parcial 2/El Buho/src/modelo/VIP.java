package modelo;

public class VIP extends Servicio {
    private static final double RECARGO_VIP = 0.30;
    private String servicioAdicional1;
    private String servicioAdicional2;
    private String servicioAdicional3;
    private int numMaletasIncluidas;

    private static final double COSTO_MALETA_ADICIONAL = 5.0;

    public VIP(boolean maletaAdicional, String servicioAdicional3) {
        super(maletaAdicional);

        this.servicioAdicional1 = "Televisión";
        this.servicioAdicional2 = "Internet";
        this.servicioAdicional3 = servicioAdicional3;
        this.numMaletasIncluidas = 2;
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
        return numMaletasIncluidas + (tieneMaletaAdicional() ? 1 : 0);
    }

    @Override
    public double calcularCostoServicio(double precioBaseRuta) {
        double costoConRecargo = precioBaseRuta * (1 + RECARGO_VIP);
        if (tieneMaletaAdicional()) {
            costoConRecargo += COSTO_MALETA_ADICIONAL;
        }
        return costoConRecargo;
    }

    @Override
    public void describirServicio() {
        System.out.println("--- Servicio VIP ---");
        System.out.println("Recargo del " + (RECARGO_VIP * 100) + "% sobre el pasaje normal.");
        System.out.println("Servicios incluidos: " + servicioAdicional1 + ", " + servicioAdicional2 + ", " + servicioAdicional3 + ".");
        System.out.println("Maletas permitidas: " + getNumMaletas() + " (2 incluidas, " + (tieneMaletaAdicional() ? "1 adicional con costo extra)" : "sin adicional)"));
    }
}