package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import mesas.control.ControllerMesas;
import mesas.model.Mesa;

/**
 * Panel para consultar una mesa en el casino.
 * Permite ingresar el ID de la mesa que se desea consultar.
 * Muestra la información de la mesa consultada en una tabla.
 * @author Alejandro Ramos Yañez
 */
public class ConsultaMesaPanel extends JPanel {
    JTextField id;
    ControllerMesas control;
    private DefaultTableModel dataTableModel;
    
    /**
     * Constructor de la clase ConsultaMesaPanel.
     * @param c El controlador de mesas que se utilizará para consultar la mesa.
     */
    public ConsultaMesaPanel(ControllerMesas c) {
        control = c;
        initGUI();
    }
    
    /**
     * Inicializa la interfaz gráfica de usuario para el panel de consulta de mesa.
     * Crea los componentes necesarios y los agrega al panel.
     */
    private void initGUI() {
        id = new JTextField();
        
        this.setLayout(new BorderLayout());
        JToolBar toolbar =  crearToolBar();
        JButton botonAtras = new JButton();
        
        botonAtras.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ConsultaMesaPanel.this);
                mainWindow.showMesasTrabajador();
            }
            
        });
        
        botonAtras.setIcon(new ImageIcon("icons/atras.png"));
        botonAtras.setBackground(new Color(0, 9, 60));
        botonAtras.setBorder(null);
        botonAtras.setBorderPainted(false);
        botonAtras.setOpaque(false);
        
        this.setBackground(Color.BLACK);
        toolbar.add(botonAtras, BorderLayout.WEST);
        this.add(toolbar, BorderLayout.NORTH);
        JPanel central = new JPanel();
        central.setLayout(new BorderLayout());
        central.setBackground(Color.BLACK);
        JPanel central2 = new JPanel();
        central2.setBackground(Color.BLACK);
        central2.setLayout(new BorderLayout());
        
        
        JPanel central3 = new JPanel();

        central.setBackground(Color.BLACK);

        central3.setLayout(new BoxLayout(central3, BoxLayout.Y_AXIS));

        central3.setBackground(Color.BLACK);

        //String[] columnHeaders = {"id", "juego", "jugadores"};
        String[] columnHeaders = {"id", "juego"};
        
        dataTableModel = new DefaultTableModel(columnHeaders,0);
        //dataTableModel.setColumnCount(3);
        dataTableModel.setColumnCount(2);

        JTable dataTable = new JTable(dataTableModel);
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        central2.add(scrollPane, BorderLayout.CENTER);
        

        JLabel labelMatricula = new JLabel("id");

        labelMatricula.setForeground(Color.WHITE);

        labelMatricula.setFont(new Font("Arial", Font.BOLD, 40));

        central3.add(labelMatricula);

        id.setFont(new Font("Arial", Font.BOLD, 40));

        central3.add(id);

        central2.add(central3, BorderLayout.PAGE_END);
        central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
        central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
        central.add(Box.createRigidArea(new Dimension(500, 25)), BorderLayout.NORTH);
        JPanel panelAuxB = new JPanel();
        panelAuxB.setLayout(new BorderLayout());
        panelAuxB.setBackground(Color.BLACK);
        panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
        panelAuxB.add(crearBoton("Consultar Mesa"), BorderLayout.PAGE_END);
        central.add(panelAuxB, BorderLayout.PAGE_END);
        central.add(central2, BorderLayout.CENTER);
        this.add(central, BorderLayout.CENTER);
    }

    /**
     * Crea y configura la barra de herramientas superior del panel.
     * @return La barra de herramientas configurada.
     */
    private JToolBar crearToolBar() {

        JToolBar toolbar = new JToolBar();
        toolbar.setLayout(new BorderLayout());

        JLabel titulo = new JLabel();

        titulo.setText("Casino ");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        toolbar.setBackground(new Color(0, 9, 128));
        toolbar.setFloatable(false);
        toolbar.add(titulo, BorderLayout.EAST);

        toolbar.setBorderPainted(false);

        return toolbar;

    }

    /**
     * Crea y configura un botón con el texto especificado.
     * Este botón permite consultar la mesa ingresada.
     * @param texto El texto a mostrar en el botón.
     * @return El botón configurado.
     */
    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(new Color(59, 89, 182));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Tahoma", Font.BOLD, 60));
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!id.getText().isEmpty()) {
                    actualizarMesas(control.consultaMesa(id.getText()));
                } else {
                    ViewUtils.showErrorMsg("Por favor, rellene los datos correctamente");
                }
            }
        });
            
        return b;
    }
    
    /**
     * Actualiza la tabla de mesas con la información de la mesa consultada.
     * @param m La mesa consultada.
     */
    private void actualizarMesas(Mesa m) {
        //dataTableModel.setRowCount(0);
            String id = m.getId();
            String juego = m.getJuego();
            String jugadores = m.getJugadores();
           
            if (id != null)
                dataTableModel.addRow(new Object[]{id, juego, jugadores});
            else 
                ViewUtils.showErrorMsg("id no existente");
        }
}
