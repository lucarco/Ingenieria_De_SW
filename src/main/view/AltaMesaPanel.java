package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import juegos.control.ControllerJuegos;
import mesas.control.ControllerMesas;

/**
 * Panel para el alta de una nueva mesa en el casino.
 * Permite ingresar la información necesaria para dar de alta una mesa.
 * Se puede especificar el ID de la mesa, el juego asociado .
 * Una vez ingresados los datos, se puede dar de alta la mesa.
 * @author Alejandro Ramos Yañez, Alejandro Remiro, Adrian Valverde
 */
public class AltaMesaPanel extends JPanel {
    
    JTextField id;
    JTextField juego;
    JTextField jugadores;
    ControllerMesas control;
    
    /**
     * Constructor de la clase AltaMesaPanel.
     * @param c El controlador de mesas que se utilizará para dar de alta la mesa.
     */
    public AltaMesaPanel(ControllerMesas c) {
        control = c;
        initGUI();
    }
    
    /**
     * Inicializa la interfaz gráfica de usuario para el panel de alta de mesa.
     * Crea los componentes necesarios y los agrega al panel.
     */
    private void initGUI() {
        // Creación de campos de texto para ingresar información de la mesa
        id = new JTextField();
        juego = new JTextField();
        jugadores = new JTextField();
        
        // Configuración del diseño del panel
        this.setLayout(new BorderLayout());
        JToolBar toolbar =  crearToolBar();
        JButton botonAtras = new JButton();
        
        // Configuración del botón para regresar
        botonAtras.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(AltaMesaPanel.this);
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
        
        // Creación de paneles para organizar los componentes
        JPanel central = new JPanel();
        central.setLayout(new BorderLayout());
        central.setBackground(Color.BLACK);
        JPanel central2 = new JPanel();
        central2.setBackground(Color.BLACK);
        central2.setLayout(new BorderLayout());
        
        JPanel central3 = new JPanel();
        central3.setLayout(new BoxLayout(central3, BoxLayout.Y_AXIS));
        central3.setBackground(Color.BLACK);
        
        // Configuración de etiquetas y campos de texto para ingresar información de la mesa
        JLabel labelID = new JLabel("ID Mesa");
        labelID.setForeground(Color.WHITE);
        labelID.setFont(new Font("Arial", Font.BOLD, 40));
        central3.add(labelID);
        id.setFont(new Font("Arial", Font.BOLD, 40));
        central3.add(id);
        
        JLabel labeljuego = new JLabel("Juego ");
        labeljuego.setForeground(Color.WHITE);
        labeljuego.setFont(new Font("Arial", Font.BOLD, 40));
        central3.add(labeljuego);
        juego.setFont(new Font("Arial", Font.BOLD, 40));
        central3.add(juego);
        
        JLabel labeljugadores = new JLabel("Jugadores");
        labeljugadores.setForeground(Color.WHITE);
        labeljugadores.setFont(new Font("Arial", Font.BOLD, 40));
        central3.add(labeljugadores);
        jugadores.setFont(new Font("Arial", Font.BOLD, 40));
        central3.add(jugadores);
        
        central2.add(central3, BorderLayout.PAGE_END);
        central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.EAST);
        central.add(Box.createRigidArea(new Dimension(500, 200)), BorderLayout.WEST);
        
        // Configuración del botón para dar de alta la mesa
        JPanel panelAuxB = new JPanel();
        panelAuxB.setLayout(new BorderLayout());
        panelAuxB.setBackground(Color.BLACK);
        panelAuxB.add(Box.createRigidArea(new Dimension(0, 60)), BorderLayout.PAGE_START);
        panelAuxB.add(crearBoton("Dar Alta Mesa"), BorderLayout.PAGE_END);
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
                // Verifica si los campos no están vacíos y realiza la acción correspondiente
                if (!id.getText().isEmpty() && !juego.getText().isEmpty() && !jugadores.getText().isEmpty()) {
                    if(control.altaMesa(id.getText(), juego.getText(), jugadores.getText())) {
                        ViewUtils.showSuccessMsg("Nueva Mesa Anadida");
                    } else {
                        ViewUtils.showErrorMsg("Operacion no valida");
                    }
                } else {
                    ViewUtils.showErrorMsg("Datos Incorrectos");
                }
            }
        });
            
        return b;
    }
}
