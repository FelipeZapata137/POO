package Modelo;
import Controlador.*;
import Main_Vista.*;

public class Persona {
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected int edad;

    public Persona(String nombre, String apellido, String cedula, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;
    }

    public void mostrarInfo(){
        System.out.println("Nombre: "+nombre);
        System.out.println("Apellido: "+apellido);
        System.out.println("Cedula: "+cedula);
        System.out.println("Edad: "+edad);
    }
}
