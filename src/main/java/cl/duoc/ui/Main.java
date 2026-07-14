package cl.duoc.ui;

import cl.duoc.data.DataManager;
import cl.duoc.model.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final DataManager manager = new DataManager();
    private JComboBox<String> entidad;
    private final ArrayList<Registrable> entidades = new ArrayList<>();
    private final JList<Registrable> registrableList = new JList<>();
    private final JTextArea area = crearTextArea();

    private JLabel lbl1;
    private JLabel lbl2;
    private JTextField txt1;
    private JTextField txt2;


    public Main() {
        super("Llanquihue Tour - Registro de Entidades");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 350);
        setLocationRelativeTo(null);
        setVisible(true);

        crearPanelEntidades();
    }

    private void crearPanelEntidades(){
        JPanel areaA = new JPanel(new GridLayout(4,2,5,5));
        areaA.add(new JLabel("Entidad"));
        entidad = new JComboBox<>(new String[]{
                "Colaborador Externo", "Guía Turístico", "Vehículo"
        });

        areaA.add(entidad);

        lbl1 = new JLabel ("Nombre");
        lbl2 = new JLabel ("Cargo");

        txt1 = new JTextField();
        txt2 = new JTextField();

        areaA.add(lbl1);
        areaA.add(lbl2);
        areaA.add(txt1);
        areaA.add(txt2);
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Mostrar Registros"); //cambiar boton limpiar

        JPanel panelBtn = new JPanel();

        panelBtn.add(btnGuardar);
        panelBtn.add(btnLimpiar);

        add(areaA, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
        add(panelBtn, BorderLayout.SOUTH);


        entidad.addActionListener(e-> cambiarCampos());
        btnGuardar.addActionListener(e-> {try {
            guardar();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }});
        btnLimpiar.addActionListener(e->mostrarRegistros());


    }

    private void cambiarCampos(){
        String opcion = (String) entidad.getSelectedItem();
        switch (opcion){
            case "Colaborador Externo":
                lbl1.setText("Nombre");
                lbl2.setText("Cargo");
                break;

            case "Guía turístico":
                lbl1.setText("Nombre");
                lbl2.setText("Especialidad");
                break;

            case "Vehículo":
                lbl1.setText("Modelo");
                lbl2.setText("Capacidad");
                break;
        }
        limpiar();
    }

    private JTextArea crearTextArea(){
        JTextArea area = new JTextArea();
        area.setEditable(false);
        return area;
    }

    private void guardar(){

        try{String opcion = (String) entidad.getSelectedItem();
            switch (opcion){
                case"Colaborador Externo":
                    ColaboradorExterno c = new ColaboradorExterno(txt1.getText(), txt2.getText());
                    entidades.add(c);
                    break;
                case"Guía Turístico":
                    GuiaTuristico g = new GuiaTuristico(txt1.getText(), txt2.getText());
                    entidades.add(g);
                    break;
                case"Vehículo":
                    Vehiculo v = new Vehiculo(txt1.getText(), Integer.parseInt(txt2.getText()));
                    entidades.add(v);
                    break;
            }

            area.setText("~~~Persona guardada~~~\n");
            for(Registrable registrable : entidades){
                area.append(registrable.mostrarResumen()+"\n");
            }
            limpiar();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "¡¡ERROR!!", "Error al guardar el nombre", JOptionPane.ERROR_MESSAGE);
        }

    }

    public String mostrarRegistros(){

        area.setText("~~~Registros históricos~~~\n");

        for(Registrable registro : entidades){

            if(registro instanceof ColaboradorExterno c) {
                area.append(registro.mostrarResumen()+"\n");
            }else if (registro instanceof GuiaTuristico g){
                area.append(registro.mostrarResumen()+"\n");
            }else if (registro instanceof Vehiculo v){
                area.append(registro.mostrarResumen()+"\n");
            }
            System.out.println("\n‿︵‿୨୧‿︵‿\n");


        }
        return null;
    }

    private void limpiar(){
        txt1.setText("");
        txt2.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}