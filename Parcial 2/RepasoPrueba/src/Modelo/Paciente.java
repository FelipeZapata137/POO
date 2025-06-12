package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Persona {
    private String fechaNacimiento;
    private String motivoConsulta;
    private List<String> historialMedico;

    public Paciente(String nombre, String apellido, String cedula, int edad, String fechaNacimiento, String motivoConsulta, String historialMedico) {
        super(nombre, apellido, cedula, edad);
        this.fechaNacimiento = fechaNacimiento;
        this.motivoConsulta = motivoConsulta;
        this.historialMedico = new ArrayList<>();
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setHistorialMedico(List<String> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public List<String> getHistorialMedico() {
        return historialMedico;
    }

    public void mostrarEstado() {
        super.mostrarInfo();
        System.out.println("Fecha de Nacimiento: "+fechaNacimiento);
        System.out.println("Motivo de Consulta: "+motivoConsulta);
        if(!historialMedico.isEmpty()){
            System.out.println("Historial Medico: "+String.join(";",historialMedico));
        } else {
            System.out.println("Historial Medico: Sin entradas.");
        }
    }
}
