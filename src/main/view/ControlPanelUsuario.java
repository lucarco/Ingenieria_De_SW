package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.SoftBevelBorder;

/**
 * @author Alejandro Remiro, Adrian Valverde, Marta Hernandez
 */
public class ControlPanelUsuario extends JPanel implements ActionListener {

    private JButton mesaButton;
    private JButton juegosButton;
    private JButton clientesButton;
    private JButton _home;

    ControlPanelUsuario() {
        initGUI();
    }

    private void initGUI() {
        setLayout(new BorderLayout());

        this.setBackground(new Color(36, 54, 69));

        JToolBar barraPrincipal = crearToolBar();

        JLabel titulo = new JLabel();

        titulo.setText("Casino ");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 60));
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        _home = new JButton();
        _home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ControlPanelUsuario.this);
                mainWindow.showInicioSesionPanel();

            }

        });

        _home.setIcon(new ImageIcon("icons/atras.png"));
        _home.setBackground(new Color(0, 9, 60));
        _home.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        _home.setBorderPainted(true);
        _home.setOpaque(true);

        barraPrincipal.add(titulo, BorderLayout.EAST);
        barraPrincipal.add(_home, BorderLayout.WEST);

        this.add(barraPrincipal, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(36, 54, 69));
        /*
        mesaButton = createButton("Mesa", "icons/oyq69sls.jpg", 150, 150);
        mesaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción al hacer clic en el botón de Mesa
            }
        });
        centerPanel.add(mesaButton);
        */

        juegosButton = createButton("Juegos",
                "icons/gambling-equipment-in-roulette-type-casinos-competitive-games-bet-in-the-casino-table-for-gambling-called-roulette-3d-rendering-free-photo.jpg",
                150, 150);
        juegosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ControlPanelUsuario.this);
                 mainWindow.showSeleccionarMesaPanel();
            }
        });
        centerPanel.add(juegosButton);

        clientesButton = createButton("Clientes", "icons/juan.jpg", 150, 150);
        clientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(ControlPanelUsuario.this);
                mainWindow.showPerfilClientePanel();
            }
        });

        centerPanel.add(clientesButton);

        centerPanel.setBackground(Color.black);
        add(Box.createRigidArea(getPreferredSize()), BorderLayout.WEST);
        add(Box.createRigidArea(getPreferredSize()), BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

    }

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

    private JButton createButton(String text, String imagePath, int width, int height) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setMargin(new Insets(10, 20, 10, 20)); // Añadir margen a los botones
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Acción del botón Home
    }
}
