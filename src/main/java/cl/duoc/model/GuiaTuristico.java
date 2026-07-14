package cl.duoc.model;

public class GuiaTuristico extends Persona implements Registrable {

    private String especialidad;

    public GuiaTuristico(String nombre, String especialidad) {
        super(nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String mostrarResumen() {
        return "Nombre: "+ super.getNombre()+" | Especialidad: "+especialidad;
    }
}
