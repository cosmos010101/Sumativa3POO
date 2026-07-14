package cl.duoc.model;

public class Vehiculo implements Registrable{

    private String modelo;
    private int capacidad;

    public Vehiculo(String modelo, int capacidad) {
        this.modelo = modelo;
        this.capacidad = capacidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("Modelo: "+modelo+"| Capacidad: "+capacidad);
    }
}
