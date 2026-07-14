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
        setSize(950, 650);
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

        JLabel lbl1 = new JLabel ("Nombre");
        JLabel lbl2 = new JLabel ("Cargo");

        JTextField txt1 = new JTextField();
        JTextField txt2 = new JTextField();

        areaA.add(lbl1);
        areaA.add(lbl2);
        areaA.add(txt1);
        areaA.add(txt2);
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        JPanel panelBtn = new JPanel();

        panelBtn.add(btnGuardar);
        panelBtn.add(btnLimpiar);

        add(areaA, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
        add(panelBtn, BorderLayout.SOUTH);

        entidad.addActionListener(e-> cambiarCampos());
        btnGuardar.addActionListener(e->guardar());
        btnLimpiar.addActionListener(e->limpiar());

        cambiarCampos();
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
        }
        limpiar();
    }

    private JTextArea crearTextArea(){
        JTextArea area = new JTextArea();
        area.setEditable(false);
        return area;
    }

    private void guardar(){
        String opcion = (String) entidad.getSelectedItem();
        switch (opcion){
            case"Colaborador Externo":
                ColaboradorExterno c = new ColaboradorExterno(txt1.getText(), txt2.getText());
                break;
            case"Guía Turístico":
                GuiaTuristico g = new GuiaTuristico(txt1.getText(), txt2.getText());
                break;
            case"Vehículo":
                Vehiculo v = new Vehiculo(txt1.getText(), Integer.parseInt(txt2.getText()));
                break;
        }

        area.setText("~~~Persona guardada~~~\n");
        area.append(manager.mostrarRegistros());
    }

    private void limpiar(){
        txt1.setText("");
        txt2.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}