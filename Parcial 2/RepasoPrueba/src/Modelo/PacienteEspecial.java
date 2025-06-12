package Modelo;

public class PacienteEspecial extends Paciente{
    private String especialidad;

    public PacienteEspecial(String nombre, String apellido, String cedula, int edad, String fechaNacimiento, String motivoConsulta, String historialMedico, String especialidad) {
        super(nombre, apellido, cedula, edad, fechaNacimiento, motivoConsulta, historialMedico);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public void mostrarEstado() {
        super.mostrarEstado();
        System.out.println("Especialidad: "+especialidad);
    }
}
