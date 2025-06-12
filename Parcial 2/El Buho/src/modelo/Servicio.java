package modelo;


public abstract class Servicio {

    protected boolean maletaAdicional;

    public Servicio(boolean maletaAdicional) {
        this.maletaAdicional = maletaAdicional;
    }

    public boolean tieneMaletaAdicional() {
        return maletaAdicional;
    }

    public void setMaletaAdicional(boolean maletaAdicional) {
        this.maletaAdicional = maletaAdicional;
    }

    public abstract double calcularCostoServicio(double precioBaseRuta);

    public abstract void describirServicio();
}