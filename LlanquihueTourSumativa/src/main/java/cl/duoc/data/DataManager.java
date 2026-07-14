package cl.duoc.data;

import cl.duoc.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private List<Registrable> entidades = new ArrayList<>();

    public String mostrarRegistros(){
        for(Registrable registro : entidades){
            registro.mostrarResumen();

            if(registro instanceof ColaboradorExterno c) {
                System.out.println(" -> Cargo: " + c.getCargo());
            }else if (registro instanceof GuiaTuristico g){
                System.out.println(" -> Especialidad: " + g.getEspecialidad());
            }else if (registro instanceof Vehiculo v){
                System.out.println(" -> Modelo: " + v.getModelo());
            }
            System.out.println("\n‿︵‿୨୧‿︵‿\n");
        }
        return null;
    }

    public void RegistrarPersona(Registrable registrable){
        entidades.add(registrable);
    }

    public void RegistrarVehiculo(Registrable registrable){
        entidades.add(registrable);
    }
    public void cargarRegistros(){
        entidades.add(new ColaboradorExterno("Pedro Valdivia", "Chofer"));
        entidades.add(new GuiaTuristico("Manuel Rodriguez", "trekking"));
        entidades.add(new Vehiculo("Kia-3000", 6));
    }

    public List<Registrable> obtenerRegistros(){
        return entidades;
    }


}
