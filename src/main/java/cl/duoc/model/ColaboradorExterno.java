package cl.duoc.model;

public class ColaboradorExterno extends Persona implements Registrable{

    private String cargo;

    public ColaboradorExterno(String nombre, String cargo) {
        super(nombre);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String mostrarResumen() {
        return "Nombre: "+ super.getNombre()+" | Cargo: "+cargo;
    }
}
