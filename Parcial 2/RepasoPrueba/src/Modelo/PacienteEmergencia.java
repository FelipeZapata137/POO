package Modelo;

public class PacienteEmergencia extends Paciente{
    private String prioridad;

    public PacienteEmergencia(String nombre, String apellido, String cedula, int edad, String fechaNacimiento, String motivoConsulta, String historialMedico, String prioridad) {
        super(nombre, apellido, cedula, edad, fechaNacimiento, motivoConsulta, historialMedico);
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public void mostrarEstado() {
        System.out.println("Prioridad: "+prioridad);
    }
}
