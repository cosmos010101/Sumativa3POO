package cl.duoc.ui;

import cl.duoc.data.DataManager;
import cl.duoc.model.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final DataManager manager = new DataManager();
    private final ArrayList<Registrable> entidades = new ArrayList<>();
    private final JList<Registrable> registrableList = new JList<>();
    private final JTextArea area = crearTextArea();


    public Main() {
        super("Llanquihue Tour - Registro de Entidades");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextArea crearTextArea(){
        JTextArea area = new JTextArea();
        area.setEditable(false);
        return area;
    }

    private Component crearPanelEntidades() {
        JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Guía Turístico", "Colaborador externo", "Vehículo"});

        JTextField nombre = new JTextField();
        JTextField cargo = new JTextField();
        JTextField especialidad = new JTextField();
        JTextField modelo = new JTextField();
        JTextField capacidad = new JTextField();

        JButton resumen = new JButton("Mostrar Resumen");
        resumen.addActionListener(e -> conEntidadSeleccionada(registrableList::mostrarResumen));

        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(e -> {
            ColaboradorExterno uno = new ColaboradorExterno(nombre.getText(), cargo.getText());
            manager.RegistrarPersona();
            area.setText("~~~Persona guardada~~~\n");
            area.append(manager.mostrarRegistros());
        });
        guardar.addActionListener(e -> conEntidadSeleccionada(registrableList::guardar));

        JButton limpiar = new JButton("Cancelar registro");
        limpiar.addActionListener(e -> conEntidadSeleccionada(registrableList::limpiar));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botones.add(resumen);
        botones.add(guardar);
        botones.add(limpiar);

        JPanel centro = new JPanel(new BorderLayout(5, 5));
        centro.setBorder(BorderFactory.createTitledBorder("Resumen"));
        centro.add(new JScrollPane(entidades),BorderLayout.CENTER);
        centro.add(botones, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(top, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);


        return panel;
    }

    private void conEntidadSeleccionada)java.util.function.Consumer<Registrable> accion){
        Registrable seleccionada = registrableList.getSelectedValue();
        if(seleccionada == null){
            JOptionPane.showMessageDialog(this, "Seleccione una entidad");
            return;
        }
        accion.accept(seleccionada);
        registrableList.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}