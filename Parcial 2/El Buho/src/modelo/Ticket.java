package modelo;

public class Ticket {
    private String idTicket;
    private Pasajero pasajero;
    private Ruta ruta;
    private Servicio servicio;
    private String fechaViaje;
    private double costoFinal;

    public Ticket() {
    }

    public Ticket(String idTicket, Pasajero pasajero, Ruta ruta, Servicio servicio, String fechaViaje) {
        this.idTicket = idTicket;
        this.pasajero = pasajero;
        this.ruta = ruta;
        this.servicio = servicio;
        this.fechaViaje = fechaViaje;
        this.costoFinal = servicio.calcularCostoServicio(ruta.getPrecioBase());
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
        if (this.servicio != null) {
            this.costoFinal = this.servicio.calcularCostoServicio(this.ruta.getPrecioBase());
        }
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
        if (this.ruta != null) {
            this.costoFinal = this.servicio.calcularCostoServicio(this.ruta.getPrecioBase());
        }
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public void imprimirTicket() {
        System.out.println("\n===== DETALLE DEL TICKET =====");
        System.out.println("ID Ticket: " + idTicket);
        System.out.println("Fecha de Viaje: " + fechaViaje);
        System.out.println("---------------------------------");
        if (pasajero != null) {
            pasajero.imprimirInformacion();
        }
        System.out.println("---------------------------------");
        if (ruta != null) {
            System.out.println("Ruta: " + ruta.getNombreRuta());
            System.out.println("Precio Base Ruta: $" + String.format("%.2f", ruta.getPrecioBase()));
        }
        System.out.println("---------------------------------");
        if (servicio != null) {
            servicio.describirServicio();
        }
        System.out.println("---------------------------------");
        System.out.println("Costo Final del Pasaje: $" + String.format("%.2f", costoFinal));
        System.out.println("================================");
    }
}