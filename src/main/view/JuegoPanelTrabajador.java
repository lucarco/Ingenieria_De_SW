package main.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

/**
 * @author Alejandro Remiro, Adrian Valverde
 */
public class JuegoPanelTrabajador extends JPanel {
	public JuegoPanelTrabajador() {
    	initGUI();
    }
    private void initGUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JToolBar toolbar = crearToolBar();
		JButton botonAtras = new JButton();
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(JuegoPanelTrabajador.this);
				mainWindow.showControlPanel();
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.BLACK);

        JButton altaJuegoButton = new JButton("Alta Juego");
        altaJuegoButton.setFont(new Font("Arial", Font.BOLD, 60));
        altaJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(JuegoPanelTrabajador.this);
				mainWindow.showAltaJuegoPanel();
            }
        });
        buttonPanel.add(altaJuegoButton);

        JButton gestionJuegoButton = new JButton("Gestion Juego");
        gestionJuegoButton.setFont(new Font("Arial", Font.BOLD, 60));
        gestionJuegoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	MainWindow mainWindow = (MainWindow) SwingUtilities.getWindowAncestor(JuegoPanelTrabajador.this);
				mainWindow.showGestionJuegosPanel();
            }
        });
        buttonPanel.add(gestionJuegoButton);

        add(buttonPanel, BorderLayout.CENTER);
        
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
}
