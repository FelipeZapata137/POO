import modelo.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int eleccionPrincipal;
        int salida = 0;

        System.out.println("\t\tAGENCIA DE TRANSPORTE 'EL BÚHO'\n");

        Ruta.mostrarRutasDisponibles();

        System.out.println("\n--- Instancias con valores quemados y seteo ---");
        Pasajero pasajero1 = new Pasajero();
        pasajero1.setNombre("Carlos");
        pasajero1.setApellido("Perez");
        pasajero1.setCedula("1735671432");
        pasajero1.imprimirInformacion();

        Ruta rutaNula = new Ruta();
        rutaNula.setNombreRuta("Quito-Puyo");
        System.out.println("Ruta Nula (set): " + rutaNula.getNombreRuta() + ", Precio: $" + rutaNula.getPrecioBase());

        Normal servicioNormalNulo = new Normal(false, "Ventana");
        servicioNormalNulo.setMaletaAdicional(true);
        servicioNormalNulo.describirServicio();

        Ticket ticketNulo = new Ticket();
        ticketNulo.setIdTicket("T000N");
        ticketNulo.setPasajero(pasajero1);
        ticketNulo.setRuta(new Ruta("Quito-Guayaquil"));
        ticketNulo.setServicio(new Normal(false, "Pasillo"));
        ticketNulo.setFechaViaje("15/06/2025");
        ticketNulo.imprimirTicket();

        System.out.println("\n--- Instancias con valores iniciales ---");
        Pasajero pasajero2 = new Pasajero("Ana", "Gómez", "0987654321", "0991234567", "ana.gomez@gmail.com");
        pasajero2.imprimirInformacion();

        Pasajero pasajero3 = new Pasajero("Luis", "Diaz", "1122334455", "0987654321", "luis.diaz@gmail.com");
        Ruta ruta1 = new Ruta("Quito-Guayaquil");
        Normal servicio1 = new Normal(false, "Pasillo");
        Ticket ticket1 = new Ticket("T001", pasajero2, ruta1, servicio1, "12/07/2025");
        ticket1.imprimirTicket();

        Pasajero pasajero4 = new Pasajero("Sofia", "Ramirez", "2233445566", "0965432109", "sofia.r@gmail.com");
        Ruta ruta2 = new Ruta("Quito-Puyo");
        VIP servicio2 = new VIP(true, "Snacks");
        Ticket ticket2 = new Ticket("T002", pasajero3, ruta2, servicio2, "20/07/2025");
        ticket2.imprimirTicket();

        Pasajero pasajero5 = new Pasajero("Pedro", "Lopez", "3344556677", "0978901234", "pedro.l@gmail.com");
        Ruta ruta3 = new Ruta("Quito-Tulcán");
        Normal servicio3 = new Normal(true, "Ventana");
        Ticket ticket3 = new Ticket("T003", pasajero4, ruta3, servicio3, "25/07/2025");
        ticket3.imprimirTicket();

        Ruta ruta4 = new Ruta("Quito-Riobamba");
        VIP servicio4 = new VIP(false, "Bebidas");
        Ticket ticket4 = new Ticket("T004", pasajero5, ruta4, servicio4, "01/08/2025");
        ticket4.imprimirTicket();

        Pasajero pasajero6 = new Pasajero("Marta", "Suarez", "4455667788", "0954321098", "marta.s@gmail.com");
        Ruta ruta5 = new Ruta("Puyo-Quito");
        Normal servicio5 = new Normal(false, "Pasillo");
        Ticket ticket5 = new Ticket("T005", pasajero6, ruta5, servicio5, "05/08/2025");
        ticket5.imprimirTicket();

        while (salida != 1) {
            System.out.println("\n\tMENÚ PRINCIPAL:");
            System.out.println("1. Comprar Ticket");
            System.out.println("2. Ver Rutas Disponibles");
            System.out.println("3. Salir");
            System.out.print("Ingrese su elección: ");

            if (teclado.hasNextInt()) {
                eleccionPrincipal = teclado.nextInt();
                teclado.nextLine();

                switch (eleccionPrincipal) {
                    case 1:
                        System.out.println("\n--- Proceso de Compra de Ticket ---");

                        System.out.print("Ingrese nombre del pasajero: ");
                        String nombre = teclado.nextLine();
                        System.out.print("Ingrese apellido del pasajero: ");
                        String apellido = teclado.nextLine();
                        System.out.print("Ingrese cédula del pasajero: ");
                        String cedula = teclado.nextLine();
                        System.out.print("Ingrese teléfono del pasajero: ");
                        String telefono = teclado.nextLine();
                        System.out.print("Ingrese email del pasajero: ");
                        String email = teclado.nextLine();
                        Pasajero nuevoPasajero = new Pasajero(nombre, apellido, cedula, telefono, email);

                        String nombreRutaElegida;
                        Ruta rutaElegida = null;
                        boolean rutaValida = false;
                        while (!rutaValida) {
                            Ruta.mostrarRutasDisponibles();
                            System.out.print("Ingrese el nombre EXACTO de la ruta deseada: ");
                            nombreRutaElegida = teclado.nextLine();
                            if (Ruta.existeRuta(nombreRutaElegida)) {
                                rutaElegida = new Ruta(nombreRutaElegida);
                                rutaValida = true;
                            } else {
                                System.out.println("Ruta no válida o mal escrita. Por favor, intente de nuevo.");
                            }
                        }

                        int tipoServicioEleccion;
                        Servicio servicioElegido = null;
                        boolean servicioValido = false;
                        while (!servicioValido) {
                            System.out.println("\nSeleccione tipo de servicio:");
                            System.out.println("1. Normal");
                            System.out.println("2. VIP");
                            System.out.print("Elección: ");
                            if (teclado.hasNextInt()) {
                                tipoServicioEleccion = teclado.nextInt();
                                teclado.nextLine();

                                boolean tieneMaletaAdicional = false;
                                System.out.print("¿Desea una maleta adicional? (si/no): ");
                                String respuestaMaleta = teclado.nextLine().toLowerCase();
                                if (respuestaMaleta.equals("si")) {
                                    tieneMaletaAdicional = true;
                                }

                                if (tipoServicioEleccion == 1) {
                                    int eleccionAsiento;
                                    String posicionAsientoElegida = null;
                                    boolean asientoValido = false;
                                    while(!asientoValido) {
                                        System.out.println("\n--- Posiciones de Asiento Disponibles (Servicio Normal) ---");
                                        for (int i = 0; i < Normal.getNumeroOpcionesAsiento(); i++) {
                                            System.out.println((i + 1) + ". " + Normal.getPosicionAsientoByIndex(i));
                                        }
                                        System.out.print("Ingrese el número de su elección para la posición del asiento: ");
                                        if (teclado.hasNextInt()) {
                                            eleccionAsiento = teclado.nextInt();
                                            teclado.nextLine();
                                            if (eleccionAsiento >= 1 && eleccionAsiento <= Normal.getNumeroOpcionesAsiento()) {
                                                posicionAsientoElegida = Normal.getPosicionAsientoByIndex(eleccionAsiento - 1);
                                                servicioElegido = new Normal(tieneMaletaAdicional, posicionAsientoElegida);
                                                asientoValido = true;
                                                servicioValido = true;
                                            } else {
                                                System.out.println("Opción de asiento inválida. Intente de nuevo.");
                                            }
                                        } else {
                                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                            teclado.nextLine();
                                        }
                                    }
                                } else if (tipoServicioEleccion == 2) {
                                    System.out.println("\n--- Servicios Adicionales VIP ---");
                                    System.out.println("1. WIFI Premium");
                                    System.out.println("2. Comida Especial");
                                    System.out.println("3. Audífonos de Calidad");
                                    System.out.print("Ingrese el número del servicio VIP adicional que desea (o presione Enter para ninguno): ");
                                    String eleccionServicioVIPStr = teclado.nextLine();
                                    String servicioExtraVIP = "";
                                    try {
                                        int eleccionServicioVIP = Integer.parseInt(eleccionServicioVIPStr);
                                        switch (eleccionServicioVIP) {
                                            case 1:
                                                servicioExtraVIP = "WIFI Premium";
                                                break;
                                            case 2:
                                                servicioExtraVIP = "Comida Especial";
                                                break;
                                            case 3:
                                                servicioExtraVIP = "Audífonos de Calidad";
                                                break;
                                            default:
                                                System.out.println("Opción de servicio VIP inválida. No se añadirá servicio extra.");
                                                servicioExtraVIP = "Ninguno";
                                                break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("No se seleccionó un servicio VIP adicional (o entrada inválida).");
                                        servicioExtraVIP = "Ninguno";
                                    }

                                    servicioElegido = new VIP(tieneMaletaAdicional, servicioExtraVIP);
                                    servicioValido = true;
                                } else {
                                    System.out.println("Tipo de servicio inválido. Por favor, elija 1 o 2.");
                                }
                            } else {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                teclado.nextLine();
                            }
                        }

                        System.out.print("\nIngrese la fecha de viaje (DD/MM/AAAA): ");
                        String fechaViaje = teclado.nextLine();

                        String idNuevoTicket = "T" + (int)(Math.random() * 90000) + 10000;

                        Ticket nuevoTicket = new Ticket(idNuevoTicket, nuevoPasajero, rutaElegida, servicioElegido, fechaViaje);
                        nuevoTicket.imprimirTicket();
                        break;
                    case 2:
                        Ruta.mostrarRutasDisponibles();
                        break;
                    case 3:
                        System.out.println("Saliendo de la aplicación...");
                        salida = 1;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                teclado.nextLine();
            }
        }
        teclado.close();
        System.out.println("Gracias por usar 'EL BÚHO'. ¡Buen viaje!");
    }
}