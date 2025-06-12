import java.util.Scanner;

class Participante {
    private String nombre;
    private String apellido;
    private int edad;
    private String telefono;
    private String correo;
    private String nacionalidad;
    private boolean certificadoMedico;
    private boolean terminos;

    public Participante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Participante(String nombre, int edad, String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    public Participante(String nombre, String apellido, int edad, String telefono, String correo, String nacionalidad, boolean certificadoMedico, boolean terminos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.nacionalidad = nacionalidad;
        this.certificadoMedico = certificadoMedico;
        this.terminos = terminos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public boolean getCertificadoMedico() {
        return certificadoMedico;
    }

    public boolean getTerminos() {
        return terminos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCertificadoMedico(Scanner entrada) {
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("¿Tiene certificado médico (si/no)?");
                String respuesta = entrada.nextLine();
                respuesta = respuesta.toLowerCase();
                if (respuesta.equals("si")) {
                    this.certificadoMedico = true;
                    entradaValida = true;
                } else if (respuesta.equals("no")) {
                    this.certificadoMedico = false;
                    entradaValida = true;
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese 'si' o 'no'.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error al leer la entrada. Por favor, intente de nuevo.");
                entrada.nextLine();
            }
        }
    }

    public void setTerminos(Scanner entrada) {
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("¿Acepta los términos (si/no)?");
                String respuesta = entrada.nextLine();
                respuesta = respuesta.toLowerCase();
                if (respuesta.equals("si")) {
                    this.terminos = true;
                    entradaValida = true;
                } else if (respuesta.equals("no")) {
                    this.terminos = false;
                    entradaValida = true;
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese 'si' o 'no'.");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error al leer la entrada. Por favor, intente de nuevo.");
                entrada.nextLine();
            }
        }
    }

    public void setNacionalidad(String nacionalidad) {
        System.out.println("Participante actualizado.");
        this.nacionalidad = nacionalidad;
    }

    public boolean cumpleRequisitos(int edad, boolean certificadoMedico, boolean terminos) {
        boolean cumple;
        if (certificadoMedico && terminos) {
            if (edad >= 18 && edad <= 50) {
                cumple = true;
            }else{
                System.out.println("No cumple requisito de edad.");
                cumple = false;
            }
        }else {
            cumple = false;
        }
        return cumple;
    }

    public void mostrarDatos(boolean cumple){
        if (cumple) {
            System.out.println("\tMOSTRANDO DATOS DE: " + nombre);
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellido: " + apellido);
            System.out.println("Edad: " + edad);
            System.out.println("Telefono: " + telefono);
            System.out.println("Correo: " + correo);
            System.out.println("Nacionalidad: " + nacionalidad);
            System.out.println("Certificado médico: " + (certificadoMedico ? "Sí" : "No"));
            System.out.println("Terminos: " + (terminos? "Sí" : "No"));
        }else{
            System.out.println(this.nombre+" no cumple requisitos, no se mostrará la información.");
        }
    }

}
public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Participante[] participantes = new Participante[3];
        String nombre[]={"nombre1","nombre2","nombre3"};
        String apellido[]={"apellido1","apellido2","apellido3"};
        int edad[]={0,0,0};
        String telefono[]={"telefono1","telefono2","telefono3"};
        String correo[]={"correo1","correo2","correo3"};
        String nacionalidad[]={"nacionalidad1","nacionalidad2","nacionalidad3"};

        for (int i=0; i<3; i++) {
            System.out.println("Ingrese el nombre del participante #"+(i+1)+": ");
            nombre[i]=teclado.nextLine();
            System.out.println("Ingrese el apellido del participante #"+(i+1)+": ");
            apellido[i]=teclado.nextLine();
            System.out.println("Ingrese la edad del participante #"+(i+1)+": ");
            edad[i]=teclado.nextInt();
            teclado.nextLine();
            System.out.println("Ingrese el telefono del participante #"+(i+1)+": ");
            telefono[i]=teclado.nextLine();
            System.out.println("Ingrese el correo del participante #"+(i+1)+": ");
            correo[i]=teclado.nextLine();
            System.out.println("Ingrese la nacionalidad del participante #"+(i+1)+": ");
            nacionalidad[i]=teclado.nextLine();


            participantes[i]=new Participante(nombre[i],apellido[i]);

            participantes[i].setEdad(edad[i]);
            participantes[i].setTelefono(telefono[i]);
            participantes[i].setCorreo(correo[i]);
            participantes[i].setNacionalidad(nacionalidad[i]);
            participantes[i].setCertificadoMedico(teclado);
            participantes[i].setTerminos(teclado);
        }

        for (int i = 0; i < 3; i++) {
            participantes[i].mostrarDatos(participantes[i].cumpleRequisitos(participantes[i].getEdad(), participantes[i].getCertificadoMedico(), participantes[i].getTerminos()));
            System.out.println("--------------------");
        }
        teclado.close();
    }
}